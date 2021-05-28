package com.example.thirdproject.model;

public class TeamModel {
    private String teamImage, teamName, teamDesc;

    public TeamModel(String teamImage, String teamName, String teamDesc) {
        this.teamImage = teamImage;
        this.teamName = teamName;
        this.teamDesc = teamDesc;
    }

    public String getTeamImage() {
        return teamImage;
    }

    public void setTeamImage(String teamImage) {
        this.teamImage = teamImage;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamDesc() {
        return teamDesc;
    }

    public void setTeamDesc(String teamDesc) {
        this.teamDesc = teamDesc;
    }
}