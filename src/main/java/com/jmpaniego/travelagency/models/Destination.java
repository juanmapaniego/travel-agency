package com.jmpaniego.travelagency.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;

    @ManyToMany
    @JoinTable(name="destination_station",
                joinColumns = @JoinColumn(name = "destination_id"),
                inverseJoinColumns = @JoinColumn(name = "station_id"))
    Set<Station> preferedStation;

    public Destination() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Station> getPreferedStation() {
        return preferedStation;
    }

    public void setPreferedStation(Set<Station> preferedStation) {
        this.preferedStation = preferedStation;
    }
}
