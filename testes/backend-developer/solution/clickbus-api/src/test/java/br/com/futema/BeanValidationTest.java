package br.com.futema;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.futema.controllers.parameters.CityParameter;
import br.com.futema.controllers.parameters.PlaceParameter;
import br.com.futema.controllers.parameters.StateParameter;

public class BeanValidationTest {

	private static ValidatorFactory validatorFactory;
    private static Validator validator;
 
    @BeforeClass
    public static void init() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }
	
    @AfterClass
    public static void close() {
    	validatorFactory.close();
    }
	
    @Test
    public void validPlaceTest() {
		PlaceParameter place = new PlaceParameter();
		place.setName("Test");
		place.setSlug("/places");
		
		CityParameter city = new CityParameter();
		city.setName("BATATAIS");
		
		StateParameter state = new StateParameter();
		state.setName("SÃO PAULO");
		
		city.setState(state);
		place.setCity(city);
		
		Set<ConstraintViolation<PlaceParameter>> validate = validator.validate(place);
		
		assertTrue(validate.isEmpty());
    }
    
    @Test
    public void invalidPlaceNameTest() {
		PlaceParameter place = new PlaceParameter();
		place.setName("  ");
		place.setSlug("/places");
		
		CityParameter city = new CityParameter();
		city.setName("BATATAIS");
		
		StateParameter state = new StateParameter();
		state.setName("SÃO PAULO");
		
		place.setCity(city);
		
		Set<ConstraintViolation<PlaceParameter>> validate = validator.validate(place);
		
		assertEquals(1, validate.size());
		
		ConstraintViolation<PlaceParameter> violation = validate.iterator().next();
		assertEquals("Place name must not be null or empty", violation.getMessage());
		assertEquals("  ", violation.getInvalidValue());
    }
    
    @Test
    public void invalidPlaceSlugTest() {
		PlaceParameter place = new PlaceParameter();
		place.setName("Test");
		place.setSlug(null);
		
		CityParameter city = new CityParameter();
		city.setName("BATATAIS");
		
		StateParameter state = new StateParameter();
		state.setName("SÃO PAULO");
		
		place.setCity(city);
		
		Set<ConstraintViolation<PlaceParameter>> validate = validator.validate(place);
		
		assertEquals(1, validate.size());
		
		ConstraintViolation<PlaceParameter> violation = validate.iterator().next();
		assertEquals("The slug must not be null or empty", violation.getMessage());
		assertEquals(null, violation.getInvalidValue());
    }
    
    @Test
    public void invalidPlaceCityTest() {
		CityParameter city = new CityParameter();
		city.setName("    ");
		
		Set<ConstraintViolation<CityParameter>> validate = validator.validate(city);
		
		assertEquals(1, validate.size());
		
		ConstraintViolation<CityParameter> violation = validate.iterator().next();
		assertEquals("City name must not be null or empty", violation.getMessage());
		assertEquals("    ", violation.getInvalidValue());
    }
    
    @Test
    public void invalidPlaceStateTest() {
		StateParameter state = new StateParameter();
		state.setName(null);
		
		Set<ConstraintViolation<StateParameter>> validate = validator.validate(state);
		
		assertEquals(1, validate.size());
		
		ConstraintViolation<StateParameter> violation = validate.iterator().next();
		assertEquals("State name must not be null or empty", violation.getMessage());
		assertEquals(null, violation.getInvalidValue());
    }
    
}
