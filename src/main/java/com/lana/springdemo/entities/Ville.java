package com.lana.springdemo.entities;

import org.springframework.stereotype.Service;

@Service
public class Ville {

    private Long id;
    private String name;
    private double nbHabitants;

    public Ville() {}

    public Ville(Long id, String name, double nbHabitants) {
        this.id = id;
        this.name = name;
        this.nbHabitants = nbHabitants;
    }



    //Getters and Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        return "VilleService {" + '\n' +
                "name= " + name + '\n' +
                ", nbHabitants=" + nbHabitants + '\n' +
                '}';
    }
}
