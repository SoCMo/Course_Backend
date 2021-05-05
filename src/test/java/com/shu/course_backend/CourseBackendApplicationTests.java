package com.shu.course_backend;

import com.auth0.jwt.algorithms.Algorithm;
import com.shu.course_backend.model.UserRole;
import com.shu.course_backend.tool.AuthTool;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RequiredArgsConstructor
class CourseBackendApplicationTests {

    private final AuthTool authTool;

    @Test
    @WithMockUser(username = "18120198", roles = "USER")
    void contextLoads() {

        System.out.println(authTool.getUserIdentity());

   }

}
