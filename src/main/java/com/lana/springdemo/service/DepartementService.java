package com.lana.springdemo.services;


import com.lana.springdemo.entities.Departement;
import com.lana.springdemo.repositories.DepartementRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * Service class for managing a list of departement (Departement).
 */
@Service
public class DepartementService {

    private DepartementRepository departementRepository;

    public DepartementService(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }


    /**
     * FindAllDpts
     * @return
     */
    public List<Departement> findAllDpts() {
        List<Departement> depts = new ArrayList<>();
        if(depts != null){
            departementRepository.findAll().forEach(depts::add);
        }

        return depts;
    }

    /**
     * Search Departement by ID
     * @param id the ID of the departement
     * @return the departement if found, null otherwise
     */
    public Departement findDepartementById(Long id) {
        return departementRepository.findById(id).orElse(null);

    }

    /**
     * Retrieve code departement with id
     * @param departementId
     * @return
     */
    public String getDepartementCode(Long departementId) {
        Optional<Departement> departement = departementRepository.findById(departementId);
        if (departement.isPresent()) {
            return departement.get().getCode();
        } else {
            throw new RuntimeException("Département non trouvé pour l'ID: " + departementId);
        }
    }

    /**
     * Create if unique id and name
     * @param newDepartement
     * @return
     */
    public ResponseEntity<String> addDepartement(Departement newDepartement) {

        List<Departement> depts = this.findAllDpts();

        for (Departement dept : depts) {
            if (dept.getId().equals(newDepartement.getId())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Ce département avec cet ID existe déjà.");
            }

            if (dept.getName().equalsIgnoreCase(newDepartement.getName())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Ce département avec ce nom existe déjà.");
            }
        }

        departementRepository.save(newDepartement);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Département inséré avec succès");
    }

    /**
     * Edit Departement
     * @param departement
     * @return
     */
    public ResponseEntity<String> updateDepartement(Departement departement) {
        Optional<Departement> oldDepartement = departementRepository.findById(departement.getId());
        if (oldDepartement.isPresent()) {
            departementRepository.save(departement);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Département modifié avec succès");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("Ce Département avec cet id n'existe pas.");


    }

    /**
     * Delete Departement by Id
     * @param id
     * @return
     */
    public ResponseEntity<String> deleteDepartement(Long id) {
        Departement departement = findDepartementById(id);
        if(departement != null) {
            departementRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Département supprimé avec succès");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("Ce Département avec cet id n'existe pas.");
    }



}
