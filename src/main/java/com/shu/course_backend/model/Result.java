package com.shu.course_backend.model;

import com.shu.course_backend.exception.AllException;
import com.shu.course_backend.exception.EmAllException;
import com.shu.course_backend.tool.TimeTool;
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

    public static Result success(Object object) {
        Result result = new Result(HttpStatus.OK, "");
        result.setData(object);
        return result;
    }

    public static Result success() {
        return new Result(HttpStatus.OK, "");
    }

    public static Result success(String msg) {
        Result result = new Result(HttpStatus.OK, msg);
        return result;
    }

    public static Result error(AllException ex) {
        return new Result(ex);
    }

    public static Result error(EmAllException ex) {
        return new Result(new AllException(ex));
    }

    public static Result error(EmAllException ex, String msg) {
        return new Result(new AllException(ex, msg));
    }
}
