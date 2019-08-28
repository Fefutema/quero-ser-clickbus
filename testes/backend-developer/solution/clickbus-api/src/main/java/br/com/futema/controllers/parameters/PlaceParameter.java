package br.com.futema.controllers.parameters;

import br.com.futema.persistence.entities.Place;

public class PlaceParameter {

	private String name;
	private String slug;

	private CityParameter city;

	public Place toEntity() {
		Place place = new Place();
		place.setName(this.name);
		place.setSlug(this.slug);
		place.setCity(this.city.toEntity());
		
		return place;
	}
	
	public Place toEntity(Long id) {
		Place place = new Place();
		place.setId(id);
		place.setName(this.name);
		place.setSlug(this.slug);
		place.setCity(this.city.toEntity());
		
		return place;
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

	public CityParameter getCity() {
		return city;
	}

	public void setCity(CityParameter city) {
		this.city = city;
	}

}
