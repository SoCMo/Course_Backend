package com.shu.course_backend.controller;

import com.shu.course_backend.model.Result;
import com.shu.course_backend.service.TeacherService;
import com.shu.course_backend.tool.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: TeacherController
 * @Description: 教师模块
 * @Author: pongshy
 * @Date: 2021/5/5 22:05
 **/
@RestController
@CrossOrigin
@RequestMapping("teacher")
@Api(tags = "教师模块")
public class TeacherController {


    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Resource
    private TeacherService teacherService;

    @PreAuthorize("hasRole('TEACHER')")
    @ApiOperation(value = "教师申请课程")
    @ApiImplicitParam(name = "courseId", value = "课程号", required = true)
    @GetMapping("/apply/{courseId}")
    public Result applyTheCourse(@PathVariable(value = "courseId") Integer courseId,
                                 HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String userid = jwtTokenUtil.getUsernameFromTokenAfterSub(token);

        return teacherService.applyTheCourseByTeacher(courseId, userid);
    }




}
