package com.lana.springdemo.controllers;

import com.lana.springdemo.entities.Ville;
import com.lana.springdemo.services.VilleService;
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

    @GetMapping
    public List<Ville> getVilles() {
        return villeService.findAllVilles();
    }

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

    @PostMapping("/add")
    public ResponseEntity<String> createVille(@RequestBody Ville newVille) {
        return villeService.addVille(newVille);
    }







}
