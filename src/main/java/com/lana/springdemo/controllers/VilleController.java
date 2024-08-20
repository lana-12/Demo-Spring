package com.lana.springdemo.controllers;


import com.lana.springdemo.entities.Ville;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleController {


    @GetMapping
    public List<Ville> getVilles() {
        // Créer une liste de villes
        List<Ville> villes = new ArrayList<>();
        villes.add(new Ville("Paris", 2148000));
        villes.add(new Ville("Lyon", 513000));
        villes.add(new Ville("Marseille", 861635));
        villes.add(new Ville("Montpellier", 360965));
        villes.add(new Ville("Carcassonne", 125020));
        villes.add(new Ville("Nice", 125000));

        return villes;
    }
}
