package br.com.futema.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.futema.persistence.entities.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

	List<City> findByName(String name);
	
}
