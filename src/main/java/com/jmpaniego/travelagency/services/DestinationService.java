package com.jmpaniego.travelagency.services;

import com.jmpaniego.travelagency.models.Destination;
import com.jmpaniego.travelagency.repositories.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DestinationService {
    @Autowired
    private DestinationRepository destinationRepository;

    public List<Destination> listAll(){
        List<Destination> res = new ArrayList<>();
        destinationRepository.findAll().forEach(res::add);
        return res;
    }

    public Destination save(Destination destination){
        return destinationRepository.save(destination);
    }

    public Destination findById(Long id){
        return destinationRepository.findById(id).orElseThrow(()->new RuntimeException("No destation with id "+ id));
    }
}
