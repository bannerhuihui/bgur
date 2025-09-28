package com.bgur.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommonResult implements Serializable {

    private static final long serialVersionUID = -767456733498763907L;

    private Integer code;

    private String message;

    private Object data;

    private void setResultCode(CommonEun resultEnum) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMsg();
    }

    private void setResultCode(CommonEun resultEnum, Object data) {
        setResultCode(resultEnum);
        this.data = data;
    }

    public static CommonResult success() {
        CommonResult resultMessage = new CommonResult();
        resultMessage.setResultCode(CommonEun.SUCCESS);
        return resultMessage;
    }

    public static CommonResult success(Object data) {
        CommonResult resultMessage = new CommonResult();
        resultMessage.setResultCode(CommonEun.SUCCESS, data);
        return resultMessage;
    }

    public static CommonResult failure(CommonEun resultEnum) {
        CommonResult resultMessage = new CommonResult();
        resultMessage.setResultCode(resultEnum);
        return resultMessage;
    }

    public static CommonResult failure(CommonEun resultEnum, Object data) {
        CommonResult resultMessage = new CommonResult();
        resultMessage.setResultCode(resultEnum, data);
        return resultMessage;
    }

    public static CommonResult failure() {
        CommonResult resultMessage = new CommonResult();
        resultMessage.setResultCode(CommonEun.ARGUMENT_NULL);
        return resultMessage;
    }

}
