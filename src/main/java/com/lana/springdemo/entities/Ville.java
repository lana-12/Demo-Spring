package com.lana.springdemo.entities;

import org.springframework.stereotype.Service;

@Service
public class Ville {

    private String name;
    private double nbHabitants;

    public Ville() {}

    public Ville(String name, double nbHabitants) {
        this.name = name;
        this.nbHabitants = nbHabitants;
    }



    //Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getNbHabitants() {
        return nbHabitants;
    }

    public void setNbHabitants(double nbHabitants) {
        this.nbHabitants = nbHabitants;
    }


    @Override
    public String toString() {
        return "VilleService{" +
                "name='" + name + '\'' +
                ", nbHabitants=" + nbHabitants +
                '}';
    }
}
