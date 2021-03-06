package com.shu.course_backend.exception;

import org.springframework.http.HttpStatus;

/**
 * @description: 错误操作
 * @author: 0GGmr0
 * @create: 2019-12-01 21:25
 */
public class AllException extends Exception implements com.shu.course_backend.exception.CommonError {

    private com.shu.course_backend.exception.CommonError commonError;

    // 直接接收EmSqlException的传参用于构造业务异常
    public AllException(com.shu.course_backend.exception.CommonError commonError) {
        super();
        this.commonError = commonError;
    }

    // 接收自定义msg的方式构造异常
    public AllException(com.shu.course_backend.exception.CommonError commonError, String msg) {
        super();
        this.commonError = commonError;
        this.commonError.setErrMsg(msg);
    }

    @Override
    public Integer getErrCode() {
        return this.commonError.getErrCode();
    }

    @Override
    public String getMsg() {
        return this.commonError.getMsg();
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.commonError.getHttpStatus();
    }

    @Override
    public com.shu.course_backend.exception.CommonError setErrMsg(String errMsg) {
        this.commonError.setErrMsg(errMsg);
        return this;
    }
}
