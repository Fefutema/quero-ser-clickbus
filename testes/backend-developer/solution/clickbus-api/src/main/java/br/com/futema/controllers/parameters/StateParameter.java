package br.com.futema.controllers.parameters;

import br.com.futema.persistence.entities.State;

public class StateParameter {

	private String name;

	public State toEntity() {
		State state = new State();
		state.setName(this.name);

		return state;
	}

	public State toEntity(Long id) {
		State state = new State();
		state.setId(id);
		state.setName(this.name);

		return state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
