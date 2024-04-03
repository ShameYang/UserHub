package com.shameyang.userhub.model.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author ShameYang
 * @date 2024/4/3 09:40
 * @description 用户注册请求体
 */
@Data
public class UserRegisterRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -1731318405328532068L;

    private String userAccount;

    private String password;

    private String checkPwd;
}
