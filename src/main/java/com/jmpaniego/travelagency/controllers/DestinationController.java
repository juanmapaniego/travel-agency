package com.jmpaniego.travelagency.controllers;

import com.jmpaniego.travelagency.models.Destination;
import com.jmpaniego.travelagency.services.DestinationService;
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
@RequestMapping("destinations")
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    @GetMapping({"","/"})
    public List<Destination> listAll(){
        return destinationService.listAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Destination> findById(@PathVariable Long id) {
        Destination dbDestination = destinationService.findById(id);
        return new ResponseEntity<>(dbDestination, HttpStatus.OK);
    }

    @PostMapping(value = {"","/"})
    public ResponseEntity<Destination> createDestination(@RequestBody Destination destination){
        Destination dbDestination = destinationService.save(destination);
        return new ResponseEntity<>(dbDestination, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> errorGeneral(Exception e, HttpServletRequest request) {
        Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        if (pathVariables.containsKey("id"))
            return new ResponseEntity<>("Destination " + pathVariables.get("id") + " does not exist", HttpStatus.NOT_FOUND);
        e.printStackTrace();
        return new ResponseEntity<>("Internal error", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> errorGeneral(DataIntegrityViolationException dive, HttpServletRequest request) {
        return new ResponseEntity<>("Already exist an station named", HttpStatus.CONFLICT);
    }
}
