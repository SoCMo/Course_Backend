package com.shu.course_backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.shu.course_backend.dao")
public class CourseBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseBackendApplication.class, args);
    }

}
