package com.shu.course_backend.model;

import com.shu.course_backend.exception.AllException;
import com.shu.course_backend.tool.TimeTool;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
public class Result {


    private String timestamp;

    private Integer code;

    private String message;


    public Result(HttpStatus status,
                  String msg
    ) {
        Date date = new Date(System.currentTimeMillis());

        this.timestamp = TimeTool.DateToString(date);
        this.code = status.value();
        this.message = msg;
    }

    public Result(AllException ex) {
        Date date = new Date(System.currentTimeMillis());

        this.timestamp = TimeTool.DateToString(date);
        this.code = ex.getErrCode();
        this.message = ex.getMsg();
    }
}
