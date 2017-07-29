package com.example.demo.constant;

public interface JATSXPath {

	String JOURNAL = "/article/front/journal-meta/journal-id[@journal-id-type='publisher']";

	String ARTICLETITLE = "/article/front/article-meta/title-group/article-title";

	String DOI = "/article/front/article-meta/article-id[@pub-id-type='doi']";

	String CONTRIBGROUP = "/article/front/article-meta/contrib-group";

	String VOLUME = "/article/front/article-meta/volume";

	String ISSUE = "/article/front/article-meta/issue";

	String ENUMBER = "/article/front/article-meta/elocation-id";

	String YEAR = "/article/front/article-meta/pub-date/year";

	String MONTH = "/article/front/article-meta/pub-date/month";

	String KEYWORDS = "/article/front/article-meta/kwd-group/kwd";

}
