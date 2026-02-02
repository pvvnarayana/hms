package com.example.onlineexam.models;

import java.util.Date;

public class ExamResult {
    private int id;
    private int examId;
    private int studentId;
    private int obtainedMarks;
    private int totalMarks;
    private double percentage;
    private String status; // "PASS" or "FAIL"
    private Date submittedAt;
    private String examTitle;
    private String studentName;

    public ExamResult() {
    }

    public ExamResult(int id, int examId, int studentId, int obtainedMarks, int totalMarks) {
        this.id = id;
        this.examId = examId;
        this.studentId = studentId;
        this.obtainedMarks = obtainedMarks;
        this.totalMarks = totalMarks;
        this.percentage = (double) obtainedMarks / totalMarks * 100;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getObtainedMarks() {
        return obtainedMarks;
    }

    public void setObtainedMarks(int obtainedMarks) {
        this.obtainedMarks = obtainedMarks;
        this.percentage = (double) obtainedMarks / totalMarks * 100;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
        this.percentage = (double) obtainedMarks / totalMarks * 100;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(Date submittedAt) {
        this.submittedAt = submittedAt;
    }

    public String getExamTitle() {
        return examTitle;
    }

    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
