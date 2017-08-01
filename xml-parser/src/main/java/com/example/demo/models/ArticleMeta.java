package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

public class ArticleMeta {

	private String journal;
	private Object title;
	private int volume;
	private int issue;
	private String id;
	private String month;
	private int year;
	private List<String> keywords = new ArrayList<>();
	private String doi;
	private List<Author> authors = new ArrayList<>();
	private List<Affiliation> affiliations = new ArrayList<>();
	private Object description;
	private String articleType;
	private Object fulltext;

	public String getJournal() {
		return journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}

	public Object getTitle() {
		return title;
	}

	public void setTitle(Object title) {
		this.title = title;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public int getIssue() {
		return issue;
	}

	public void setIssue(int issue) {
		this.issue = issue;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public String getDoi() {
		return doi;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public List<Affiliation> getAffiliations() {
		return affiliations;
	}

	public void setAffiliations(List<Affiliation> affiliations) {
		this.affiliations = affiliations;
	}

	public Object getDescription() {
		return description;
	}

	public void setDescription(Object description) {
		this.description = description;
	}

	public String getArticleType() {
		return articleType;
	}

	public void setArticleType(String articleType) {
		this.articleType = articleType;
	}

	public Object getFulltext() {
		return fulltext;
	}

	public void setFulltext(Object fulltext) {
		this.fulltext = fulltext;
	}
}
