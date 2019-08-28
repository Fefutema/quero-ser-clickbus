package br.com.futema.controllers.parameters;

import br.com.futema.persistence.entities.City;

public class CityParameter {

	private String name;
	private StateParameter state;

	public City toEntity() {
		City city = new City();
		city.setName(this.name);
		city.setState(this.state.toEntity());
		
		return city;
	}
	
	public City toEntity(Long id) {
		City city = new City();
		city.setId(id);
		city.setName(this.name);
		city.setState(this.state.toEntity());
		
		return city;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StateParameter getState() {
		return state;
	}

	public void setState(StateParameter state) {
		this.state = state;
	}

}
