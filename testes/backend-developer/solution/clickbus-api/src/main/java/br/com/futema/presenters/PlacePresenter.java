package br.com.futema.presenters;

import br.com.futema.persistence.entities.Place;

public class PlacePresenter extends AbstractPresenter {

	private Long id;
	private String name;
	private String slug;
	private CityPresenter city;

	public PlacePresenter(Place place) {
		this.id = place.getId();
		this.name = place.getName();
		this.slug = place.getSlug();
		this.city = new CityPresenter(place.getCity());
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

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public CityPresenter getCity() {
		return city;
	}

	public void setCity(CityPresenter city) {
		this.city = city;
	}

}
