package br.com.futema.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.futema.controllers.parameters.PlaceParameter;
import br.com.futema.services.PlaceService;

@RestController
@RequestMapping("/places")
public class PlaceController {

	@Autowired
	private PlaceService placeService;
	
	@PostMapping
	public ResponseEntity<?> savePlace(@RequestBody PlaceParameter parameter) {
		
		return ResponseEntity.ok("");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editPlace(@RequestBody PlaceParameter parameter, @PathVariable("id") Long id) {
		
		return ResponseEntity.ok("");
	} 
	
	@GetMapping
	public ResponseEntity<?> listPlaces(@RequestParam("name") List<String> names) {
		
		return ResponseEntity.ok(placeService.getPlacesByName(names));
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<?> getPlaceById(@PathVariable("id") Long id) {
		
		return ResponseEntity.ok(placeService.getPlaceById(id));
	}
}
