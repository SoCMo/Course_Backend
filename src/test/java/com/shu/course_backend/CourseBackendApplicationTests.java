package com.shu.course_backend;

import com.shu.course_backend.dao.ElectionDoMapper;
import com.shu.course_backend.exception.AllException;
import com.shu.course_backend.tool.CourseTool;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
//@RequiredArgsConstructor
class CourseBackendApplicationTests {

//    private final AuthTool authTool;
    @Resource
    ElectionDoMapper electionDoMapper;

    @Test
//    @WithMockUser(username = "18120198", roles = "USER")
    void contextLoads() {
//        System.out.println(authTool.getUserIdentity());
//        System.out.println(StrUtil.semesterConversion("20212"));
//        List<String> test = new ArrayList<>();
//        test.add("周二:3-4");
//        test.add("周二:9-13");
//        test.add("周四:3-4");
//        test.add("周五:7-10");
//        try {
//            String time = CourseTool.translateFromStrToBit(test);
//            System.out.println(time);
//            System.out.println(CourseTool.translateFromBitToStr(time));
//        } catch (AllException e) {
//            System.out.println(e.getMsg());
//        }
        Double point = 0.0;
        Double grade = 88.8;
        point = electionDoMapper.getGradePoint(grade);
        System.out.println(point);
    }

}
