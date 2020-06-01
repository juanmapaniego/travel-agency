package com.jmpaniego.travelagency.repositories;

import com.jmpaniego.travelagency.models.Station;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends CrudRepository<Station, Long> {
}
