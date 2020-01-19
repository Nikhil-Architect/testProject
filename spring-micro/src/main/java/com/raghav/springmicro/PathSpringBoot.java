package com.raghav.springmicro;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PathSpringBoot {
	
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/getName/{value}")
	public String getNameByPath(@PathVariable String value){
		return "your value is ===="+value;
	}

}
