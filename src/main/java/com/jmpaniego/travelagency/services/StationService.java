package com.jmpaniego.travelagency.services;

import com.jmpaniego.travelagency.models.Station;
import com.jmpaniego.travelagency.repositories.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StationService {
    @Autowired
    private StationRepository stationRepository;

    public List<Station> listAll(){
        List<Station> res = new ArrayList<>();
        stationRepository.findAll().forEach(res::add);
        return res;
    }

    public Station save(Station station) {
        return stationRepository.save(station);
    }

    public Station findById(Long id) {
        return stationRepository.findById(id).orElseThrow(()->new RuntimeException("No station with id "+ id));
    }

    public void delete(Long id){
        stationRepository.deleteById(id);
    }
}
