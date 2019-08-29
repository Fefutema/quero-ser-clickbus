package br.com.futema.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.futema.controllers.parameters.PlaceParameter;
import br.com.futema.exceptions.ObjectNotFoundException;
import br.com.futema.persistence.entities.Place;
import br.com.futema.persistence.repositories.CityRepository;
import br.com.futema.persistence.repositories.PlaceRepository;
import br.com.futema.persistence.repositories.StateRepository;
import br.com.futema.presenters.PlacePresenter;

@Service
public class PlaceService {

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private PlaceRepository placeRepository;

	public PlacePresenter savePlace(PlaceParameter placeParameter) {

		Place place = placeParameter.toEntity();

		place = placeRepository.save(place);

		return new PlacePresenter(place);
	}

	public PlacePresenter updatePlace(PlaceParameter placeParameter, Long id) {

		Place place = placeRepository.getOne(id);

		if (place != null && place.getId() != null) {
			place = placeParameter.toEntity(place.getId());

			place = placeRepository.save(place);
			return new PlacePresenter(place);
		} else {
			throw new ObjectNotFoundException();
		}

	}

	public PlacePresenter getPlaceById(Long id) {
		Place place = placeRepository.getOne(id);

		if (place != null && place.getId() != null) {
			return new PlacePresenter(place);
		} else {
			throw new ObjectNotFoundException();
		}
	}

	public List<PlacePresenter> getPlacesByName(List<String> names) {
		List<Place> places = placeRepository.findByNameIn(names);

		if (places == null || places.isEmpty()) {
			throw new ObjectNotFoundException();
		} else {
			return places.stream().map(PlacePresenter::new).collect(Collectors.toList());
		}

	}

}
