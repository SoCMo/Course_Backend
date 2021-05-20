package com.shu.course_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("test")
public class TestController {

    /**
     * @Description: 测试接口
     * @Param: [num]
     * @return: org.springframework.http.ResponseEntity<?>
     * @Author: pongshy
     * @Date: 2021/1/6
     * @Version: V1.0
     **/
    @GetMapping("try")
    public ResponseEntity<?> getTest(@RequestParam(value = "num", defaultValue = "1") Integer num) {
        return new ResponseEntity<>(num, HttpStatus.OK);
    }

}
