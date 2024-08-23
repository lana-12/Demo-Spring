package com.lana.springdemo.service;


import com.lana.springdemo.entity.Departement;
import com.lana.springdemo.entity.Ville;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

import com.lana.springdemo.exception.SpringDemoException;
import com.lana.springdemo.repository.DepartementRepository;
import com.lana.springdemo.repository.VilleRepository;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Service class for managing a list of cities (Ville).
 */
@Service
public class VilleService {

    @Autowired
    private VilleRepository villeRepository;

    @Autowired
    private DepartementRepository departementRepository;

    @Value("${csv.file.path}")
    private String csvFilePath;

    public VilleService(VilleRepository villeRepository, DepartementRepository departementRepository) {
        this.villeRepository = villeRepository;
        this.departementRepository = departementRepository;
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
        Ville result = null;
        try {
            result = villeRepository.findById(id).get();

        } catch(NoResultException nre){
        }
        return result;

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
     * Search ville startingWith/letter(s)
     * @param prefix
     * @return
     */
    public List<Ville> findVillesByNameStartingWith(String prefix) {

        if (prefix != null) {
            return villeRepository.findByNameStartingWith(prefix);

        }
        return null;
    }


    /**
     * Search ville with population > param
     * @param minPopulation
     * @return
     */
    public List<Ville> findVillesByMinPopulation(int minPopulation) {
        return villeRepository.findByNbHabitantsGreaterThan(minPopulation);
    }

    /**
     * Retrieves the list of cities whose population is between a minimum and maximum value
     * Search ville with
     * @param minPopulation
     * @return
     */
    public List<Ville> findVillesByMinPopulationandByMawPopulation(int minPopulation, int maxPopulation) {
        return villeRepository.findByNbHabitantsGreaterThanAndNbHabitantsLessThan(minPopulation, maxPopulation);
    }


    /**
     * Retrieves all cities in a department where the population is greater than a minimum value
     * @param departementId
     * @param minPopulation
     * @return
     */
    public List<Ville> findVillesByDepartementAndMinPopulation(Long departementId, int minPopulation) {
        return villeRepository.findByDepartementIdAndNbHabitantsGreaterThan(departementId, minPopulation);
    }


    /**
     * Retrieves all cities in a department where the population is greater than a minimum value and less than a maximum value
     * @param departementId
     * @param minPopulation
     * @param maxPopulation
     * @return
     */
    public List<Ville> findVillesByDepartementAndPopulationRange(Long departementId, int minPopulation, int maxPopulation) {
        return villeRepository.findByDepartementIdAndNbHabitantsGreaterThanAndNbHabitantsLessThan(departementId, minPopulation, maxPopulation);
    }



    public List<Ville> findByDepartementCode(String codeDepartement) {
        return villeRepository.findByDepartementCode(codeDepartement);
    }

//
//    @Async
//    public void exportToCsvOnDisk() {
//    }

    public void loadCsvFromDisk() {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            br.readLine(); // Lire la première ligne d'en-tête et l'ignorer

            while ((line = br.readLine()) != null) {
                String[] columns = line.split(","); // Diviser la ligne en colonnes

                // Récupérer les données nécessaires pour créer un objet Ville
                String nameVille = columns[3]; // label
                double nbHabitants = 0.0; // Utiliser 0 ou une autre valeur par défaut pour le nombre d'habitants
                String codeDepartement = columns[7]; // department_number
                String nomDepartement = columns[6]; // department_name

                // Trouver ou créer un objet Departement
                Departement departement = departementRepository.findByCode(codeDepartement);
                if (departement == null) {
                    departement = new Departement(nomDepartement, codeDepartement);
                    departementRepository.save(departement);
                }

                // Créer une nouvelle ville
                Ville ville = new Ville();
                ville.setName(nameVille);
                ville.setNbHabitants(nbHabitants);
                ville.setDepartement(departement);

                // Sauvegarder la ville dans la base de données
                villeRepository.save(ville);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Create if unique id and name
     * @param newVille
     * @return
     */
    public ResponseEntity<String> addVille(Ville newVille) {
        // Verif departement is null
        if (newVille.getDepartement() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Le département est requis.");
        }

        // Verif departement isEmpty
        if (newVille.getDepartement().getName() == null || newVille.getDepartement().getName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Le nom du département est requis.");
        }

        // Verif ville isEmpty
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

        // Search  département by name
        Optional<Departement> departement = departementRepository.findByName(newVille.getDepartement().getName());

        if (departement.isPresent()) {
            newVille.setDepartement(departement.get());
        } else {

            Departement newDepartement = new Departement();
            newDepartement.setName(newVille.getDepartement().getName());
            newDepartement.setCode(newVille.getDepartement().getCode());

            departementRepository.save(newDepartement);

            newVille.setDepartement(newDepartement);
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
