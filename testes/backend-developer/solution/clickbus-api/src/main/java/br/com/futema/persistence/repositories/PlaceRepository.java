package br.com.futema.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.futema.persistence.entities.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

	List<Place> findByNameIn(List<String> name);
	
}
