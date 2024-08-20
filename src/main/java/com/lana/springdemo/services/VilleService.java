package com.lana.springdemo.services;


import com.lana.springdemo.entities.Ville;
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
        villes.add(new Ville("Paris", 2148000));
        villes.add(new Ville("Lyon", 513000));
        villes.add(new Ville("Marseille", 861635));
        villes.add(new Ville("Montpellier", 360965));
        villes.add(new Ville("Carcassonne", 125020));
        villes.add(new Ville("Nice", 125000));
    }


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
     * Verif if if isset(!ville.getname) and Add newVille in the list
     * @param newVille
     * @return
     */
    public ResponseEntity<String> addVille(Ville newVille) {

        for (Ville ville : villes) {
            if (ville.getName().equalsIgnoreCase(newVille.getName())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("La ville existe déjà");
            }
        }

        villes.add(newVille);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ville insérée avec succès");
    }

}
