package com.itau.itauLocate.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itau.itauLocate.dao.AgencyDao;
import com.itau.itauLocate.model.AgencyModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/agencyInfo")
@SpringBootApplication
@Api(value ="ItauLocate - Agencias")
public class AgencyController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3094109731493000364L;
	
	@Autowired
	AgencyDao dao;
	
	@CrossOrigin
	@ApiOperation(value = "Salva uma agencia favorita")
	@RequestMapping(value = "/save/", method = RequestMethod.POST)
	public ResponseEntity<String> salvarAgencia(@RequestBody AgencyModel agency){
		
		
		System.out.println(agency);
		dao.insert(agency);
		return new ResponseEntity<String>(HttpStatus.OK);
		
	}
	
	@CrossOrigin
	@ApiOperation(value = "Busca de Favoritos")
	@RequestMapping(value = "/retriveall/", method = RequestMethod.GET)
	public ResponseEntity<List<AgencyModel>> buscarAgencias(){
		
		
		List<AgencyModel> agencias = dao.getAll();
		
		if(agencias == null || agencias.isEmpty()) {
			return new ResponseEntity<List<AgencyModel>>(agencias, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<AgencyModel>>(agencias, HttpStatus.OK);
		
	}
	
	@CrossOrigin
	@ApiOperation(value = "deleta agencia favorita")
	@RequestMapping(value = "/delete/", method = RequestMethod.DELETE)
	public ResponseEntity<String> deletarAgenciaFavorita(@RequestParam Long id){
		
		AgencyModel agencia = dao.findById(id);
		
		if(agencia == null) {
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}
		dao.remove(agencia);
		return new ResponseEntity<String>(HttpStatus.OK);
		
	}


}
