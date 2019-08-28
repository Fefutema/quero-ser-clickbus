package br.com.futema.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.futema.persistence.entity.City;

public interface CityRepository extends JpaRepository<City, Long> {

}
