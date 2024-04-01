package com.shameyang.userhub.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shameyang.userhub.model.domain.User;
import com.shameyang.userhub.service.UserService;
import com.shameyang.userhub.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author shameyang
 * @description 针对表【user(用户)】的数据库操作Service实现
 * @createDate 2024-04-01 10:39:53
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Override
    public long userRegister(String userAccount, String password, String checkPwd) {
        // 1. 校验
        if (StringUtils.isAnyBlank(userAccount, password, checkPwd)) {
            return -1;
        }
        if (userAccount.length() < 4) {
            return -1;
        }
        if (password.length() < 6) {
            return -1;
        }
        // 账号不能包含特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}:;\\\\\\\\[\\\\\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？']";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            return -1;
        }
        // 密码和确认密码相同
        if (!password.equals(checkPwd)) {
            return -1;
        }
        // 账号不能重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", userAccount);
        long count = this.count(queryWrapper);
        if (count > 0) {
            return -1;
        }
        // 2.密码加密
        final String SALT = "abigsalt";
        String handlePassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        // 3.插入数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setPassword(handlePassword);
        boolean saveResult = this.save(user);
        if (!saveResult) {
            return -1;
        }
        return user.getId();
    }
}




