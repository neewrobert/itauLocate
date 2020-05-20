package com.itau.itauLocate.controller;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.PlacesApi;
import com.google.maps.TextSearchRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/addressInfo")
@SpringBootApplication
@Api(value ="ItauLocate")
public class LocationController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1757243011135154074L;

	/**
	 * Busca a Geolocalizacao de uma regiao com base no CEP ou endereco informado informado
	 * @param address(CEP)
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws ApiException 
	 */
	@CrossOrigin
	@ApiOperation(value = "busca de geolocalizacao por rua ou cep")
	@RequestMapping(value = "/buscaporcep/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<LatLng> getGeocode(@RequestParam String address) throws ApiException, InterruptedException, IOException {
		
		GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyCmyZCs4slOhJW-b8J5gHLO_5kpvVU2-oU").build();
		GeocodingResult[] results = GeocodingApi.geocode(context, address).await();
		
		return new ResponseEntity<LatLng>(results[0].geometry.location, HttpStatus.OK);
		
	}
	
	/**
	 * Busca as agencias do itau com base em uma geolocalizacao em um raio de 20km
	 * @param address(CEP)
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws ApiException 
	 */
	@CrossOrigin
	@ApiOperation(value = "busca de agencias do itau em um raio de 20km com base em uma geolocalizacao")
	@RequestMapping(value = "/buscaagencias/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<PlacesSearchResult>> getAgencias(LatLng geocode) throws ApiException, InterruptedException, IOException {
		System.out.println("BUSCA DE AGENCIAS");
		GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyCmyZCs4slOhJW-b8J5gHLO_5kpvVU2-oU").build();
		
		TextSearchRequest query = PlacesApi.textSearchQuery(context, "itau'", geocode);
		query.radius(20000);
		
		PlacesSearchResponse response = query.await();
		
		PlacesSearchResult[] results = response.results;
		return new ResponseEntity<List<PlacesSearchResult>>(Arrays.asList(results), HttpStatus.OK);
		
	}
	
	

}
