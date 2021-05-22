package com.shu.course_backend.controller;

import com.shu.course_backend.model.Result;
import com.shu.course_backend.service.StudentService;
import com.shu.course_backend.tool.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.Pattern;
import java.util.Map;

/**
 * program: StudentController
 * description: 学生资源控制器
 * author: SoCMo
 * create: 2021/5/15 12:44
 */
@RestController
@RequestMapping("/student")
@CrossOrigin
@Api(tags = "学生资源")
@RequiredArgsConstructor
@Validated
public class StudentController {
    private final StudentService studentService;

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/this/semester/{semester}/course")
    @ApiOperation(value = "获取学生选课列表")
    Result courseList(@ApiParam(value = "学期", required = true)
                      @Pattern(regexp = "^\\d{4}学年[春夏秋冬]季学期$", message = "学期格式错误")
                      @PathVariable("semester") String semester) {
        semester = StrUtil.semesterFromStrToInt(semester);
        return studentService.selectionList(semester);
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PutMapping("/this/semester/this/course/")
    @ApiOperation(value = "选课")
    @ApiImplicitParam(name = "openId", value = "请求体:开课Id", required = true, dataType = "int")
    Result selectCourse(@RequestBody @ApiIgnore Map<String, Object> paramsMap) {
        return studentService.selectCourse(Integer.parseInt(paramsMap.get("openId").toString()));
    }

    @PreAuthorize("hasRole('STUDENT')")
    @DeleteMapping("/this/semester/this/course/")
    @ApiOperation(value = "退课")
    @ApiImplicitParam(name = "openId", value = "请求体:开课Id", required = true, dataType = "int")
    Result quiteCourse(@RequestBody @ApiIgnore Map<String, Object> paramsMap) {
        return studentService.quiteCourse(Integer.parseInt(paramsMap.get("openId").toString()));
    }
}
