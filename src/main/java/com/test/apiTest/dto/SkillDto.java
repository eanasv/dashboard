package com.test.apiTest.dto;

public class SkillDto {
    private String name;
    private int score;
    private String achievedStatus;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getAchievedStatus() {
        return achievedStatus;
    }

    public void setAchievedStatus(String achievedStatus) {
        this.achievedStatus = achievedStatus;
    }
}
