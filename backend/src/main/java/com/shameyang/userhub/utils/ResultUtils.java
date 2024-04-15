package com.shameyang.userhub.utils;

import com.shameyang.userhub.common.BaseResponse;
import com.shameyang.userhub.common.ErrorCode;

/**
 * @author ShameYang
 * @date 2024/4/11 09:02
 * @description 通用返回对象工具类
 */
public class ResultUtils {

    /**
     * 成功
     * @param data 返回的数据
     * @param <T> 返回类型
     * @return 成功对象
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(0, data, "ok", "");
    }

    /**
     * 失败
     * @param errorCode 错误码
     * @return 失败对象
     */
    public static BaseResponse error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode);
    }

    public static BaseResponse error(int errorCode, String message, String description) {
        return new BaseResponse<>(errorCode, null, message, description);
    }

    public static BaseResponse error(ErrorCode errorCode, String message, String description) {
        return new BaseResponse<>(errorCode, message, description);
    }
}
