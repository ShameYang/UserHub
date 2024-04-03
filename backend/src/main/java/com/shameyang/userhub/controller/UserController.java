package com.shameyang.userhub.controller;

import com.shameyang.userhub.model.domain.User;
import com.shameyang.userhub.model.request.UserLoginRequest;
import com.shameyang.userhub.model.request.UserRegisterRequest;
import com.shameyang.userhub.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ShameYang
 * @date 2024/4/3 09:31
 * @description 用户接口
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Long userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            return null;
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String password = userRegisterRequest.getPassword();
        String checkPwd = userRegisterRequest.getCheckPwd();
        if (StringUtils.isAnyBlank(userAccount, password, checkPwd)) {
            return null;
        }
        return userService.userRegister(userAccount, password, checkPwd);
    }

    @PostMapping("/login")
    public User userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            return null;
        }
        String userAccount = userLoginRequest.getUserAccount();
        String password = userLoginRequest.getPassword();
        if (StringUtils.isAnyBlank(userAccount, password)) {
            return null;
        }
        return userService.userLogin(userAccount, password, request);
    }
}
