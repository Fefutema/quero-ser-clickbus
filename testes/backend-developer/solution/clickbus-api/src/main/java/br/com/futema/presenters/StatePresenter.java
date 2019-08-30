package br.com.futema.presenters;

import br.com.futema.persistence.entities.State;

public class StatePresenter {

	private Long id;
	private String name;

	public StatePresenter() {
	}
	
	public StatePresenter(State state) {
		this.id = state.getId();
		this.name = state.getName();
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

	@Override
	public String toString() {
		return "State [id=" + id + ", name=" + name + "]";
	}

}
