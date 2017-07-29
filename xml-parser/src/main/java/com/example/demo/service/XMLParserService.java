package com.example.demo.service;

import java.io.IOException;
import java.util.Collections;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.example.demo.constant.JATSXPath;
import com.example.demo.models.Affiliation;
import com.example.demo.models.ArticleMeta;
import com.example.demo.models.Author;

@Service
public class XMLParserService {

	public ArticleMeta parseXML(String inputFile)
			throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		Document document = builder.parse(inputFile);

		XPath xPath = XPathFactory.newInstance().newXPath();

		return jatsParser(document, xPath);

	}

	private ArticleMeta jatsParser(Document document, XPath xPath) throws XPathExpressionException {
		ArticleMeta articleMeta = new ArticleMeta();

		// set doi
		Node doi = (Node) xPath.compile(JATSXPath.DOI).evaluate(document, XPathConstants.NODE);
		if (doi != null) {
			articleMeta.setDoi(doi.getTextContent());
		}

		// set Journal title
		Node journalNameNode = (Node) xPath.compile(JATSXPath.JOURNALTITLE).evaluate(document, XPathConstants.NODE);
		if (journalNameNode != null) {
			articleMeta.setJournal(journalNameNode.getTextContent());
		}
		// set article title
		NodeList articleTitle = (NodeList) xPath.compile(JATSXPath.ARTICLETITLE).evaluate(document,
				XPathConstants.NODESET);
		for (int i = 0; i < articleTitle.getLength(); i++) {
			articleMeta.setTitle(articleTitle.item(i).getTextContent());
		}

		// set article volume
		try {
			Node volume = (Node) xPath.compile(JATSXPath.VOLUME).evaluate(document, XPathConstants.NODE);
			if (volume != null) {
				articleMeta.setVolume(Integer.parseInt(volume.getTextContent()));
			}
			Node issue = (Node) xPath.compile(JATSXPath.ISSUE).evaluate(document, XPathConstants.NODE);
			if (issue != null) {
				articleMeta.setIssue(Integer.parseInt(issue.getTextContent()));
			}
		} catch (NumberFormatException e) {
			System.err.println("Error: volume and issue must be numbers");
			e.printStackTrace();
		}

		// set article number
		Node electronicNumber = (Node) xPath.compile(JATSXPath.ENUMBER).evaluate(document, XPathConstants.NODE);
		if (electronicNumber != null) {
			articleMeta.setId(electronicNumber.getTextContent());
		}

		// set article year
		try {
			Node year = (Node) xPath.compile(JATSXPath.YEAR).evaluate(document, XPathConstants.NODE);
			if (year != null) {
				articleMeta.setYear(Integer.parseInt(year.getTextContent()));
			}
		} catch (NumberFormatException e) {
			System.err.println("Error: year must be a valid number");
			e.printStackTrace();
		}

		// set article month
		try {
			Node month = (Node) xPath.compile(JATSXPath.MONTH).evaluate(document, XPathConstants.NODE);
			if (month != null) {
				String monthString;
				switch (Integer.parseInt(month.getTextContent())) {
				case 1:
					monthString = "January";
					break;
				case 2:
					monthString = "February";
					break;
				case 3:
					monthString = "March";
					break;
				case 4:
					monthString = "April";
					break;
				case 5:
					monthString = "May";
					break;
				case 6:
					monthString = "June";
					break;
				case 7:
					monthString = "July";
					break;
				case 8:
					monthString = "August";
					break;
				case 9:
					monthString = "September";
					break;
				case 10:
					monthString = "October";
					break;
				case 11:
					monthString = "November";
					break;
				case 12:
					monthString = "December";
					break;
				default:
					monthString = "Invalid month";
					break;
				}
				articleMeta.setMonth(monthString);
			}
		} catch (NumberFormatException e) {
			System.err.println("Month should be a valid number from 1 to 12");
			e.printStackTrace();
		}

		// set keywords
		NodeList keywords = (NodeList) xPath.compile(JATSXPath.KEYWORDS).evaluate(document, XPathConstants.NODESET);
		for (int i = 0; i < keywords.getLength(); i++) {
			articleMeta.getKeywords().add(keywords.item(i).getTextContent());
		}

		Node contribGroup = (Node) xPath.compile(JATSXPath.CONTRIBGROUP).evaluate(document, XPathConstants.NODE);

		NodeList affs = (NodeList) xPath.compile(JATSXPath.AFFIL).evaluate(document, XPathConstants.NODESET);
		if (affs != null) {
			for (int i = 0; i < affs.getLength(); i++) {
				Affiliation affiliation = new Affiliation();
				affiliation.setAffString(affs.item(i).getTextContent());
				affiliation.setOrder(i + 1);
				affiliation.setRid(affs.item(i).getAttributes().getNamedItem("id").getTextContent());
				articleMeta.getAffiliations().add(affiliation);
			}
		}

		// set authors
		NodeList contribs = (NodeList) xPath.compile("contrib").evaluate(contribGroup, XPathConstants.NODESET);
		for (int i = 0; i < contribs.getLength(); i++) {
			Node contrib = contribs.item(i);

			Author author = new Author();
			Node surname = (Node) xPath.compile("name/surname").evaluate(contrib, XPathConstants.NODE);
			if (surname != null) {
				author.setSurname(surname.getTextContent());
			}
			Node given = (Node) xPath.compile("name/given-names").evaluate(contrib, XPathConstants.NODE);
			if (given != null) {
				author.setGiven(given.getTextContent());
			}

			NodeList xref = (NodeList) xPath.compile("xref[@ref-type='aff']").evaluate(contrib, XPathConstants.NODESET);
			if (xref != null) {
				for (int j = 0; j < xref.getLength(); j++) {
					String aff = xref.item(j).getAttributes().getNamedItem("rid").getTextContent();
					for (Affiliation affiliation : articleMeta.getAffiliations()) {
						if (affiliation.getRid().equals(aff)) {
							author.getAffils().add(affiliation.getOrder());
						}
					}
				}
			}
			Collections.sort(author.getAffils());

			Node email = (Node) xPath.compile("email").evaluate(contrib, XPathConstants.NODE);
			if (email != null) {
				author.setEmail(email.getTextContent());
			}

			articleMeta.getAuthors().add(author);

		}

		// set abstract
		Node description = (Node) xPath.compile(JATSXPath.ABSTRACT).evaluate(document, XPathConstants.NODE);
		if (description != null) {
			articleMeta.setDescription(description.getTextContent());
		}

		return articleMeta;
	}
}
