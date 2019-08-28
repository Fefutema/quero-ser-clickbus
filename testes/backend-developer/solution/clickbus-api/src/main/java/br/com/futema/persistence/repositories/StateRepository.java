package br.com.futema.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.futema.persistence.entities.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

}
