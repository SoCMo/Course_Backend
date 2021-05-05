package com.shu.course_backend.model.entity;

import java.util.Date;

public class ApplicationDo {
    private Integer id;

    private String teacherId;

    private Integer courseId;

    private Integer status;

    private Date createTime;

    private String semseter;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId == null ? null : teacherId.trim();
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSemseter() {
        return semseter;
    }

    public void setSemseter(String semseter) {
        this.semseter = semseter == null ? null : semseter.trim();
    }
}