package com.springmvc.springmongodbweb.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import com.springmvc.springmongodbweb.models.Article;
import com.springmvc.springmongodbweb.models.Blurb;
import com.springmvc.springmongodbweb.models.Company;
import com.springmvc.springmongodbweb.models.IndustryTag;
import com.springmvc.springmongodbweb.models.IssueTag;
import com.springmvc.springmongodbweb.repositories.ArticleRepository;
import com.springmvc.springmongodbweb.repositories.CompanyRepository;
import com.springmvc.springmongodbweb.repositories.IndustryTagRepository;
import com.springmvc.springmongodbweb.repositories.IssueTagRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/marketvote")
public class MarketVoteController {

    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    IssueTagRepository issueTagRepository;
    @Autowired
    IndustryTagRepository industryTagRepository;
    
    public static final String kStringSuccessful = "The Articles have been created";
    public static final String kClickAdded = "The article has been updated with the clicks";
    

    @RequestMapping(value = "/article/create/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createArticles(@RequestBody List<Article> articles) {
    	ListIterator<Article> articleItr = articles.listIterator();
    	if(articleItr != null){
    		while(articleItr.hasNext()){
    			Article article = articleItr.next();
    			List<Blurb> createdConBlurbs = new ArrayList<Blurb>();
    	    	List<Blurb> createdProBlurbs = new ArrayList<Blurb>();
    	    	List<IssueTag> createdIssueTags = new ArrayList<IssueTag>();
    	    	List<IndustryTag> createdIndustryTags = new ArrayList<IndustryTag>();
    	    	
    	    	//adding createdDate
    	    	Date date = new Date();
    	    	article.setCreatedDate(date);
    	    	//creating blurbs
    	        List<Blurb> conBlurbs = article.getConBlurb();
    	        createdConBlurbs = createBlurbs(conBlurbs);
    	        article.setConBlurb(createdConBlurbs);
    	        List<Blurb> proBlurbs = article.getProBlurb();
    	        createdProBlurbs = createBlurbs(proBlurbs);
    	        article.setProBlurb(createdProBlurbs);
    	        
    	        //creating IssueTags
    	        List<IssueTag> issueTags = article.getIssueTags();
    	        createdIssueTags = createIssueTags(issueTags);
    	        article.setIssueTags(createdIssueTags);
    	        
    	        //creating IndustryTags
    	        List<IndustryTag> industryTags = article.getIndustryTags();
    	        createdIndustryTags = createIndustryTags(industryTags);
    	        article.setIndustryTags(createdIndustryTags);
    	        
    	        Article createdArticle = articleRepository.save(article);
    		}
    	}
        
        return MarketVoteController.kStringSuccessful;
    }
    
    @RequestMapping(value = "/article/get/all/", method = RequestMethod.GET)
    public List<Article> getArticles(){
    	Iterable<Article> existingArticlesItr = articleRepository.findAll();
    	List<Article> existingArticles = convertFromIteratorToList(existingArticlesItr);
    	return existingArticles;
    }
    @RequestMapping(value = "/article/get/{id}/", method = RequestMethod.GET)
    public Article getArticleById(@PathVariable String id){
    	Article article = articleRepository.findOne(id);
    	return article;
    }
    
    @RequestMapping(value = "/article/conblurb/click/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String addConClick(@RequestBody Article article){
    	List<Blurb> updatedConBlurbs = article.getConBlurb();
    	Article existingArticle = articleRepository.findOne(article.getId());
    	existingArticle.setConBlurb(updatedConBlurbs);
    	Article updatedArticle = articleRepository.save(existingArticle);
    	return MarketVoteController.kClickAdded;
    }
    
    @RequestMapping(value = "/article/problurb/click/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String addProClick(Article article){
    	List<Blurb> updatedProBlurbs = article.getProBlurb();
    	Article existingArticle = articleRepository.findOne(article.getId());
    	existingArticle.setConBlurb(updatedProBlurbs);
    	Article updatedArticle = articleRepository.save(existingArticle);
    	return MarketVoteController.kClickAdded;
    }
    
    public List<Blurb> createBlurbs(List<Blurb> blurbs){
    	Company createdCompany = null;
    	List<Blurb> createdBlurbs = new ArrayList<Blurb>();
    	ListIterator<Blurb> blurbItr = blurbs.listIterator();
        if(blurbItr != null){
        	while (blurbItr.hasNext()){
        		Blurb blurbObject = blurbItr.next();
        		Company company = blurbObject.getCompany();
        		//String companyName = company.getName();
        		Company existingCompany = companyRepository.findByName(company.getName());
        		if(existingCompany == null){
        			createdCompany = companyRepository.save(company);
        		}
        		else{
        			createdCompany = existingCompany;
        		}
        		blurbObject.setCompany(createdCompany);
        		createdBlurbs.add(blurbObject);
        	}
        }
        return createdBlurbs;
    }
    
    public List<IssueTag> createIssueTags(List<IssueTag> issueTags){
    	IssueTag createdIssueTag = null;
    	List<IssueTag> createdIssueTags= new ArrayList<IssueTag>();
    	ListIterator<IssueTag> issueItr = issueTags.listIterator();
        if(issueItr != null){
        	while (issueItr.hasNext()){
        		IssueTag issueObject = issueItr.next();
        		IssueTag existingIssueTag = issueTagRepository.findByName(issueObject.getName());
        		if(existingIssueTag == null){
        			createdIssueTag = issueTagRepository.save(issueObject);
        		}
        		else{
        			createdIssueTag = existingIssueTag;
        		}
        		createdIssueTags.add(createdIssueTag);
        	}
        }
        return createdIssueTags;
    }
    
    public List<IndustryTag> createIndustryTags(List<IndustryTag> industryTags){
    	IndustryTag createdIndustryTag = null;
    	List<IndustryTag> createdIndustryTags= new ArrayList<IndustryTag>();
    	ListIterator<IndustryTag> industryItr = industryTags.listIterator();
        if(industryItr != null){
        	while (industryItr.hasNext()){
        		IndustryTag industryObject = industryItr.next();
        		IndustryTag existingIndustryTag = industryTagRepository.findByName(industryObject.getName());
        		if(existingIndustryTag == null){
        			createdIndustryTag = industryTagRepository.save(industryObject);
        		}
        		else{
        			createdIndustryTag = existingIndustryTag;
        		}
        		createdIndustryTags.add(createdIndustryTag);
        	}
        }
        return createdIndustryTags;
    }
    
    public List<Article> convertFromIteratorToList(Iterable<Article> existingArticlesItr){
    	if(existingArticlesItr instanceof List) {
    		return (List<Article>) existingArticlesItr;
    	}
    	ArrayList<Article> list = new ArrayList<Article>();
    	if(existingArticlesItr != null) {
    		for(Article article: existingArticlesItr) {
    			list.add(article);
    	    }
    	}
    	return list;
    }
}
