package com.shameyang.userhub.model.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author ShameYang
 * @date 2024/4/3 09:44
 * @description 用户登录请求体
 */
@Data
public class UserLoginRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 2845425839137970274L;

    private String userAccount;

    private String password;
}
