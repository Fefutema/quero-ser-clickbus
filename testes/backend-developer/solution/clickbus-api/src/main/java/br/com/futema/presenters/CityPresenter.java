package br.com.futema.presenters;

import br.com.futema.persistence.entities.City;

public class CityPresenter extends AbstractPresenter {

	private Long id;
	private String name;
	private StatePresenter state;

	public CityPresenter(City city) {
		this.id = city.getId();
		this.name = city.getName();
		this.state = new StatePresenter(city.getState());
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

	public StatePresenter getState() {
		return state;
	}

	public void setState(StatePresenter state) {
		this.state = state;
	}

}
