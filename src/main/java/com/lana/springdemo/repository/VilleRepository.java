package com.lana.springdemo.repositories;

import com.lana.springdemo.entities.Ville;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VilleRepository extends CrudRepository<Ville, Long> {

    public Ville findByName(String name);

    List<Ville> findByNameStartingWith(String prefix);



}
