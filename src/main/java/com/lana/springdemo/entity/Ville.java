package com.lana.springdemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="ville")
public class Ville {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false )
    private String name;

    @Column(name="nb_habitants", nullable = false )
    private double nbHabitants;

    @ManyToOne
    @JoinColumn(name="dept_id")
    private Departement departement;

    public Ville() {}

    public Ville(Long id, String name, double nbHabitants, Departement departement ) {
        this.id = id;
        this.name = name;
        this.nbHabitants = nbHabitants;
        this.departement = departement;
    }

    public Ville(String name, double nbHabitants) {

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

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    @Override
    public String toString() {
        return "VilleService {" + '\n' +
                "name= " + name + '\n' +
                ", nbHabitants=" + nbHabitants + '\n' +
                '}';
    }
}
