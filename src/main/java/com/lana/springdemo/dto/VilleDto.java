package com.lana.springdemo.dto;

public class VilleDto {

    private String VilleName;
    private double VilleNbHabitants;
    private String departementCode;
    private String departementName;


    public String getVilleName() {
        return VilleName;
    }

    public void setVilleName(String villeName) {
        VilleName = villeName;
    }

    public String getDepartementCode() {
        return departementCode;
    }

    public void setDepartementCode(String departementCode) {
        this.departementCode = departementCode;
    }

    public double getVilleNbHabitants() {
        return VilleNbHabitants;
    }

    public void setVilleNbHabitants(double villeNbHabitants) {
        VilleNbHabitants = villeNbHabitants;
    }

    public String getDepartementName() {
        return departementName;
    }

    public void setDepartementName(String departementName) {
        this.departementName = departementName;
    }
}
