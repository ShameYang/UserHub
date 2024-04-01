## UserHub 用户管理系统

## 项目介绍

基于 SpringBoot + React + Ant Design Pro 的用户管理系统，实现了用户注册、登录、查询管理等功能。

## 技术选型

### 前端

- HTML + CSS + JavaScript 前端三件套
- React 开发框架
- Ant Design Pro 项目模板

### 后端

- Java
- Spring + SpringMVC + SpringBoot 开发框架
- MyBatis + MyBatis-Plus 持久层框架
- MySQL 数据库
- JUint 单元测试

### 部署

- Linux 单机部署
- Nginx
- Docker 容器
- 容器托管平台

## 数据库表设计

用户表：

| 列名         | 说明                 | 类型    |
| ------------ | -------------------- | ------- |
| id           | 主键                 | bigint  |
| user_name    | 昵称                 | varchar |
| user_account | 账号                 | varchar |
| avatar_url   | 头像                 | varchar |
| gender       | 性别（0-男 1-女）    | tinyint |
| password     | 密码                 | varchar |
| phone        | 电话                 | varchar |
| email        | 邮箱                 | varchar |
| user_status  | 用户状态（0-正常）   | tinyint |
| create_time  | 创建时间             |         |
| update_time  | 更新时间             |         |
| is_delete    | 是否删除（逻辑删除） |         |

