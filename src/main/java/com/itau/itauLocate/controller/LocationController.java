package com.itau.itauLocate.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.PlacesApi;
import com.google.maps.TextSearchRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;
import com.itau.itauLocate.config.ConfigUtils;

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
	public static final Logger log = Logger.getLogger(LocationController.class);
	
	private static final String apikey = ConfigUtils.getProp().getProperty("daods.google.api.psw");

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
		
		GeoApiContext context = new GeoApiContext.Builder().apiKey(apikey).build();
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
	@RequestMapping(value = "/buscaagencias", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<List<PlacesSearchResult>> getAgencias(@RequestBody LatLng geocode) throws ApiException, InterruptedException, IOException {
		System.out.println("BUSCA DE AGENCIAS");
		GeoApiContext context = new GeoApiContext.Builder().apiKey(apikey).build();
		
		TextSearchRequest query = PlacesApi.textSearchQuery(context, "itau'", geocode);
		query.radius(20000);
		
		PlacesSearchResponse response = query.await();
		
		PlacesSearchResult[] results = response.results;
		return new ResponseEntity<List<PlacesSearchResult>>(Arrays.asList(results), HttpStatus.OK);
		
	}
	
	

}
