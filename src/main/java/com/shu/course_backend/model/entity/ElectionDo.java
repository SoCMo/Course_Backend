package com.shu.course_backend.model.entity;

public class ElectionDo {
    private Integer id;

    private String studentId;

    private Integer openId;

    private Double grade;

    private Integer usual;

    private Integer examination;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public Integer getOpenId() {
        return openId;
    }

    public void setOpenId(Integer openId) {
        this.openId = openId;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public Integer getUsual() {
        return usual;
    }

    public void setUsual(Integer usual) {
        this.usual = usual;
    }

    public Integer getExamination() {
        return examination;
    }

    public void setExamination(Integer examination) {
        this.examination = examination;
    }
}