package com.springmvc.springmongodbweb.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "articles")
public class Article {
	
	@Id
    String id;
	String title;
	String content;
	List<IndustryTag> industryTags;
	List<IssueTag> issueTags;
	String imageUrl;
	List<Blurb> conBlurbs;
	List<Blurb> proBlurbs;
	int likes;
	int dislikes;
	
	public Article() {
    }

    public Article(String title, String content, List<IndustryTag> industryTags, List<IssueTag> issueTags, String imageUrl) {
        this.title = title;
        this.content = content;
        this.industryTags = industryTags;
        this.issueTags = issueTags;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<IndustryTag> getIndustryTags() {
		return industryTags;
	}

	public void setIndustryTags(List<IndustryTag> industryTags) {
		this.industryTags = industryTags;
	}

	public List<IssueTag> getIssueTags() {
		return issueTags;
	}

	public void setIssueTags(List<IssueTag> issueTags) {
		this.issueTags = issueTags;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<Blurb> getConBlurb() {
		return conBlurbs;
	}

	public void setConBlurb(List<Blurb> conBlurbs) {
		this.conBlurbs = conBlurbs;
	}

	public List<Blurb> getProBlurb() {
		return proBlurbs;
	}

	public void setProBlurb(List<Blurb> proBlurbs) {
		this.proBlurbs = proBlurbs;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}
}





