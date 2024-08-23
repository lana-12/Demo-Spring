package com.lana.springdemo.controller;

import com.lana.springdemo.entity.Departement;
import com.lana.springdemo.entity.Ville;
import com.lana.springdemo.service.DepartementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departement")
public class DepartementController {

    private final DepartementService departementService;

    public DepartementController(DepartementService departementService) {
        this.departementService = departementService;
    }


    @GetMapping
    public List<Departement> getDpt() {
        return departementService.findAllDpts();
    }

    @GetMapping("/code/{id}")
    public String getDptCode(@PathVariable Long id) {
        return departementService.getDepartementCode(id);
    }


    @GetMapping("/{departementId}/villes/top/{n}")
    public ResponseEntity<List<Ville>> getTopNVillesByDepartement(@PathVariable Long departementId, @PathVariable int n) {
        List<Ville> villes = departementService.findTopNVillesByDepartement(departementId, n);
        return ResponseEntity.ok(villes);
    }

    @GetMapping("/{codeDepartement}/villes/population-range")
    public ResponseEntity<List<Ville>> getVillesByDepartementAndPopulationRange(
            @PathVariable String codeDepartement,
            @RequestParam int minPopulation,
            @RequestParam int maxPopulation) {
        List<Ville> villes = departementService.findVillesByDepartementAndPopulationRange(codeDepartement, minPopulation, maxPopulation);
        return ResponseEntity.ok(villes);
    }





    @PostMapping("/create")
    public ResponseEntity<String> createVille(@RequestBody Departement newDepartement) {
        return departementService.addDepartement(newDepartement);

    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<String> editVille(@PathVariable Long id, @RequestBody Departement newDpt) {
        newDpt.setId(id);
        return departementService.updateDepartement(newDpt);

    }


    @DeleteMapping("/sup/{id}")
    public ResponseEntity<String> suppDepartement(@PathVariable Long id) {
        return departementService.deleteDepartement(id);
    }


}
