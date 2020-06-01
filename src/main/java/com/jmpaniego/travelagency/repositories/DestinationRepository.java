package com.jmpaniego.travelagency.repositories;

import com.jmpaniego.travelagency.models.Destination;
import org.springframework.data.repository.CrudRepository;

public interface DestinationRepository extends CrudRepository<Destination,Long> {
}
