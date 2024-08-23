package com.lana.springdemo.controller;


import com.lana.springdemo.entity.Ville;
import com.lana.springdemo.repository.VilleRepository;
import com.lana.springdemo.service.VilleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

//@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class VilleServiceTest {

    @Autowired
    VilleService villeService;

    @Autowired
    VilleRepository villeRepository;

    @Autowired
    private MockMvc mockMvc;



    @Test
    public void testGetAllTowns(){
        when(villeRepository.findAll()).thenReturn(List.of(new Ville("Angers", 142000)));

        Iterable<Ville> villes = villeService.findAllVilles();
        assertTrue(villes.iterator().hasNext());

    }

}
