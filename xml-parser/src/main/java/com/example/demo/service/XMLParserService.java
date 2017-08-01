package com.example.demo.service;

import java.io.IOException;
import java.util.Collections;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.example.demo.constant.JATSXPathConstants;
import com.example.demo.helper.XMLtoHTMLTagHelper;
import com.example.demo.models.Affiliation;
import com.example.demo.models.ArticleFullTextModel;
import com.example.demo.models.ArticleMetaModel;
import com.example.demo.models.ArticleModel;
import com.example.demo.models.ArticleReferenceModel;
import com.example.demo.models.Author;

@Service
public class XMLParserService {

	@Value(value = "${jats.xml.doctype}")
	String jatsDocType;

	@Value(value = "${nlm.xml.doctype}")
	String nlmDocType;

	static boolean isJATSXml = false;
	static boolean isNLMXml = false;

	public ArticleModel parseXML(String inputFile)
			throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		Document document = builder.parse(inputFile);

		String docType = checkXMLDocType(document);

		isJATSXml = (docType.equals("JATS"));
		isNLMXml = docType.equals("NLM");

		if (isJATSXml || isNLMXml) {
			XMLtoHTMLTagHelper.xmlToHtml(document);

			if (isJATSXml) {
				return processJatsArticle(document);
			}
		}
		else {
			System.out.println("========We only support JATS and NLM========");
			System.out.println("========Please check your input XML for the docType and try again :)========");
			System.out.println("====Please provide input in these formats====");
		}

		return null;
	}

	private String checkXMLDocType(Document document) {
		if (document.getDoctype().getPublicId().equals(jatsDocType)) {
			return "JATS";
		} else if (document.getDoctype().getPublicId().equals(jatsDocType)) {
			return "NLM";
		}
		return "UNSUPPORTED_DOCTYPE";
	}

	public ArticleModel processJatsArticle(Document document) throws XPathExpressionException {
		ArticleModel articleModel = new ArticleModel();

		Node article_front = XMLtoHTMLTagHelper.getNode(JATSXPathConstants.ARTICLE_FRONT, document);
		ArticleMetaModel articleMetadata = getArticleMetadata(article_front);

		articleModel.setArticleMetadata(articleMetadata);

		Node article_body = XMLtoHTMLTagHelper.getNode(JATSXPathConstants.ARTICLE_BODY, document);
		ArticleFullTextModel articleFulltext = getArticleFullText(article_body);

		articleModel.setArticleFulltext(articleFulltext);

		Node article_back = XMLtoHTMLTagHelper.getNode(JATSXPathConstants.ARTICLE_BACK, document);
		ArticleReferenceModel articleReference = getArticleReference(article_back);

		articleModel.setArticleReferenceModel(articleReference);

		return articleModel;
	}

	private ArticleMetaModel getArticleMetadata(Node article_front) throws XPathExpressionException {
		ArticleMetaModel articleMeta = new ArticleMetaModel();

		// set doi
		Node doi = XMLtoHTMLTagHelper.getNode(JATSXPathConstants.DOI, article_front);
		if (doi != null) {
			articleMeta.setDoi(doi.getTextContent());
		}

		// set Journal title
		Node journalNameNode = XMLtoHTMLTagHelper.getNode(JATSXPathConstants.JOURNAL_TITLE, article_front);
		if (journalNameNode != null) {
			articleMeta.setJournal(journalNameNode.getTextContent());
		}

		// set article title
		Node articleTitle = XMLtoHTMLTagHelper.getNode(JATSXPathConstants.ARTICLE_TITLE, article_front);
		if (articleTitle != null) {
			articleMeta.setTitle(articleTitle);
		}

		// set issue volume
		try {
			Node volume = XMLtoHTMLTagHelper.getNode(JATSXPathConstants.VOLUME, article_front);
			if (volume != null) {
				articleMeta.setVolume(Integer.parseInt(volume.getTextContent()));
			}
			Node issue = XMLtoHTMLTagHelper.getNode(JATSXPathConstants.ISSUE, article_front);
			if (issue != null) {
				articleMeta.setIssue(Integer.parseInt(issue.getTextContent()));
			}
		} catch (NumberFormatException e) {
			System.err.println("Error: volume and issue must be numbers");
			e.printStackTrace();
		}

		// set article number
		Node electronicNumber = XMLtoHTMLTagHelper.getNode(JATSXPathConstants.ENUMBER, article_front);
		if (electronicNumber != null) {
			articleMeta.setId(electronicNumber.getTextContent());
		}

		// set article year
		try {
			Node year = XMLtoHTMLTagHelper.getNode(JATSXPathConstants.YEAR, article_front);
			if (year != null) {
				articleMeta.setYear(Integer.parseInt(year.getTextContent()));
			}
		} catch (NumberFormatException e) {
			System.err.println("Error: year must be a valid number");
			e.printStackTrace();
		}

		// set article month
		try {
			Node month = XMLtoHTMLTagHelper.getNode(JATSXPathConstants.MONTH, article_front);
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
		NodeList keywords = XMLtoHTMLTagHelper.getNodeList(JATSXPathConstants.KEYWORDS, article_front);
		for (int i = 0; i < keywords.getLength(); i++) {
			articleMeta.getKeywords().add(keywords.item(i).getTextContent());
		}

		Node contribGroup = XMLtoHTMLTagHelper.getNode(JATSXPathConstants.CONTRIBGROUP, article_front);

		NodeList affs = XMLtoHTMLTagHelper.getNodeList(JATSXPathConstants.AFFIL, article_front);
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
		NodeList contribs = XMLtoHTMLTagHelper.getNodeList("contrib", contribGroup);
		for (int i = 0; i < contribs.getLength(); i++) {
			Node contrib = contribs.item(i);

			Author author = new Author();
			Node surname = XMLtoHTMLTagHelper.getNode("name/surname", contrib);
			if (surname != null) {
				author.setSurname(surname.getTextContent());
			}
			Node given = XMLtoHTMLTagHelper.getNode("name/given-names", contrib);
			if (given != null) {
				author.setGiven(given.getTextContent());
			}

			NodeList xref = XMLtoHTMLTagHelper.getNodeList("xref[@ref-type='aff']", contrib);
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

			Node email = XMLtoHTMLTagHelper.getNode("email", contrib);
			if (email != null) {
				author.setEmail(email.getTextContent());
			}

			articleMeta.getAuthors().add(author);

		}

		// set abstract
		Node description = XMLtoHTMLTagHelper.getNode(JATSXPathConstants.ABSTRACT, article_front);
		if (description != null) {
			articleMeta.setDescription(description);
		}

		Node articleType = XMLtoHTMLTagHelper.getNode("/article", article_front);
		if (articleType != null) {
			articleMeta.setArticleType(articleType.getAttributes().getNamedItem("article-type").getTextContent());
		}

		return articleMeta;
	}

	private ArticleFullTextModel getArticleFullText(Node article_body) throws XPathExpressionException {
		// TODO : Parse body from XML to generate FullText HTML
		return null;
	}

	private ArticleReferenceModel getArticleReference(Node article_back) throws XPathExpressionException {
		// TODO : Parse Back content from XML to extract references
		return null;
	}
}
