package com.lana.springdemo.services;


import com.lana.springdemo.entities.Ville;
import java.util.Optional;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
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


    private VilleRepository villeRepository;

    public VilleService(VilleRepository villeRepository) {
        this.villeRepository = villeRepository;
    }

    /**
     * FindAllVilles
     * @return
     */
    public List<Ville> findAllVilles() {
        List<Ville> villes = new ArrayList<>();
        villeRepository.findAll().forEach(villes::add);

        return villes;
    }
    /**
     * Search Ville by ID
     * @param id the ID of the Ville
     * @return the Ville if found, null otherwise
     */
    public Ville findVilleById(Long id) {
        return villeRepository.findById(id).orElse(null);

    }


    /**
     * Search Ville by name
     * @param name
     * @return
     */
    public Ville findVilleByName(String name) {
        return villeRepository.findByName(name);
    }

    /**
     * Create if unique id and name
     * @param newVille
     * @return
     */
    public ResponseEntity<String> addVille(Ville newVille) {

        List<Ville> villes = this.findAllVilles();

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

//        villes.add(newVille);
        villeRepository.save(newVille);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ville insérée avec succès");
    }


    /**
     * Edit ville
     * @param ville
     * @return
     */
    public ResponseEntity<String> updateVille(Ville ville) {
        Optional<Ville> oldville = villeRepository.findById(ville.getId());
        if (oldville.isPresent()) {
            villeRepository.save(ville);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Ville modifiée avec succès");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("La ville avec cet id n'existe pas.");

        //TP4-5
//        for (Ville ville : villes) {
//            if (ville.getId().equals(ville.getId())) {
//
//                ville.setName(ville.getName());
//                ville.setNbHabitants(ville.getNbHabitants());
//                villes.add(ville);
//                return ResponseEntity.status(HttpStatus.CONFLICT)
//                        .body("Ville modifiée avec succès");
//            }
//        }
//        return ResponseEntity.status(HttpStatus.CONFLICT)
//                .body("La ville avec cet id n'existe pas.");
    }



    /**
     * Delete ville by Id
     * @param id
     * @return
     */
    public ResponseEntity<String> deleteVille(Long id) {
        Ville ville = findVilleById(id);
        if(ville != null) {
            villeRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Ville supprimée avec succès");
        }

        //Avec la List
//        for (Ville ville : villes) {
//            if (ville.getId().equals(id)) {
//                villes.remove(ville);
//                return ResponseEntity.status(HttpStatus.CONFLICT)
//                        .body("Ville supprimée avec succès");
//            }
//        }
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("La ville avec cet id n'existe pas.");
    }



    /**
     * Crud pour les TP4-5 sans BDD
     */
    //private List<Ville> villes = new ArrayList<>();
    /**
     * Constructor with instance of the ville
     */

//    public VilleService() {
//        villes.add(new Ville(1L,"Paris", 2148000));
//        villes.add(new Ville(2L,"Lyon", 513000));
//        villes.add(new Ville(3L,"Marseille", 861635));
//        villes.add(new Ville(4L,"Montpellier", 360965));
//        villes.add(new Ville(5L,"Carcassonne", 125020));
//        villes.add(new Ville(6L,"Nice", 125000));
//    }
}
