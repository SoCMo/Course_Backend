package com.shu.course_backend;

import com.shu.course_backend.model.UserRole;
import com.shu.course_backend.tool.AuthTool;
import com.shu.course_backend.tool.StrUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
//@RequiredArgsConstructor
class CourseBackendApplicationTests {

//    private final AuthTool authTool;

    @Test
//    @WithMockUser(username = "18120198", roles = "USER")
    void contextLoads() {
//        System.out.println(authTool.getUserIdentity());
        System.out.println(StrUtil.semesterConversion("20212"));
   }

}
