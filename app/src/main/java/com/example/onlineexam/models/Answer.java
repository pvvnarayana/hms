package com.example.onlineexam.models;

public class Answer {
    private int id;
    private int questionId;
    private int examId;
    private int studentId;
    private String selectedAnswer; // "A", "B", "C", or "D"
    private boolean isCorrect;

    public Answer() {
    }

    public Answer(int questionId, int examId, int studentId, String selectedAnswer) {
        this.questionId = questionId;
        this.examId = examId;
        this.studentId = studentId;
        this.selectedAnswer = selectedAnswer;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
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

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
