package br.com.futema.presenters;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.futema.persistence.entities.Place;

public class PlacePresenter {

	private Long id;
	private String name;
	private String slug;
	private CityPresenter city;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createdAt;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updatedAt;
	
	public PlacePresenter() {
	}
	
	public PlacePresenter(Place place) {
		this.id = place.getId();
		this.name = place.getName();
		this.slug = place.getSlug();
		this.city = new CityPresenter(place.getCity());
		this.createdAt = place.getCreatedAt();
		this.updatedAt = place.getUpdatedAt();
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
