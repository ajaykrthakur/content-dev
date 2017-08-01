package com.example.demo.models;

public class ArticleModel {

	private ArticleMetaModel articleMetadata;
	private ArticleFullTextModel articleFulltext;
	private ArticleReferenceModel articleReferenceModel;

	public ArticleFullTextModel getArticleFulltext() {
		return articleFulltext;
	}

	public void setArticleFulltext(ArticleFullTextModel articleFulltext) {
		this.articleFulltext = articleFulltext;
	}

	public ArticleMetaModel getArticleMetadata() {
		return articleMetadata;
	}

	public void setArticleMetadata(ArticleMetaModel articleMetadata) {
		this.articleMetadata = articleMetadata;
	}

	public ArticleReferenceModel getArticleReferenceModel() {
		return articleReferenceModel;
	}

	public void setArticleReferenceModel(ArticleReferenceModel articleReferenceModel) {
		this.articleReferenceModel = articleReferenceModel;
	}

}
