package com.shameyang.userhub.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shameyang.userhub.common.ErrorCode;
import com.shameyang.userhub.exception.BusinessException;
import com.shameyang.userhub.model.domain.User;
import com.shameyang.userhub.service.UserService;
import com.shameyang.userhub.mapper.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.shameyang.userhub.constant.UserConstant.USER_LOGIN_STATE;

/**
 * @author shameyang
 * @description 针对表【user(用户)】的数据库操作Service实现
 * @createDate 2024-04-01 10:39:53
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    /**
     * 盐值，混淆密码
     */
    private static final String SALT = "abigsalt";

    @Override
    public long userRegister(String userAccount, String password, String checkPwd) {
        // 1. 校验
        if (StringUtils.isAnyBlank(userAccount, password, checkPwd)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号过短");
        }
        if (password.length() < 6) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码过短");
        }
        // 账号不能包含特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}:;\\\\\\\\[\\\\\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？']";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号包含特殊字符");
        }
        // 密码和确认密码相同
        if (!password.equals(checkPwd)) {
            log.info("password not match");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次密码不一致");
        }
        // 账号不能重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", userAccount);
        long count = this.count(queryWrapper);
        if (count > 0) {
            log.info("user already exists");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号已存在");
        }
        // 2.密码加密
        String handlePassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        // 3.插入数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setPassword(handlePassword);
        boolean saveResult = this.save(user);
        if (!saveResult) {
            throw new BusinessException(ErrorCode.INSERT_ERROR);
        }
        return user.getId();
    }

    @Override
    public User userLogin(String userAccount, String password, HttpServletRequest request) {
        // 1. 校验
        if (StringUtils.isAnyBlank(userAccount, password)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号过短");
        }
        if (password.length() < 6) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码过短");
        }
        // 账号不能包含特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}:;\\\\\\\\[\\\\\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？']";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号包含特殊字符");
        }
        // 2.加密
        String handlePassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        // 用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", userAccount);
        queryWrapper.eq("password", handlePassword);
        User user = this.getOne(queryWrapper);
        // 用户不存在
        if (user == null) {
            log.info("user login failed, not match");
            throw new BusinessException(ErrorCode.NULL_ERROR, "用户不匹配");
        }
        // 3.用户信息脱敏
        User handleUser = getHandlerUser(user);
        // 4.记录用户登录态
        request.getSession().setAttribute(USER_LOGIN_STATE, handleUser);
        return handleUser;
    }

    /**
     * 用户信息脱敏
     * @param user 原用户
     * @return 脱敏后的用户
     */
    @Override
    public User getHandlerUser(User user) {
        if (user == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        User handleUser = new User();
        handleUser.setId(user.getId());
        handleUser.setUserName(user.getUserName());
        handleUser.setUserAccount(user.getUserAccount());
        handleUser.setAvatarUrl(user.getAvatarUrl());
        handleUser.setGender(user.getGender());
        handleUser.setPhone(user.getPhone());
        handleUser.setEmail(user.getEmail());
        handleUser.setUserStatus(user.getUserStatus());
        handleUser.setUserRole(user.getUserRole());
        handleUser.setCreateTime(user.getCreateTime());
        return handleUser;
    }

    @Override
    public int userLogout(HttpServletRequest request) {
        // 移除登录态
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return 1;
    }
}
