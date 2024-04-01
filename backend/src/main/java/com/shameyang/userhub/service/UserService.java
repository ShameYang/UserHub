package com.shameyang.userhub.service;

import com.shameyang.userhub.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
