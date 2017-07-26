package com.example.demo.controller;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("home")
public class HomeController {
	
    @CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method=RequestMethod.GET)
	public Test  myMethod() {
		return processXml (new File("/sample.xml"));
	}
	
	Test processXml(File xmlSourceDocument){
		
		// equivalent of ajax call.
		// url call and get response in custom obj form.
		RestTemplate template = new RestTemplate();
		String res = null;
		try {
			res = template.getForObject(new URI("http://localhost:8000/news"), String.class);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Test(res);
	}
	
	
	static class Test{
		private String res;
		
		public Test(String res){
			this.res = res;
		}
		
		public String getRes() {
			return res;
		}
		
		public void setRes(String res) {
			this.res = res;
		}
		
	}
	
}
