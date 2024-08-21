package com.lana.springdemo.services;

import com.lana.springdemo.entities.Departement;
import org.springframework.data.repository.CrudRepository;

public interface DepartementRepository extends CrudRepository<Departement, Long> {



}
