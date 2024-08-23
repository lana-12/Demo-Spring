package com.lana.springdemo.mapper;

import com.lana.springdemo.dto.VilleDto;
import com.lana.springdemo.entity.Departement;
import com.lana.springdemo.entity.Ville;
import com.lana.springdemo.service.DepartementService;
import com.lana.springdemo.service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VilleMapper {

    @Autowired
    VilleService villeService;

    @Autowired
    DepartementService departementService;

    public VilleDto toDto(Ville ville) {

        return null;
    }

    public Ville toBean(VilleDto villeDto) {
        Ville ville = new Ville();
        ville.setName(villeDto.getVilleName());
        ville.setNbHabitants(villeDto.getVilleNbHabitants());

        Departement departement = new Departement();
        departement.setCode(villeDto.getDepartementCode());
        departement.setName(villeDto.getDepartementName());

        ville.setDepartement(departement);
        Ville v = villeService.findVilleByName(villeDto.getVilleName());
        if(v!=null){
            ville.setId(v.getId());
        }

        Departement d = departementService.findByCode(villeDto.getDepartementCode());
        if(d!=null){
            departement.setId(d.getId());
        }


        return ville;
    }

}
