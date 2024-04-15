package com.shameyang.userhub.common;

import lombok.Getter;

/**
 * @author ShameYang
 * @date 2024/4/11 09:17
 * @description 自定义错误码
 */
@Getter
public enum ErrorCode {

    PARAMS_ERROR(40000, "请求参数错误", ""),
    NULL_ERROR(40001, "请求数据为空", ""),
    NO_LOGIN(40100, "未登录", ""),
    NO_AUTH(40101, "无权限", "没有管理员权限"),
    INSERT_ERROR(40300, "操作失败", "插入数据失败"),
    SYSTEM_ERROR(50000, "系统内部异常", "");

    private final int code;

    /**
     * 状态码信息
     */
    private final String message;

    /**
     * 状态码详细描述
     */
    private final String description;

    ErrorCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

}
