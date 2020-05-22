package com.itau.itauLocate;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.model.LatLng;
import com.itau.itauLocate.controller.LocationController;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ComponentScan("com.itau.itauLocate")
public class LocationControllerTest extends ItauLocateApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private LocationController controller;

	public final String REST_SERVICE_URI = "http://localhost:5000/addressInfo";

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	/**
	 * Testa se a conexao esta funcionando
	 * 
	 * @throws Exception
	 */
	@Test
	public void test1_GetIndexRestController() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get(REST_SERVICE_URI + "/buscaporcep/"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	/**
	 * Testa a busca por CEP
	 * @throws Exception
	 */
	@Test
	public void test2_BuscaPorCep() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get(REST_SERVICE_URI + "/buscaporcep/?address=08032-250"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	/**
	 * Testa a busca das agencias proximas a uma geolocalizacao
	 * @throws Exception
	 */
	@Test
	public void test2_BuscaAgencias() throws Exception {
		
		LatLng geocode = new LatLng(-23.5155776, -46.4277422);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(geocode);
		
		System.out.println(json);
		
		
		this.mockMvc.perform(MockMvcRequestBuilders.post(REST_SERVICE_URI + "/buscaagencias").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
