package com.example.demo.helper;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class XMLtoHTMLTagHelper {

	Map<String, String> xmlTagsMap = new HashMap<>();

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
}
