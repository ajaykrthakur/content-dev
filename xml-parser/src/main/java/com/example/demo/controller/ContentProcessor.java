package com.example.demo.controller;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import com.example.demo.models.ArticleModel;
import com.example.demo.service.XMLParserService;

@RestController
@RequestMapping("home")
public class ContentProcessor {

	@Autowired
	XMLParserService xmlParserService;

	@Value(value = "${input.jats.xml.path}")
	String jatsXmlBasePath;
	
	@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8000"})
	@RequestMapping(method = RequestMethod.GET)
	public ArticleModel myMethod() {
		try {
			System.out.println("shashank  " + jatsXmlBasePath);
			return xmlParserService.parseXML(jatsXmlBasePath + "/CIR.2017.0831.xml");
		} catch (XPathExpressionException | ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
			return null;
		}
		// return processXml (new File("/sample.xml"));
	}

	/*
	 * Test processXml(File xmlSourceDocument) {
	 * 
	 * // equivalent of ajax call. // url call and get response in custom obj form.
	 * RestTemplate template = new RestTemplate(); String res = null; try { res =
	 * template.getForObject(new URI("http://localhost:8000/news"), String.class); }
	 * catch (RestClientException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } catch (URISyntaxException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } return new Test(res); }
	 */

	/*
	 * static class Test { private String res;
	 * 
	 * public Test(String res) { this.res = res; }
	 * 
	 * public String getRes() { return res; }
	 * 
	 * public void setRes(String res) { this.res = res; }
	 * 
	 * }
	 */

}
