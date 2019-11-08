package br.com.futema.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.futema.controllers.parameters.PlaceParameter;
import br.com.futema.presenters.PlacePresenter;
import br.com.futema.services.PlaceService;

@RestController
@RequestMapping("/places")
public class PlaceController {

	@Autowired
	private PlaceService placeService;
	
	@PostMapping
	public ResponseEntity<?> savePlace(@Valid @RequestBody PlaceParameter parameter) {

		DiscoveryClient d = new EurekaDiscoveryClient(null, null);
		for (String id :d.getServices()) {
			List<ServiceInstance> instances = d.getInstances(id);
			for (ServiceInstance instance : instances) {
				
			}
		}
		
		PlacePresenter place = placeService.savePlace(parameter);
		
		URI location = ServletUriComponentsBuilder
							.fromCurrentRequest().path("/{id}")
							.buildAndExpand(place.getId()).toUri();
		
		return ResponseEntity.created(location).body(place);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editPlace(@Valid @RequestBody PlaceParameter parameter, @PathVariable("id") Long id) {
		
		PlacePresenter place = placeService.updatePlace(parameter, id);
		
		return ResponseEntity.ok(place);
	} 
	
	@GetMapping
	public ResponseEntity<?> listPlaces(@RequestParam(name="name", required=false) List<String> names) {
		
		return ResponseEntity.ok(placeService.getPlacesByName(names));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getPlaceById(@PathVariable("id") Long id) {
		
		return ResponseEntity.ok(placeService.getPlaceById(id));
	}
}
