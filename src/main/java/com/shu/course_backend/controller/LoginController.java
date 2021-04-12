package com.shu.course_backend.controller;

import com.pongshy.model.request.JwtRequest;
import com.pongshy.service.LoginService;
import com.pongshy.tool.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: LoginController
 * @Description: TODO
 * @Author: pongshy
 * @Date: 2021/1/8-10:37
 * @Version: V1.0
 **/
@RestController
@CrossOrigin
public class LoginController {


    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<?> loginByUsernameAndPassword(@RequestBody JwtRequest jwtRequest) throws Exception {
        return loginService.login(jwtRequest.getUsername(), jwtRequest.getPassword());
    }

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public ResponseEntity<?> test1(@RequestParam(value = "word") String word,
                                   HttpServletRequest request) {
        String jwtToken = request.getHeader("Authorization");
        return loginService.testGetMsgFromToken(jwtToken.substring(7));
    }


}
