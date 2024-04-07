package com.shameyang.userhub.service;

import com.shameyang.userhub.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;

/**
* @author shameyang
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2024-04-01 10:39:53
*/
public interface UserService extends IService<User> {
    /**
     * 用户注册
     * @param userAccount 账号
     * @param password 密码
     * @param checkPwd 确认密码
     * @return 新用户 id
     */
    long userRegister(String userAccount, String password, String checkPwd);

    /**
     * 用户登录
     * @param userAccount 账号
     * @param password 密码
     * @return 脱敏后的用户信息
     */
    User userLogin(String userAccount, String password, HttpServletRequest request);

    /**
     * 用户信息脱敏
     * @param user 原用户
     * @return 脱敏后的用户
     */
    User getHandlerUser(User user);
}
