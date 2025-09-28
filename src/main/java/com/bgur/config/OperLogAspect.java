package com.bgur.config;

import cn.hutool.core.lang.UUID;
import cn.hutool.extra.servlet.ServletUtil;
import com.bgur.entity.ExcEntity;
import com.bgur.entity.LoggerEntity;
import com.bgur.log.OperLog;
import com.bgur.util.JacksonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @projectName: bgur
 * @package: com.bgur.config
 * @className: OperLogAspect
 * @description: TODO
 * @date: 2025/7/16 18:24
 * @version: 1.0
 */
@Aspect
@Component
public class OperLogAspect {

    public static final String dateformat = "yyyy:MM:dd HH:mm:ss";

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 设置操作日志切入点 记录操作日志 在注解的位置切入代码
     */
    @Pointcut("@annotation(com.bgur.log.OperLog)")
    public void operLogPoinCut() {

    }

    /**
     * 设置操作异常切入点记录异常日志 扫描所有controller包下操作
     */
    @Pointcut("execution(* com.bgur.controller..*.*(..))")
    public void operExceptionLogPoinCut() {}

    /**
     * 正常返回通知，拦截用户操作日志，连接点正常执行完成后执行， 如果连接点抛出异常，则不会执行
     *
     * @param joinPoint 切入点
     * @param keys 返回结果
     */
    @AfterReturning(value = "operLogPoinCut()", returning = "keys")
    public void saveOperLog(JoinPoint joinPoint, Object keys) {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request =
                (HttpServletRequest)requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

        LoggerEntity operlog = new LoggerEntity();
        try {
            operlog.setOperId(UUID.fastUUID().toString()); // 主键ID

            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature)joinPoint.getSignature();
            // 获取切入点所在的方法
            Method method = signature.getMethod();
            // 获取操作
            OperLog opLog = method.getAnnotation(OperLog.class);
            if (opLog != null) {
                String operModul = opLog.operModule();
                String operType = opLog.operType();
                String operDesc = opLog.operDesc();
                operlog.setOperModule(operModul); // 操作模块
                operlog.setOperType(operType); // 操作类型
                operlog.setOperDesc(operDesc); // 操作描述
            }
            // 获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();
            // 获取请求的方法名
            String methodName = method.getName();
            methodName = className + "." + methodName;

            operlog.setOperMethod(methodName); // 请求方法
            String params = convertArgsToJsonString(joinPoint);
            // 请求的参数
            // 将参数所在的数组转换成json
            operlog.setOperRequestParam(params); // 请求参数
            operlog.setOperResponseParam(JacksonUtil.toJSONString(keys)); // 返回结果
            operlog.setOperIp(ServletUtil.getClientIP(request, null)); // 请求IP
            operlog.setOperUri(request.getRequestURI()); // 请求URI
            operlog.setCreateTime(new Date()); // 创建时间// 操作版本
            mongoTemplate.save(operlog,"LoggerEntity");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 异常返回通知，用于拦截异常日志信息 连接点抛出异常后执行
     *
     * @param joinPoint 切入点
     * @param e 异常信息
     */
    @AfterThrowing(pointcut = "operExceptionLogPoinCut()", throwing = "e")
    public void saveExceptionLog(JoinPoint joinPoint, Throwable e) {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request =
                (HttpServletRequest)requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

        ExcEntity excepLog = new ExcEntity();
        try {
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature)joinPoint.getSignature();
            // 获取切入点所在的方法
            Method method = signature.getMethod();
            excepLog.setExcId(UUID.fastUUID().toString());
            // 获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();
            // 获取请求的方法名
            String methodName = method.getName();
            methodName = className + "." + methodName;
            // 请求的参数
            String params = convertArgsToJsonString(joinPoint);
            // 将参数所在的数组转换成json
            excepLog.setExcRequestParam(params); // 请求参数
            excepLog.setOperMethod(methodName); // 请求方法名
            excepLog.setExcName(e.getClass().getName()); // 异常名称
            excepLog.setExcMessage(stackTraceToString(e.getClass().getName(), e.getMessage(), e.getStackTrace())); // 异常信息
            excepLog.setOperUri(request.getRequestURI()); // 操作URI
            excepLog.setOperIp(ServletUtil.getClientIP(request, null)); // 操作员IP
            excepLog.setCreateTime(new Date()); // 发生异常时间
            mongoTemplate.save(excepLog,"ExcEntity");
        } catch (Exception e2) {
            e2.printStackTrace();
        }

    }

    /**
     * 转换异常信息为字符串
     *
     * @param exceptionName 异常名称
     * @param exceptionMessage 异常信息
     * @param elements 堆栈信息
     */
    public String stackTraceToString(String exceptionName, String exceptionMessage, StackTraceElement[] elements) {
        StringBuffer strbuff = new StringBuffer();
        for (StackTraceElement stet : elements) {
            strbuff.append(stet + "\n");
        }
        String message = exceptionName + ":" + exceptionMessage + "\n\t" + strbuff.toString();
        return message;
    }

    public static String convertArgsToJsonString(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        StringBuilder sb = new StringBuilder();

        sb.append("[");
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            sb.append(convertObjectToJson(arg));
            if (i < args.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");

        return sb.toString();
    }

    private static String convertObjectToJson(Object obj) {
        if (obj == null) {
            return "null";
        }
        if (obj instanceof String) {
            return "\"" + escapeJsonString((String) obj) + "\"";
        }
        if (obj instanceof Number || obj instanceof Boolean) {
            return obj.toString();
        }
        // 这里可以根据需要增加对其他类型的处理，比如数组、集合、对象等
        return "\"" + escapeJsonString(obj.toString()) + "\"";
    }

    private static String escapeJsonString(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            switch (c) {
                case '\"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                default:
                    if (c < ' ') {
                        String t = "000" + Integer.toHexString(c);
                        sb.append("\\u" + t.substring(t.length() - 4));
                    } else {
                        sb.append(c);
                    }
            }
        }
        return sb.toString();
    }
}
