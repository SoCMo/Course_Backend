package com.shu.course_backend.model.entity;

public class OpenDo {
    private Integer openId;

    private Integer courseId;

    private String semester;

    private String address;

    private String answerAddress;

    private String answerTime;

    private byte[] courseTime;

    public Integer getOpenId() {
        return openId;
    }

    public void setOpenId(Integer openId) {
        this.openId = openId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester == null ? null : semester.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getAnswerAddress() {
        return answerAddress;
    }

    public void setAnswerAddress(String answerAddress) {
        this.answerAddress = answerAddress == null ? null : answerAddress.trim();
    }

    public String getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(String answerTime) {
        this.answerTime = answerTime == null ? null : answerTime.trim();
    }

    public byte[] getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(byte[] courseTime) {
        this.courseTime = courseTime;
    }
}