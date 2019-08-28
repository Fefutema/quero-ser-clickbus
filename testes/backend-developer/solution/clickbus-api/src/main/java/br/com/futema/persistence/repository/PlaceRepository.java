package br.com.futema.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.futema.persistence.entity.Place;

public interface PlaceRepository extends JpaRepository<Place, Long> {

}
