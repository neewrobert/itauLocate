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

import com.itau.itauLocate.controller.AgencyController;
import com.itau.itauLocate.model.AgencyModel;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ComponentScan("com.itau.itauLocate")
public class AgencyControllerTest extends ItauLocateApplicationTests{ 
	
	private MockMvc mockMvc;

	@Autowired
	private AgencyController controller;

	public final String REST_SERVICE_URI = "http://localhost:5000/agencyInfo";

	private AgencyModel agencia;

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
		this.mockMvc.perform(MockMvcRequestBuilders.get(REST_SERVICE_URI + "/retriveall/"))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
	
	/**
	 * Busca todas as agencias com o banco de dados vazio, deve retornar NO CONTENT
	 * @throws Exception
	 */
	@Test
	public void test2_buscarAgenciasQuandoOBancoEstaVazio() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.get(REST_SERVICE_URI + "/retriveall/"))
				.andExpect(MockMvcResultMatchers.status().isNoContent());

	}
	
	/**
	 * Teste para salvar uma agencia favorita, deve retornar OK
	 * @throws Exception
	 */
	@Test
	public void test3_salvarAgencia() throws Exception {
		
		agencia = new AgencyModel();
		agencia.setFormattedAddress("R, Rua Harry Dannemberg, 265 - Vila Carmosina, São Paulo - SP, 08270-010, Brazil");
		agencia.setPlaceId("ChIJTSyNNtBmzpQR7LQami27Nlw");
		agencia.setName("agencia itau itaquera");
		agencia.setLat(-23.5527976);
		agencia.setLng(-46.4629974);
		agencia.setRating(3.3);
		
		String json = agencia.toJson();
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.post(REST_SERVICE_URI + "/save/")
				.contentType(MediaType.APPLICATION_JSON).content(json))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	/**
	 * Com o BD populado pelo teste anterior, deve se buscar todas as agencias favoritas. O retorno deve ser OK
	 * @throws Exception
	 */
	@Test
	public void test4_buscarAgencias() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.get(REST_SERVICE_URI + "/retriveall/"))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

}
