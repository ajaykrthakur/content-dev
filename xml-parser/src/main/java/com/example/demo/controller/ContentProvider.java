package com.example.demo.controller;

import java.io.File;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("news")
public class ContentProvider {
	@RequestMapping(method=RequestMethod.GET)
	public String  myMethod() {
		return processXml (new File("/sample.xml"));
	}
	
	String processXml(File xmlSourceDocument){
		return "Hello News!";
	}
}
