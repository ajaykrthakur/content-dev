package com.example.demo.helper;

import java.util.HashMap;
import java.util.Map;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLtoHTMLTagHelper {

	Map<String, String> xmlTagsMap = new HashMap<>();

	private static XPath xPath = XPathFactory.newInstance().newXPath();

	public static Document xmlToHtml(Document document) {

		NodeList nodes = document.getElementsByTagName("italic");
		for (int i = 0; i < nodes.getLength(); i++) {
			document.renameNode(nodes.item(i), null, "i");
		}
		return document;
	}

	void xmlMapper() {
		xmlTagsMap.put("italic", "i");
	}

	public static Node getNode(String nodeXPath, Object item) throws XPathExpressionException {
		return (Node) xPath.compile(nodeXPath).evaluate(item, XPathConstants.NODE);
	}

	public static NodeList getNodeList(String nodeXPath, Object item) throws XPathExpressionException {
		return (NodeList) xPath.compile(nodeXPath).evaluate(item, XPathConstants.NODESET);
	}
	
}
