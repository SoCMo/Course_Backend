package com.shu.course_backend.exception;

import org.springframework.http.HttpStatus;

/**
 * @description: 报错枚举
 * @author: 0GGmr0
 * @create: 2019-12-01 21:27
 */
public enum EmAllException implements com.shu.course_backend.exception.CommonError {
    BAD_REQUEST(400, "请求参数格式有误", HttpStatus.BAD_REQUEST),

    IDENTITY_ERROR(401, "未经权限", HttpStatus.UNAUTHORIZED),

    DATABASE_ERROR(500, "数据库异常或数据有误", HttpStatus.INTERNAL_SERVER_ERROR),

    ErrorCode(400, "wx.login接口返回", HttpStatus.BAD_REQUEST),

    INTERNAL_ERROR(500, "程序内部错误", HttpStatus.INTERNAL_SERVER_ERROR),

    USER_REFUSE(503, "服务器无法处理请求", HttpStatus.SERVICE_UNAVAILABLE),

    EMPTY_RESPONSE(504, "请求的资源为空", HttpStatus.BAD_REQUEST);

    // 错误码
    private Integer code;

    // 错误信息
    private String msg;

    private HttpStatus httpStatus;

    EmAllException(Integer code, String msg, HttpStatus httpStatus) {
        this.code = code;
        this.msg = msg;
        this.httpStatus = httpStatus;
    }

    @Override
    public Integer getErrCode() {
        return this.code;
    }

    @Override
    public com.shu.course_backend.exception.CommonError setErrMsg(String errMsg) {
        this.msg = errMsg;
        return this;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }
}
