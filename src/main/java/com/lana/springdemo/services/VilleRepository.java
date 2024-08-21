package com.lana.springdemo.services;

import com.lana.springdemo.entities.Ville;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface VilleRepository extends CrudRepository<Ville, Long> {

    public Ville findByName(String name);

}
