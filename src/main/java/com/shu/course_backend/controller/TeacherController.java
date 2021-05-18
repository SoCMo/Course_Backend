package com.shu.course_backend.controller;

import com.shu.course_backend.model.Result;
import com.shu.course_backend.model.request.ApplyRequest;
import com.shu.course_backend.model.request.GradeRequest;
import com.shu.course_backend.model.request.SemesterRequest;
import com.shu.course_backend.service.TeacherService;
import com.shu.course_backend.tool.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

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
//    @ApiImplicitParam(name = "courseId", value = "课程号", required = true)
    @PostMapping("/apply")
    public Result applyTheCourse(@RequestBody @Validated ApplyRequest applyRequest,
                                 HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String userid = jwtTokenUtil.getUsernameFromTokenAfterSub(token);

        return teacherService.applyTheCourseByTeacher(
                applyRequest.getCourseId(),
                applyRequest.getCourseTimeId(),
                userid);
    }

    @PreAuthorize("hasRole('TEACHER')")
    @ApiOperation(value = "教师获取本学期所有课程")
    @GetMapping("/courses")
    public Result getCourses(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String teacherid = jwtTokenUtil.getUsernameFromTokenAfterSub(token);

        return teacherService.getCourses(teacherid);
    }

    @PreAuthorize("hasRole('TEACHER')")
    @ApiOperation(value = "获取指定学期教师开课情况")
    @PostMapping("/courses")
    public Result getSemesterCourses(@RequestBody @Validated SemesterRequest semesterRequest,
                                     HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String teacherid = jwtTokenUtil.getUsernameFromTokenAfterSub(token);
        return teacherService.getSemesterCourses(semesterRequest.getSemester(), teacherid);
    }

    @PreAuthorize("hasRole('TEACHER')")
    @ApiOperation(value = "查看具体一门课的所有学生的成绩")
    @ApiImplicitParam(name = "courseId", value = "课程号", required = true, paramType = "path")
    @GetMapping("/getGrades/{courseId}")
    public Result getAllGrades(@PathVariable("courseId") Integer courseId) {
        return teacherService.getAllGrades(courseId);
    }

    @PreAuthorize("hasRole('TEACHER')")
    @ApiOperation(value = "输入成绩")
    @PatchMapping("/enterGrade")
    public Result enterStudentGrades(@RequestBody @Validated GradeRequest gradeRequest) {
        return teacherService.enterStudentGrades(gradeRequest.getCourseId(), gradeRequest.getGradeList());
    }






}
