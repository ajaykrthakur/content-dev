package com.example.demo.controller;

import java.io.File;

//@RestController
//@RequestMapping("news")
public class ContentProvider {
	//@RequestMapping(method=RequestMethod.GET)
	public String  myMethod() {
		return processXml (new File("/sample.xml"));
	}
	
	String processXml(File xmlSourceDocument){
		return "Hello News!";
	}
}
