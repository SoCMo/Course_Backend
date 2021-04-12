package com.shu.course_backend.service.Impl;

import com.pongshy.config.JwtUserDetailsService;
import com.pongshy.dao.UserDOMapper;
import com.pongshy.model.response.JwtResponse;
import com.pongshy.service.LoginService;
import com.pongshy.tool.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @ClassName: LoginServiceImpl
 * @Description: 登录接口实现类
 * @Author: pongshy
 * @Date: 2021/1/8-10:38
 * @Version: V1.0
 **/
@Service
public class LoginServiceImpl implements LoginService {


    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private UserDOMapper userDOMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Resource
    private JwtUserDetailsService jwtUserDetailsService;


    @Override
    public ResponseEntity<?> login(String username, String password) throws Exception {
        authenticate(username, password);
        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        try{
            // 采用默认的认证，会自动调用重写的UserDetailsService中的loadUserByName()方法
            // 获取用户信息进行认证
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @Override
    public ResponseEntity<?> testGetMsgFromToken(String token) {
        String username = jwtTokenUtil.getUsernameFromToken(token);
        return ResponseEntity.ok(username);
    }
}
