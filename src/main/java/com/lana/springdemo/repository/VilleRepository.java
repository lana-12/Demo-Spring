package com.lana.springdemo.repository;

import com.lana.springdemo.entity.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface VilleRepository extends JpaRepository<Ville, Long> {

    public Ville findByName(String name);

    List<Ville> findByNameStartingWith(String prefix);
    List<Ville> findByNbHabitantsGreaterThan(int minNbHabitants);

    List<Ville> findByNbHabitantsGreaterThanAndNbHabitantsLessThan(int minNbHabitants, int maxNbHabitants);

    List<Ville> findByDepartementIdAndNbHabitantsGreaterThan(Long departementId, int minPopulation);

    List<Ville> findByDepartementIdAndNbHabitantsGreaterThanAndNbHabitantsLessThan(Long departementId, int minPopulation, int maxPopulation);

    List<Ville> findByDepartementCode(String codeDepartement);

    @Query("SELECT v FROM Ville v WHERE v.departement.id = :departementId ORDER BY v.nbHabitants DESC")
    List<Ville> findTopNVillesByDepartementId(@Param("departementId") Long departementId, Pageable pageable);


    List<Ville> findByDepartement_CodeAndNbHabitantsBetween(String codeDepartement, int minPopulation, int maxPopulation);

}
