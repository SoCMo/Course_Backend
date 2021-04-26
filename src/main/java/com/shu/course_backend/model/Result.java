package com.shu.course_backend.model;

import com.shu.course_backend.exception.AllException;
import com.shu.course_backend.tool.TimeTool;
import com.sun.net.httpserver.Authenticator;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
public class Result<T> {


    private String timestamp;

    private Integer code;

    private String message;

    private T data;


    private Result(HttpStatus status,
                  String msg
    ) {
        Date date = new Date(System.currentTimeMillis());

        this.timestamp = TimeTool.DateToString(date);
        this.code = status.value();
        this.message = msg;
    }

    private Result(AllException ex) {
        Date date = new Date(System.currentTimeMillis());

        this.timestamp = TimeTool.DateToString(date);
        this.code = ex.getErrCode();
        this.message = ex.getMsg();
    }

    public static Result success(Object object){
        Result result = new Result(HttpStatus.OK, "");
        result.setData(object);
        return result;
    }

    public static Result success(String msg) {
        Result result = new Result(HttpStatus.OK, msg);
        return result;
    }

    public static Result error(AllException ex){
        return new Result(ex);
    }
}
