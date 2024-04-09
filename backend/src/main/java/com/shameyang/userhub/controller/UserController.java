package com.shameyang.userhub.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shameyang.userhub.model.domain.User;
import com.shameyang.userhub.model.request.UserLoginRequest;
import com.shameyang.userhub.model.request.UserRegisterRequest;
import com.shameyang.userhub.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.shameyang.userhub.constant.UserConstant.ADMIN_ROLE;
import static com.shameyang.userhub.constant.UserConstant.USER_LOGIN_STATE;

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

    @GetMapping("/current")
    public User getCurrentUser(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null) {
            return null;
        }
        long userId = currentUser.getId();
        User user = userService.getById(userId);
        return userService.getHandlerUser(user);
    }

    @GetMapping("/search")
    public List<User> searchUser(String username, HttpServletRequest request) {
        if (isNotAdmin(request)) {
            return new ArrayList<>();
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.like("username", username);
        }
        List<User> userList = userService.list(queryWrapper);
        return userList.stream().map(user -> userService.getHandlerUser(user)).collect(Collectors.toList());
    }

    @PostMapping("/delete")
    public boolean deleteUser(@RequestBody Long id, HttpServletRequest request) {
        if (isNotAdmin(request) || id <= 0) {
            return false;
        }
        return userService.removeById(id);
    }

    /**
     * 是否为管理员
     * @param request 请求
     * @return 不是管理员
     */
    private boolean isNotAdmin(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        return user == null || user.getUserRole() != ADMIN_ROLE;
    }
}
