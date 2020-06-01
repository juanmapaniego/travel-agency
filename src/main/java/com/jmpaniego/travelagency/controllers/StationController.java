package com.jmpaniego.travelagency.controllers;

import com.jmpaniego.travelagency.models.Station;
import com.jmpaniego.travelagency.services.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("stations")
public class StationController {

    @Autowired
    private StationService stationService;

    @GetMapping({"", "/"})
    public List<Station> listAll() {
        return stationService.listAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Station> findById(@PathVariable Long id) {
        Station dbStation = stationService.findById(id);
        return new ResponseEntity<>(dbStation, HttpStatus.OK);
    }

    @PostMapping({"", "/"})
    public ResponseEntity<Station> create(@RequestBody Station station) {
        Station dbStation = stationService.save(station);
        return new ResponseEntity<>(dbStation, HttpStatus.OK);
    }


    @PutMapping("{id}")
    public ResponseEntity<Station> update(@PathVariable Long id,
                                    @RequestBody Station station) {
        Station dbStation = stationService.findById(id);
        dbStation.setName(station.getName());
        dbStation = stationService.save(dbStation);
        return new ResponseEntity<>(dbStation, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> errorGeneral(Exception e, HttpServletRequest request) {
        Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        if (pathVariables.containsKey("id"))
            return new ResponseEntity<>("Station " + pathVariables.get("id") + " does not exist", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>("Internal error", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> errorGeneral(DataIntegrityViolationException dive, HttpServletRequest request) {
        return new ResponseEntity<>("Already exist an station named", HttpStatus.CONFLICT);
    }
}
