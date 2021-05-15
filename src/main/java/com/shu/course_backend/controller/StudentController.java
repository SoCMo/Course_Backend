package com.shu.course_backend.controller;

import com.shu.course_backend.exception.AllException;
import com.shu.course_backend.exception.EmAllException;
import com.shu.course_backend.model.Result;
import com.shu.course_backend.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotBlank;
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

    @GetMapping("/this/semester/{semester}/course")
    @ApiOperation(value = "获取学生选课列表")
    Result courseList(@ApiParam(value = "学期", required = true)
                      @Pattern(regexp = "^\\d{4}[0-3]$", message = "学期格式错误")
                      @PathVariable("semester") String semester) {
        return studentService.selectionList(semester);
    }

    @PutMapping("/this/semester/this/course/")
    @ApiOperation(value = "选课")
    @ApiImplicitParam(name = "openId", value = "请求体:开课Id", required = true, dataType = "int")
    Result selectCourse(@RequestBody @ApiIgnore Map<String, Object> paramsMap){
        return studentService.selectCourse(Integer.parseInt(paramsMap.get("openId").toString()));
    }
}