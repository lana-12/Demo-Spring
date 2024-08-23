package com.lana.springdemo.repository;

import com.lana.springdemo.entity.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DepartementRepository extends JpaRepository<Departement, Long> {

    Optional<Departement> findByName(String name);
    public Departement findByCode(String code);



}
