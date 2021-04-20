package com.shu.course_backend.service.Impl;


import com.shu.course_backend.config.JwtUserDetailsService;
import com.shu.course_backend.dao.DepartmentDoMapper;
import com.shu.course_backend.dao.PasswordDoMapper;
import com.shu.course_backend.dao.UserDoMapper;
import com.shu.course_backend.exception.AllException;
import com.shu.course_backend.exception.EmAllException;
import com.shu.course_backend.model.Result;
import com.shu.course_backend.model.UserRole;
import com.shu.course_backend.model.entity.*;
import com.shu.course_backend.model.request.RegisterRequest;
import com.shu.course_backend.model.response.LoginResponse;
import com.shu.course_backend.service.LoginService;
import com.shu.course_backend.tool.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private UserDoMapper userDOMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Resource
    private JwtUserDetailsService jwtUserDetailsService;

    @Resource
    private DepartmentDoMapper departmentDoMapper;

    @Resource
    private PasswordDoMapper passwordDoMapper;




    /**
     * @Description: 登录接口实现
     * @Param: [username, password]
     * @return: org.springframework.http.ResponseEntity<?>
     * @Author: pongshy
     * @Date: 2021/4/20
     * @Version: V1.0
     **/
    @Override
    public ResponseEntity<?> login(String username, String password) throws Exception {
        authenticate(username, password);
        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);

        UserDo userDo = userDOMapper.selectByPrimaryKey(username);
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setUserId(username);
        response.setUsername(userDo.getName());
        response.setEducation(userDo.getEducation());
        response.setPhone(userDo.getMobilePhone());
        response.setEmail(userDo.getEmail());
        response.setRoles(UserRole.getUserRole(userDo.getIdentity()));
        response.setDepartment(
                departmentDoMapper
                        .selectByPrimaryKey(userDo.getDepartmentId())
                        .getDepartmentName()
        );

        return ResponseEntity.ok(response);
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

    /**
     * @Description: 新用户注册接口实现
     * @Param: [registerRequest]
     * @return: org.springframework.http.ResponseEntity<?>
     * @Author: pongshy
     * @Date: 2021/4/20
     * @Version: V1.0
     **/
    @Override
    public ResponseEntity<?> register(RegisterRequest registerRequest)  {
        UserDo isExisted = userDOMapper.selectByPrimaryKey(registerRequest.getUserId());

        if (isExisted == null) {
            UserDo userDo = new UserDo();
            userDo.setUserId(registerRequest.getUserId());
            userDo.setName(registerRequest.getUsername());
            userDo.setEducation(registerRequest.getEducation());
            userDo.setIdentity("001");
            userDo.setMobilePhone(registerRequest.getPhone());
            userDo.setEmail(registerRequest.getEmail());
            userDo.setDepartmentId(registerRequest.getDepartmentId());
            try {
                if (userDOMapper.insertSelective(userDo) == 1) {
                    // 插入密码
                    PasswordDo passwordDo = new PasswordDo();
                    passwordDo.setUserId(userDo.getUserId());
                    passwordDo.setPassword(new BCryptPasswordEncoder().encode(registerRequest.getPassword()));
                    passwordDoMapper.insertSelective(passwordDo);

                    return ResponseEntity.ok(new Result(
                            HttpStatus.OK,
                            "注册成功"
                    ));
                } else {
                    throw new AllException(EmAllException.DATABASE_ERROR, "用户注册出错");
                }
            } catch (AllException ex) {
                return new ResponseEntity<>(new Result(ex), HttpStatus.OK);
            }
        } else {
            String msg = "该学号/工号已有用户注册";
            return new ResponseEntity<>(new Result(HttpStatus.BAD_REQUEST, msg), HttpStatus.OK);
        }
    }
}
