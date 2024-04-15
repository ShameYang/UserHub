package com.shameyang.userhub.exception;

import com.shameyang.userhub.common.ErrorCode;
import lombok.Getter;

import java.io.Serial;

/**
 * @author ShameYang
 * @date 2024/4/11 09:43
 * @description 业务异常
 */
@Getter
public class BusinessException extends RuntimeException {

    private final int code;

    private final String description;

    public BusinessException(String message, int code, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }

    public BusinessException(ErrorCode errorCode, String description) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = description;
    }
}
