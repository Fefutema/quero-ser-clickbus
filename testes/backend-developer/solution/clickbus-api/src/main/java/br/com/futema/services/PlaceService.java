package br.com.futema.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public PlacePresenter getPlaceById(Long id) {
		return new PlacePresenter(placeRepository.getOne(id));
	}
	
	public List<PlacePresenter> getPlacesByName(List<String> names) {
		List<Place> places = placeRepository.findByNameIn(names);
		
		if (places == null || places.isEmpty()) {
			return new ArrayList<>();
		} else {
			return places.stream().map(PlacePresenter::new)
						.collect(Collectors.toList());
		}
		
	}
	
}
