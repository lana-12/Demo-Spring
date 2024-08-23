package com.lana.springdemo.dto;

public class DepartementDto {

    private String departementCode;
    private String departementName;
    private Double totalNbInhabitants;

    public String getDepartementCode() {
        return departementCode;
    }

    public void setDepartementCode(String departementCode) {
        this.departementCode = departementCode;
    }

    public String getDepartementName() {
        return departementName;
    }

    public void setDepartementName(String departementName) {
        this.departementName = departementName;
    }

    public Double getTotalNbInhabitants() {
        return totalNbInhabitants;
    }

    public void setTotalNbInhabitants(Double totalNbInhabitants) {
        this.totalNbInhabitants = totalNbInhabitants;
    }
}
