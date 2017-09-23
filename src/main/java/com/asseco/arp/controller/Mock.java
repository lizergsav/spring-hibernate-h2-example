package com.asseco.arp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asseco.arp.domain.RestMetaData;
import com.asseco.arp.repository.RestMetaDataRepository;

import io.swagger.annotations.ApiOperation;

@RestController
public class Mock {

	@Autowired
	private RestMetaDataRepository restRepo;
	
	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	ResponseEntity<?> test(){
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ApiOperation(value = "save restMetaData", notes = "Save meta data", response = RestMetaData.class)
	ResponseEntity<RestMetaData> saveRestMetaData(@RequestParam(required = true) String name){
		
		RestMetaData rest = new RestMetaData();
		rest.setName(name);
		
		rest = this.restRepo.saveAndFlush(rest);
		
		return new ResponseEntity<RestMetaData>(rest,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getRestMetaData", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	@ApiOperation(value = "Get restMetaData", notes = "Get meta data", response = RestMetaData.class)
	ResponseEntity<RestMetaData> getRestMetaData(@RequestParam(required = true) Long id){
		
		return new ResponseEntity<RestMetaData>(this.restRepo.findOne(id),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getRestMetaDataList", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	@ApiOperation(value = "Get restMetaData list", notes = "Get meta data list", response = RestMetaData.class)
	ResponseEntity<List<RestMetaData>> getRestMetaDataList(){
		
		return new ResponseEntity<List<RestMetaData>>(this.restRepo.findAll(),HttpStatus.OK);
	}
}
