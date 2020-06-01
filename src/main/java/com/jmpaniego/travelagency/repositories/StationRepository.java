package com.jmpaniego.travelagency.repositories;

import com.jmpaniego.travelagency.models.Station;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface StationRepository extends CrudRepository<Station, Long> {
    Set<Station> findByName(String name);
    @Query("SELECT s FROM Station s WHERE (s.name Not LIKE :name)")
    Set<Station> findAllExceptByName(String name);
}
