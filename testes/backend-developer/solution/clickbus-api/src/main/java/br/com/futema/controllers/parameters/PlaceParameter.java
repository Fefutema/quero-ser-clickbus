package br.com.futema.controllers.parameters;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import br.com.futema.persistence.entities.Place;

public class PlaceParameter {

	@NotBlank(message = "Place name must not be null or empty")
	private String name;
	@NotBlank(message = "The slug must not be null or empty")
	private String slug;

	@Valid
	private CityParameter city;

	public Place toEntity() {
		Place place = new Place();
		place.setName(this.name);
		place.setSlug(this.slug);
		place.setCity(this.city.toEntity());
		
		return place;
	}
	
	public Place toEntity(Long id, Date createdAt) {
		Place place = new Place();
		place.setId(id);
		place.setName(this.name);
		place.setSlug(this.slug);
		place.setCity(this.city.toEntity());
		place.setCreatedAt(createdAt);
		
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
