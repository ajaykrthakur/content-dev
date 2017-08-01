package com.example.demo.constant;

public interface JATSXPathConstants {
	

	String ARTICLE_FRONT = "/article/front";
	
	String ARTICLE_BACK = "/article/back";
	
	String ARTICLE_BODY = "/article/body";
	
	String JOURNAL_TITLE = "journal-meta/journal-title-group/journal-title";

	String ARTICLE_TITLE = "article-meta/title-group/article-title";

	String DOI = "article-meta/article-id[@pub-id-type='doi']";

	String CONTRIBGROUP = "article-meta/contrib-group";
	
	String AFFIL = "article-meta/aff";

	String VOLUME = "article-meta/volume";

	String ISSUE = "article-meta/issue";

	String ENUMBER = "article-meta/elocation-id";

	String YEAR = "article-meta/pub-date/year";

	String MONTH = "article-meta/pub-date/month";

	String KEYWORDS = "article-meta/kwd-group/kwd";
	
	String ABSTRACT = "article-meta/abstract";

}
