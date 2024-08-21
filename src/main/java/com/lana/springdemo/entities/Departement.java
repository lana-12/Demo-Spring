package com.lana.springdemo.entities;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="departement")
public class Departement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false )
    private String name;

    @Column(name="code", nullable = false )
    private String code;

    @OneToMany(mappedBy = "departement")
    private Set<Ville> villes;

    {
        villes = new HashSet<Ville>();
    }

    public Departement() {}

    public Departement(String name, String code) {
        this.name = name;
        this.code = code;
    }

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    @Override
    public String toString() {
        return "Departement{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
