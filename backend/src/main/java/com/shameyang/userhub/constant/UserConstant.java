package com.shameyang.userhub.constant;

/**
 * @author ShameYang
 * @date 2024/4/7 11:00
 * @description 用户常量
 */
public interface UserConstant {

    /**
     * 用户登录态键
     */
    String USER_LOGIN_STATE = "userLoginState";

    // ------ 权限 ------
    /**
     * 普通用户
     */
    int DEFAULT_ROLE = 0;
    /**
     * 管理员
     */
    int ADMIN_ROLE = 1;
}
