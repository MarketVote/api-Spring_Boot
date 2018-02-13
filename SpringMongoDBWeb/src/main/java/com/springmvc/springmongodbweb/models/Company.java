package com.springmvc.springmongodbweb.models;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "companies")
public class Company {
	
	@Id
	String id;
	String name;
	String logoUrl;
	List<IndustryTag> industryTags;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getLogoUrl() {
		return logoUrl;
	}
	
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	
	public List<IndustryTag> getIndustryTags() {
		return industryTags;
	}
	
	public void setIndustryTags(List<IndustryTag> industryTags) {
		this.industryTags = industryTags;
	}
}
