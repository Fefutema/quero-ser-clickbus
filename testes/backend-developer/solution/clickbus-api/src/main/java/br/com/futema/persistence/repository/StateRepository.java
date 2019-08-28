package br.com.futema.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.futema.persistence.entity.State;

public interface StateRepository extends JpaRepository<State, Long> {

}
