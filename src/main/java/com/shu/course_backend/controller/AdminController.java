package com.shu.course_backend.controller;

import com.shu.course_backend.model.Result;
import com.shu.course_backend.model.request.CourseRequest;
import com.shu.course_backend.model.request.CourseTimeModifyRequest;
import com.shu.course_backend.model.request.CourseTimeRequest;
import com.shu.course_backend.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.DeleteProvider;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName: AdminController
 * @Description: 管理员模块
 * @Author: pongshy
 * @Date: 2021/4/25-18:30
 * @Version: V1.0
 **/
@RestController
@RequestMapping("/admin")
@CrossOrigin
@Api(tags = "管理员模块")
public class AdminController {
    @Resource
    private AdminService adminService;

    @PostMapping("/create")
    @ApiOperation(value = "创建课程")
    public Result createCourse(@RequestBody CourseRequest courseRequest) {
        return adminService.createCourse(courseRequest);
    }

    @PostMapping("/createTime")
    @ApiOperation(value = "创建一个课程的上课时间与地点等信息")
    public Result createCourseTime(@RequestBody @Validated CourseTimeRequest courseTimeRequest) {
        return adminService.addCourseTime(courseTimeRequest);
    }

    @PutMapping("/modify")
    @ApiOperation(value = "修改一门课程除了上课时间与地点外的具体信息")
    public Result modifyCourse(@RequestBody @Validated CourseTimeModifyRequest courseTimeModifyRequest) {
        return adminService.modifyCourseTime(courseTimeModifyRequest);
    }

    @GetMapping("/getCourses")
    @ApiOperation(value = "获取所有课程")
    public Result getAllCourses() {
        return adminService.getAllCourses();
    }

    @DeleteMapping("/course/{id}")
    @ApiOperation(value = "删除一门指定课程")
    public Result deleteCourse(@PathVariable("id") Integer courseId) {
        return adminService.deleteOneCourse(courseId);
    }

    @DeleteMapping("/coursetime/{id}")
    @ApiOperation(value = "删除一门课程一个指定上课时间等数据")
    public Result deleteCourseTime(@PathVariable("id") Integer id) {
        return adminService.deleteCourseTime(id);
    }

}
