package com.lana.springdemo.mapper;

import com.lana.springdemo.dto.DepartementDto;
import com.lana.springdemo.entity.Departement;
import com.lana.springdemo.entity.Ville;
import org.springframework.stereotype.Service;


@Service
public class DepartementMapper {

    public DepartementDto toDto(Departement departement) {

        Double nbTotalInhabitants = 0.0;
        for (Ville v : departement.getVilles()) {
            nbTotalInhabitants += v.getNbHabitants();
        }
        DepartementDto departmentDto = new DepartementDto();
        departmentDto.setDepartementName(departement.getName());
        departmentDto.setDepartementCode(departement.getCode());
        departmentDto.setTotalNbInhabitants(nbTotalInhabitants);
        return departmentDto;

//        DepartementDto dto = new DepartementDto();
//        dto.setDepartementCode(departement.getCode());
//        dto.setDepartementName(departement.getName());
//
//        return dto;
    }


}
