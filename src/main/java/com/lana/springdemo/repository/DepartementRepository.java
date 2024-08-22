package com.lana.springdemo.repositories;

import com.lana.springdemo.entities.Departement;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DepartementRepository extends CrudRepository<Departement, Long> {

    public Departement findByName(String name);
    Optional<Departement> findByCode(String code);



}
