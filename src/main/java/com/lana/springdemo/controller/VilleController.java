package com.lana.springdemo.controller;

import com.lana.springdemo.dto.VilleDto;
import com.lana.springdemo.entity.Ville;
import com.lana.springdemo.service.VilleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/villes")
public class VilleController {

    @Autowired
    private final VilleService villeService;

    @Autowired
    private ModelMapper modelMapper;

    public VilleController(VilleService villeService) {
        this.villeService = villeService;
    }

//    //BDD=>ok
//    @GetMapping
//    public List<Ville> getVilles() {
//        return villeService.findAllVilles();
//    }

    @GetMapping
    public List<VilleDto> getAllTowns(){
        return StreamSupport.stream(villeService.findAllVilles().spliterator(),true).map(ville -> modelMapper.map(ville,VilleDto.class)).toList();
    }

    //BDD=>ok
    @GetMapping(path = "{name}")
    public ResponseEntity<String> getVilleByName(@PathVariable String name) {
        Ville ville = villeService.findVilleByName(name);
        if (ville != null) {
            return ResponseEntity.ok(ville.toString());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("La ville '" + name + "' n'existe pas");
        }
    }


    @GetMapping("/search")
    public ResponseEntity<?> findVillesByPrefix(@RequestParam String prefix) {
        List<Ville> villes = villeService.findVillesByNameStartingWith(prefix);
        if (villes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Aucune ville trouvée pour le préfixe : " + prefix);
        } else {
            return ResponseEntity.ok(villes);
        }
    }

    @GetMapping("/population/superieur")
    public ResponseEntity<List<Ville>> getVillesByMinPopulation(@RequestParam int minPopulation) {
        List<Ville> villes = villeService.findVillesByMinPopulation(minPopulation);
        return ResponseEntity.ok(villes);
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<Ville> getVilleById(@PathVariable Long id) {
        Ville ville = villeService.findVilleById(id);
        if (ville != null) {
            return ResponseEntity.ok(ville);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }


    @GetMapping("/population/range/{minPopulation}/{maxPopulation}")
    public ResponseEntity<List<Ville>> getVillesByPopulationRange(@PathVariable int minPopulation, @PathVariable int maxPopulation) {
        List<Ville> villes = villeService.findVillesByMinPopulationandByMawPopulation(minPopulation, maxPopulation);
        return ResponseEntity.ok(villes);
    }

    @GetMapping("/departement/{departementId}/population/greater-than/{minPopulation}")
    public ResponseEntity<List<Ville>> getVillesByDepartementAndMinPopulation(@PathVariable Long departementId, @PathVariable int minPopulation) {
        List<Ville> villes = villeService.findVillesByDepartementAndMinPopulation(departementId, minPopulation);
        return ResponseEntity.ok(villes);
    }


    @GetMapping("/departement/{departementId}/population/range/{minPopulation}/{maxPopulation}")
    public ResponseEntity<List<Ville>> getVillesByDepartementAndPopulationRange(@PathVariable Long departementId, @PathVariable int minPopulation, @PathVariable int maxPopulation) {
        List<Ville> villes = villeService.findVillesByDepartementAndPopulationRange(departementId, minPopulation, maxPopulation);
        return ResponseEntity.ok(villes);
    }


//    @GetMapping("/departement/{departementId}/top/{n}/population")
//    public ResponseEntity<List<Ville>> getTopNVillesByDepartement(@PathVariable Long departementId, @PathVariable int n) {
//        List<Ville> villes = villeService.findTopNVillesByDepartement(departementId, n);
//        return ResponseEntity.ok(villes);
//    }

    @GetMapping("/departement/{codeDepartement}")
    public ResponseEntity<List<Ville>> getByDepartementCode(@PathVariable String codeDepartement) {
        List<Ville> villes = villeService.findByDepartementCode(codeDepartement);
        return ResponseEntity.ok(villes);
    }


    @GetMapping("/villes/csv")
    public String getVillesCsv() {
        // Charger les données depuis le fichier CSV
       //villeService.loadCsvFromDisk();

        // Récupérer toutes les villes depuis le repository
        List<Ville> villes = villeService.findAllVilles();

        // Afficher les villes dans la console
        for (Ville ville : villes) {
            System.out.println("Ville: " + ville.getName() + ", Habitants: " + ville.getNbHabitants() + ", Département: " + ville.getDepartement().getName());
        }

        // Retourner une réponse simple pour confirmer que l'action a été effectuée
        return "Villes affichées dans la console.";


    }


    //BDD=>ok
    @PostMapping("/create")
    public ResponseEntity<String> createVille(@RequestBody Ville newVille) {
        return villeService.addVille(newVille);

    }

    //BDD=>ok
    @PutMapping("/edit/{id}")
    public ResponseEntity<String> editVille(@PathVariable Long id, @RequestBody Ville newVille) {
        newVille.setId(id);
        return villeService.updateVille(newVille);

        // TP4-5
//        Ville ville = villeService.findVilleById(id);
//        if (ville != null) {
//            return villeService.updateVille(newVille);
//        }
//        return null;
    }


    @DeleteMapping("/sup/{id}")
    public ResponseEntity<String> deleteVille(@PathVariable Long id) {
        return villeService.deleteVille(id);
    }


}
