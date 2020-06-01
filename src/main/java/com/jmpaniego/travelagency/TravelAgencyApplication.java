package com.jmpaniego.travelagency;

import com.jmpaniego.travelagency.models.Destination;
import com.jmpaniego.travelagency.models.Station;
import com.jmpaniego.travelagency.repositories.DestinationRepository;
import com.jmpaniego.travelagency.repositories.StationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class TravelAgencyApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelAgencyApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(StationRepository stationRepository,
								  DestinationRepository destinationRepository){
		return args -> {
			// Add stations
			Set<Station> stations = new HashSet<>();
			stations.add(new Station("Winter"));
			stations.add(new Station("Summer"));
			stations.add(new Station("Fall"));
			stations.add(new Station("Spring"));
			stationRepository.saveAll(stations);

			//Create destinations
			Destination rio_de_janeiro = new Destination("Rio de Janeiro", "All in one, most beatiful beach");
			Destination bariloche = new Destination("Bariloche", "All in one, ideal at winter");

			rio_de_janeiro.setPreferedStation(stationRepository.findByName("Summer"));
			bariloche.setPreferedStation(stationRepository.findAllExceptByName("Fall"));

			//save destinations
			destinationRepository.save(rio_de_janeiro);
			destinationRepository.save(bariloche);
		};
	}

}
