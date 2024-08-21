package com.lana.springdemo.services;


import com.lana.springdemo.entities.Ville;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;


/**
 * Service class for managing a list of cities (Villes).
 */
@Service
public class VilleService {

    private List<Ville> villes = new ArrayList<>();


    /**
     * Constructor with instance of the ville
     */
    public VilleService() {
        villes.add(new Ville(1L,"Paris", 2148000));
        villes.add(new Ville(2L,"Lyon", 513000));
        villes.add(new Ville(3L,"Marseille", 861635));
        villes.add(new Ville(4L,"Montpellier", 360965));
        villes.add(new Ville(5L,"Carcassonne", 125020));
        villes.add(new Ville(6L,"Nice", 125000));
    }
//    {
//        "id": 7,
//            "name": "Candillargues",
//            "nbHabitants": 2560.0
//    }

    /**
     * FindAllVilles
     * @return
     */
    public List<Ville> findAllVilles() {
        return villes;
    }


    /**
     * Search Ville by name
     * @param name
     * @return
     */
    public Ville findVilleByName(String name) {
        for (Ville ville : villes) {
            if (ville.getName().equalsIgnoreCase(name)) {

                return ville;
            }
        }
        return null;
    }

    /**
     * Search Ville by ID
     * @param id the ID of the Ville
     * @return the Ville if found, null otherwise
     */
    public Ville findVilleById(Long id) {
        for (Ville ville : villes) {
            if (ville.getId().equals(id)) {
                return ville;
            }
        }
        return null;
    }


    /**
     * Create if unique id and name
     * @param newVille
     * @return
     */
    public ResponseEntity<String> addVille(Ville newVille) {

        for (Ville ville : villes) {
            if (ville.getId().equals(newVille.getId())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Une ville avec cet ID existe déjà.");
            }

            if (ville.getName().equalsIgnoreCase(newVille.getName())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Une ville avec ce nom existe déjà.");
            }
        }

        villes.add(newVille);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ville insérée avec succès");
    }

    /**
     * Edit ville
     * @param newVille
     * @return
     */
    public ResponseEntity<String> updateVille(Ville newVille) {
        for (Ville ville : villes) {
            if (ville.getId().equals(newVille.getId())) {

                ville.setName(newVille.getName());
                ville.setNbHabitants(newVille.getNbHabitants());
                villes.add(newVille);
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Ville modifiée avec succès");
            }
        }
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("La ville avec cet id n'existe pas.");
    }

    /**
     * Delete ville by Id
     * @param id
     * @return
     */
    public ResponseEntity<String> deleteVille(Long id) {
        for (Ville ville : villes) {
            if (ville.getId().equals(id)) {
                villes.remove(ville);
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Ville supprimée avec succès");
            }
        }
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("La ville avec cet id n'existe pas.");
    }
}
