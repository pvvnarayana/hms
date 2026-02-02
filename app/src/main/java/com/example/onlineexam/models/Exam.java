package com.example.onlineexam.models;

import java.util.Date;

public class Exam {
    private int id;
    private String title;
    private String description;
    private int duration; // in minutes
    private int totalMarks;
    private int passingMarks;
    private Date startTime;
    private Date endTime;
    private String status; // "UPCOMING", "ACTIVE", "COMPLETED"
    private int totalQuestions;

    public Exam() {
    }

    public Exam(int id, String title, String description, int duration, int totalMarks, int passingMarks) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.totalMarks = totalMarks;
        this.passingMarks = passingMarks;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }

    public int getPassingMarks() {
        return passingMarks;
    }

    public void setPassingMarks(int passingMarks) {
        this.passingMarks = passingMarks;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }
}
