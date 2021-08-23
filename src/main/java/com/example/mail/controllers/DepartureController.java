package com.example.mail.controllers;

import com.example.mail.entity.Departure;
import com.example.mail.service.DepartureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/depar")
public class DepartureController {

    private DepartureService departureService;

    public DepartureController(DepartureService departureService) {
        this.departureService = departureService;
    }

    @PostMapping("/add")
    public ResponseEntity<Departure> add(@RequestBody Departure departure){
        System.out.println("Departure: add------------------------");
        return ResponseEntity.ok(departureService.add(departure));
    }
}
