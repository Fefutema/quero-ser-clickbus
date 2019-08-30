package br.com.futema.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.futema.controllers.parameters.PlaceParameter;
import br.com.futema.exceptions.BusinessException;
import br.com.futema.exceptions.NotFoundException;
import br.com.futema.persistence.entities.City;
import br.com.futema.persistence.entities.Place;
import br.com.futema.persistence.repositories.CityRepository;
import br.com.futema.persistence.repositories.PlaceRepository;
import br.com.futema.presenters.PlacePresenter;

@Service
public class PlaceService {

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private PlaceRepository placeRepository;

	public PlacePresenter savePlace(PlaceParameter placeParameter) {

		Place place = placeParameter.toEntity();

		List<City> cities = cityRepository.findByName(place.getCity().getName().toUpperCase());
		
		if (cities != null && !cities.isEmpty()) {
			String stateName = place.getCity().getState().getName().toUpperCase();
			City city = cities.stream()
									.filter(c -> c.getState() != null)
									.filter(c -> stateName.equalsIgnoreCase(c.getState().getName().toUpperCase()))
									.findFirst()
									.orElseThrow(() -> new BusinessException("State and City must be valid"));
			place.setCity(city);
		} else {
			throw new BusinessException("State and City must be valid");
		}
		
		place = placeRepository.save(place);

		return new PlacePresenter(place);
	}

	public PlacePresenter updatePlace(PlaceParameter placeParameter, Long id) {

		try {
			Place place = placeRepository.getOne(id);
			place = placeParameter.toEntity(place.getId(), place.getCreatedAt());

			place = placeRepository.save(place);
			return new PlacePresenter(place);
		} catch(EntityNotFoundException e) {
			throw new NotFoundException("Place by id cannot be found");
		}
	}

	public PlacePresenter getPlaceById(Long id) {
		try {
			Place place = placeRepository.getOne(id);
			return new PlacePresenter(place);
		} catch(EntityNotFoundException e) {
			throw new NotFoundException("Place by id cannot be found");
		}
	}

	public List<PlacePresenter> getPlacesByName(List<String> names) {
		List<Place> places = null;
		if (names == null || names.isEmpty()) {
			places = placeRepository.findAll();
		} else {
			places = placeRepository.findByNameIn(names);
		}

		if (places == null || places.isEmpty()) {
			throw new NotFoundException("Has no places to found");
		} else {
			return places.stream().map(p -> new PlacePresenter(p)).collect(Collectors.toList());
		}

	}

}
