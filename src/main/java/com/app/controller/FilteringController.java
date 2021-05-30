package com.app.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Data;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	//Example of static filtering
	@GetMapping("/data")
	public MappingJacksonValue getSecureData(){
		Data data= new Data("varun","password","myEmailId@gmail.com"); 
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("userName","email");
		FilterProvider filters = new SimpleFilterProvider().addFilter("myDataFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(data);
		mapping.setFilters(filters);
	return mapping;
}
	
	@GetMapping("/data-list")
	public List<Data> getSecureDataList(){
		
		return Arrays.asList(new Data("varun","password","myEmailId@gmail.com")
				,new Data("Rahul","password","rahulEmailId@gmail.com")); 
	}
	
	
}
