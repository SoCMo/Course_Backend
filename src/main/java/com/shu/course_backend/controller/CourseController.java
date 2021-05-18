package com.shu.course_backend.controller;

import com.shu.course_backend.model.Result;
import com.shu.course_backend.model.entity.CourseNeedDo;
import com.shu.course_backend.model.response.Info.CourseFilterRes;
import com.shu.course_backend.service.CourseService;
import com.shu.course_backend.tool.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @program: CourseController
* @Description: 课程资源控制层
* @Author: SoCMo
* @Date: 2021/5/18
*/
@RestController
@RequestMapping("/courses")
@CrossOrigin
@Api(tags = "课程资源")
@RequiredArgsConstructor
@Validated
public class CourseController {
    private final CourseService courseService;

    @PreAuthorize("hasAnyRole('STUDENT', 'TEACHER', 'ADMIN')")
    @GetMapping("")
    @ApiOperation(value = "筛选课程")
    Result getCourseList(
            @ApiParam(value = "课程Id")
            @RequestParam(value = "courseId", required = false)
                    Integer courseId,
            @ApiParam(value = "课程名")
            @RequestParam(value = "courseName", required = false)
                    String courseName,
            @ApiParam(value = "学分")
            @RequestParam(value = "credit", required = false)
                    Integer credit,
            @ApiParam(value = "课程时间")
            @RequestParam(value = "courseTime", required = false)
                    List<String> courseTime,
            @ApiParam(value = "教师号")
            @RequestParam(value = "teacherId", required = false)
                    String teacherId,
            @ApiParam(value = "教师姓名")
            @RequestParam(value = "teacherName", required = false)
                    String teacherName,
            @ApiParam(value = "课程未满, false表示不包含该参数")
            @RequestParam(value = "notFull", required = false)
                    Boolean notFull,
            @ApiParam(value = "学期")
            @RequestParam(value = "semester", required = false)
                    String semester){
            CourseNeedDo courseNeedDo = new CourseNeedDo();
            courseNeedDo.setCourseId(courseId);
            courseNeedDo.setCourseName(courseName);
            courseNeedDo.setCredit(credit);
            courseNeedDo.setCourseTime(courseTime);
            courseNeedDo.setSemester(StrUtil.semesterFromStrToInt(semester));
            courseNeedDo.setNotFull(notFull);
            courseNeedDo.setTeacherId(teacherId);
            courseNeedDo.setTeacherName(teacherName);

            return courseService.getCourseList(courseNeedDo);
    }

}
