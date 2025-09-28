# BGUR - 企业级商务管理系统

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.16-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-8-orange.svg)](https://www.oracle.com/java/)
[![MySQL](https://img.shields.io/badge/MySQL-5.7+-blue.svg)](https://www.mysql.com/)
[![MongoDB](https://img.shields.io/badge/MongoDB-4.0+-green.svg)](https://www.mongodb.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

## 📖 项目简介

BGUR 是一个基于 Spring Boot 的企业级商务管理系统，提供完整的用户权限管理、商品管理、订单处理、积分系统等功能。系统采用现代化的微服务架构设计，支持多公司、多环境部署，具备高可扩展性和安全性。

## ✨ 核心功能

### 🔐 用户与权限管理
- **用户管理**：注册、登录、个人信息管理、多级用户体系
- **角色权限**：灵活的角色权限分配、菜单权限控制
- **会话管理**：JWT Token 认证、多设备登录管理
- **安全防护**：登录失败锁定、IP 白名单、操作审计

### 🛍️ 商品与订单系统
- **商品管理**：商品分类、库存管理、价格体系、可见性控制
- **订单处理**：下单流程、订单状态管理、物流跟踪
- **购物车**：商品收藏、批量操作、实时计算
- **促销活动**：优惠券、满减活动、限时促销

### 💰 积分与会员系统
- **积分管理**：积分获取、消费、转账、历史记录
- **会员等级**：多级会员体系、权益管理
- **推荐奖励**：邀请码系统、推荐返利

### 🏢 企业管理
- **多公司架构**：支持多个公司独立运营
- **组织架构**：部门管理、员工角色分配
- **数据隔离**：公司间数据完全隔离

### 📱 微信生态集成
- **公众号管理**：AppID/Secret 配置、菜单管理
- **小程序支持**：用户授权、支付集成
- **微信支付**：支付配置、订单回调处理

### 📊 数据分析与日志
- **操作审计**：完整的用户操作记录
- **数据统计**：商品浏览统计、销售分析
- **系统监控**：性能监控、健康检查

## 🛠️ 技术栈

### 后端技术
- **框架**：Spring Boot 2.7.16
- **安全**：Spring Security + JWT
- **数据访问**：MyBatis + MyBatis Generator
- **数据库**：MySQL 5.7+ (主数据库)
- **NoSQL**：MongoDB (日志存储)
- **消息队列**：RabbitMQ
- **工具库**：Hutool、Lombok

### 开发工具
- **API 文档**：Springdoc OpenAPI (Swagger UI)
- **监控**：Spring Boot Actuator
- **测试**：JUnit、H2 Database (测试环境)
- **构建**：Maven 3.6+

### 部署与运维
- **容器化**：Docker 支持
- **多环境**：Local/Prod 环境配置
- **日志**：SLF4J + Logback
- **缓存**：内置缓存支持

## 🚀 快速开始

### 环境要求
- Java 8+
- Maven 3.6+
- MySQL 5.7+
- MongoDB 4.0+
- RabbitMQ 3.8+

### 1. 克隆项目
```bash
git clone https://github.com/your-username/bgur.git
cd bgur
```

### 2. 数据库配置
```sql
-- 创建数据库
CREATE DATABASE bgur CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 创建用户
CREATE USER 'bgur'@'localhost' IDENTIFIED BY 'tzXr9ejizlvxlDR';
GRANT ALL PRIVILEGES ON bgur.* TO 'bgur'@'localhost';
FLUSH PRIVILEGES;
```

### 3. 配置文件
根据环境修改配置文件：
- `src/main/resources/application-local.yml` (本地开发)
- `src/main/resources/application-prod.yml` (生产环境)

### 4. 启动应用

#### 方式一：IDE 启动
```bash
# 使用 Maven 编译
mvn clean compile

# 运行主类
com.bgur.BgurApplication
```

#### 方式二：命令行启动
```bash
# 本地环境
mvn spring-boot:run -Dspring-boot.run.profiles=local

# 生产环境
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

#### 方式三：打包部署
```bash
# 打包
mvn clean package -DskipTests

# 运行
java -jar target/bgur-1.0-SNAPSHOT.jar --spring.profiles.active=prod
```

### 5. 访问应用
- **应用地址**：http://localhost:8899
- **Swagger UI 界面**：http://localhost:8899/swagger-ui/index.html
- **Swagger UI (旧版本)**：http://localhost:8899/swagger-ui.html
- **OpenAPI JSON 文档**：http://localhost:8899/v3/api-docs
- **健康检查**：http://localhost:8899/actuator/health

#### 📖 API 文档说明
- **Swagger UI**：提供可视化的 API 文档界面，支持在线测试
- **OpenAPI 3.0**：符合 OpenAPI 3.0 规范的 JSON 格式文档
- **JWT 认证**：支持在 Swagger UI 中进行 Bearer Token 认证测试
- **API 分组**：按功能模块分组展示（用户管理、菜单管理、实时通信等）

## 🐳 Docker 部署

### 构建镜像
```bash
# 使用 Spring Boot Maven 插件构建
mvn spring-boot:build-image

# 或使用 Dockerfile
docker build -t bgur-app:latest .
```

### 运行容器
```bash
docker run -d \
  --name bgur-app \
  -p 8899:8899 \
  -e "SPRING_PROFILES_ACTIVE=prod" \
  -e "SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/bgur" \
  bgur-app:latest
```

### Docker Compose
```yaml
version: '3.8'
services:
  app:
    image: bgur-app:latest
    ports:
      - "8899:8899"
    environment:
      SPRING_PROFILES_ACTIVE: "prod"
      SPRING_DATASOURCE_URL: "jdbc:mysql://mysql:3306/bgur"
    depends_on:
      - mysql
      - mongodb
      - rabbitmq
  
  mysql:
    image: mysql:5.7
    environment:
      MYSQL_DATABASE: bgur
      MYSQL_USER: bgur
      MYSQL_PASSWORD: tzXr9ejizlvxlDR
      MYSQL_ROOT_PASSWORD: root123
    ports:
      - "3306:3306"
```

## 📁 项目结构

```
bgur/
├── src/
│   ├── main/
│   │   ├── java/com/bgur/
│   │   │   ├── config/          # 配置类
│   │   │   ├── controller/      # 控制器
│   │   │   ├── service/         # 业务逻辑
│   │   │   ├── mapper/          # 数据访问层
│   │   │   ├── pojo/            # 实体类
│   │   │   ├── entity/          # 业务实体
│   │   │   ├── util/            # 工具类
│   │   │   ├── log/             # 日志注解
│   │   │   └── BgurApplication.java
│   │   └── resources/
│   │       ├── mapper/          # MyBatis XML 映射文件
│   │       ├── application.yml  # 主配置文件
│   │       ├── application-local.yml
│   │       ├── application-prod.yml
│   │       └── generatorConfig.xml
│   └── test/                    # 测试代码
├── target/                      # 编译输出
├── pom.xml                      # Maven 配置
└── README.md
```

## 🔧 开发指南

### 代码生成
使用 MyBatis Generator 自动生成代码：
```bash
mvn mybatis-generator:generate
```

### 环境切换
修改 `application.yml` 中的 `spring.profiles.active` 值：
```yaml
spring:
  profiles:
    active: local  # 可选：local, prod
```

### API 开发规范
- 使用 `@OperLog` 注解记录操作日志
- 统一返回格式和异常处理
- 接口文档使用 OpenAPI 3.0 规范

### 数据库操作
- 使用 MyBatis 进行数据访问
- 支持事务管理 `@Transactional`
- 数据库连接池使用 HikariCP

## 📋 开发计划

### 已完成功能
- ✅ 基础框架搭建
- ✅ 用户权限系统
- ✅ 商品管理模块
- ✅ 订单处理流程
- ✅ 积分系统
- ✅ 微信集成
- ✅ 操作日志记录

### 待开发功能
- 🔄 前端管理界面
- 🔄 移动端 API
- 🔄 数据报表系统
- 🔄 消息推送服务
- 🔄 文件上传管理
- 🔄 系统配置管理

## 🧪 API 测试指南

### Swagger UI 使用说明

#### 1. 访问 Swagger UI
启动应用后，访问以下地址查看 API 文档：
- **主要地址**：http://localhost:8899/swagger-ui/index.html
- **备用地址**：http://localhost:8899/swagger-ui.html (自动重定向)

#### 2. API 文档结构
- **用户管理**：登录、登出、Token 刷新等用户认证相关接口
- **菜单管理**：获取用户菜单树结构
- **实时通信**：SSE 相关功能接口（待开发）

#### 3. JWT 认证测试
1. 点击页面右上角的 "Authorize" 按钮
2. 在弹出框中输入：`Bearer {your-jwt-token}`
3. 点击 "Authorize" 完成认证
4. 现在可以测试需要认证的接口

#### 4. 测试流程建议
1. 首先调用 `/login` 接口获取 JWT Token
2. 使用获取的 Token 进行认证
3. 测试其他需要认证的接口
4. 使用 `/logout/{userId}` 接口登出

#### 5. 其他访问方式
- **JSON 格式文档**：http://localhost:8899/v3/api-docs
- **健康检查**：http://localhost:8899/actuator/health

## 🤝 贡献指南

1. Fork 本仓库
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 打开 Pull Request

## 📄 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情。

## 📞 联系方式

- **项目维护者**：huihui
- **邮箱**：your-email@example.com
- **项目地址**：https://github.com/your-username/bgur

## 🙏 致谢

感谢以下开源项目的支持：
- [Spring Boot](https://spring.io/projects/spring-boot)
- [MyBatis](https://mybatis.org/)
- [Hutool](https://hutool.cn/)
- [Swagger](https://swagger.io/)

---

⭐ 如果这个项目对你有帮助，请给它一个星标！