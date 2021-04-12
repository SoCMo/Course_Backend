package com.shu.course_backend.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: JwtReuqest
 * @Description: TODO
 * @Author: pongshy
 * @Date: 2021/1/8-10:47
 * @Version: V1.0
 **/
@Data
public class JwtRequest implements Serializable {


    private static final long serialVersionUID = 5926468583005150707L;

    private String username;

    private String password;
}
