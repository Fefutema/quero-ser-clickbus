package br.com.futema;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.futema.controllers.parameters.CityParameter;
import br.com.futema.controllers.parameters.PlaceParameter;
import br.com.futema.controllers.parameters.StateParameter;
import br.com.futema.presenters.PlacePresenter;
import br.com.futema.utils.LoadTestData;
import br.com.futema.utils.SetupH2Console;

@RunWith(SpringRunner.class)
@SpringBootTest(
		  classes = ClickbusApiApplication.class)
@AutoConfigureMockMvc
@SetupH2Console
@LoadTestData(script="scripts/data.sql")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;
	
	
	@Test
	public void a1_createPlaceTest() throws Exception {
		PlaceParameter place = new PlaceParameter();
		place.setName("Test");
		place.setSlug("/places");
		
		CityParameter city = new CityParameter();
		city.setName("BATATAIS");
		
		StateParameter state = new StateParameter();
		state.setName("SÃO PAULO");
		
		city.setState(state);
		place.setCity(city);
		
		RequestBuilder request = post("/places")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(place));
		
		MvcResult result = mvc.perform(request).andReturn();
		
		PlacePresenter resource = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<PlacePresenter>() {});
		
		assertEquals(new Long(1), resource.getId());
		assertEquals("Test", resource.getName());
		assertEquals("/places", resource.getSlug());
		assertEquals("BATATAIS", resource.getCity().getName());
		assertEquals("SÃO PAULO", resource.getCity().getState().getName());
		assertTrue(resource.getCreatedAt() != null);
		assertEquals(null, resource.getUpdatedAt());
		
	}
	
	@Test
	public void a2_getAllPlaceTest() throws Exception {
		RequestBuilder request = get("/places")
					.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mvc.perform(request).andExpect(status().isOk()).andReturn();
		
		PlacePresenter[] resource = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<PlacePresenter[]>() {});
		
		assertEquals(new Long(1), resource[0].getId());
		assertEquals("Test", resource[0].getName());
		assertEquals("/places", resource[0].getSlug());
		assertEquals("BATATAIS", resource[0].getCity().getName());
		assertEquals("SÃO PAULO", resource[0].getCity().getState().getName());
		assertTrue(resource[0].getCreatedAt() != null);
		assertEquals(null, resource[0].getUpdatedAt());
		
	}
	
	@Test
	public void a3_getOnePlaceTest() throws Exception {
		RequestBuilder request = get("/places/1")
					.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mvc.perform(request).andExpect(status().isOk()).andReturn();
		
		PlacePresenter resource = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<PlacePresenter>() {});
		
		assertEquals(new Long(1), resource.getId());
		assertEquals("Test", resource.getName());
		assertEquals("/places", resource.getSlug());
		assertEquals("BATATAIS", resource.getCity().getName());
		assertEquals("SÃO PAULO", resource.getCity().getState().getName());
		assertTrue(resource.getCreatedAt() != null);
		assertEquals(null, resource.getUpdatedAt());
		
	}
	
	@Test
	public void a4_getPlaceByNameTest() throws Exception {
		RequestBuilder request = get("/places?name=Test")
					.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mvc.perform(request).andExpect(status().isOk()).andReturn();
		
		PlacePresenter[] resource = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<PlacePresenter[]>() {});
		
		assertEquals(new Long(1), resource[0].getId());
		assertEquals("Test", resource[0].getName());
		assertEquals("/places", resource[0].getSlug());
		assertEquals("BATATAIS", resource[0].getCity().getName());
		assertEquals("SÃO PAULO", resource[0].getCity().getState().getName());
		assertTrue(resource[0].getCreatedAt() != null);
		assertEquals(null, resource[0].getUpdatedAt());
		
	}
	
	//@Test
	public void UpdatePlaceTest() throws Exception {
		PlaceParameter place = new PlaceParameter();
		place.setName("UpdatedName");
		place.setSlug("/places");
		
		CityParameter city = new CityParameter();
		city.setName("ADOLFO");
		
		StateParameter state = new StateParameter();
		state.setName("SÃO PAULO");
		
		city.setState(state);
		place.setCity(city);
		
		RequestBuilder request = put("/places")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(place));
		
		MvcResult result = mvc.perform(request).andReturn();
		
		PlacePresenter resource = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<PlacePresenter>() {});
		
		assertEquals(new Long(1), resource.getId());
		assertEquals("UpdatedName", resource.getName());
		assertEquals("/places", resource.getSlug());
		assertEquals("ADOLFO", resource.getCity().getName());
		assertEquals("SÃO PAULO", resource.getCity().getState().getName());
		assertTrue(resource.getCreatedAt() != null);
		assertTrue(resource.getUpdatedAt() != null);
	}
	
}
