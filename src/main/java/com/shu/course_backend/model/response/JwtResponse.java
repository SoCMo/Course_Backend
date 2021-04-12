package com.shu.course_backend.model.response;

import java.io.Serializable;

/**
 * @ClassName: JwtResponse
 * @Description: TODO
 * @Author: pongshy
 * @Date: 2021/1/8-14:43
 * @Version: V1.0
 **/
public class JwtResponse implements Serializable {


    private static final long serializableUID = -8091879091924046844L;

    private final String jwttoken;

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }


    public String getToken() {
        return this.jwttoken;
    }
}
