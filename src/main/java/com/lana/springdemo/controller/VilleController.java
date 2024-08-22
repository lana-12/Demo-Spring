package com.lana.springdemo.controllers;

import com.lana.springdemo.entities.Ville;
import com.lana.springdemo.service.VilleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleController {


    private final VilleService villeService;

    public VilleController(VilleService villeService) {
        this.villeService = villeService;
    }

    //BDD=>ok
    @GetMapping
    public List<Ville> getVilles() {
        return villeService.findAllVilles();
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


    //BDD=>ok
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
