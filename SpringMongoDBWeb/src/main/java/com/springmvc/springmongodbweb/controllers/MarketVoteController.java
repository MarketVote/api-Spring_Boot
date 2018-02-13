package com.springmvc.springmongodbweb.controllers;

import java.util.ArrayList;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @RequestMapping(value = "/article/create/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String save(@RequestBody List<Article> articles) {
    	ListIterator<Article> articleItr = articles.listIterator();
    	if(articleItr != null){
    		while(articleItr.hasNext()){
    			Article article = articleItr.next();
    			List<Blurb> createdConBlurbs = new ArrayList<Blurb>();
    	    	List<Blurb> createdProBlurbs = new ArrayList<Blurb>();
    	    	List<IssueTag> createdIssueTags = new ArrayList<IssueTag>();
    	    	List<IndustryTag> createdIndustryTags = new ArrayList<IndustryTag>();
    	    	
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
    
    
    
    
   /* @RequestMapping("/show/{id}")
    public String show(@PathVariable String id, Model model) {
        model.addAttribute("product", productRepository.findOne(id));
        return "show";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam String id) {
        Product product = productRepository.findOne(id);
        productRepository.delete(product);

        return "redirect:/product";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        model.addAttribute("product", productRepository.findOne(id));
        return "edit";
    }

    @RequestMapping("/update")
    public String update(@RequestParam String id, @RequestParam String prodName, @RequestParam String prodDesc, @RequestParam Double prodPrice, @RequestParam String prodImage) {
        Product product = productRepository.findOne(id);
        product.setProdName(prodName);
        product.setProdDesc(prodDesc);
        product.setProdPrice(prodPrice);
        product.setProdImage(prodImage);
        productRepository.save(product);

        return product.getId();
    }

    @RequestMapping("/manufacturer")
    public String Manufacturer(Model model) {
        model.addAttribute("manufacturer", manufacturerRepository.findAll());
        return "manufacturer";
    }

    @RequestMapping("/man/create")
    //@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String manCreate(Model model) {
        return "create";
    }

    @RequestMapping("/man/save")
    public String manSave(@RequestParam String manName, @RequestParam String manDesc) {
        Manufacturer man = new Manufacturer();
        man.setManName(manName);
        man.setManDesc(manDesc);
        manufacturerRepository.save(man);
        return man.getId();
    }

    @RequestMapping("/man/show/{id}")
    public String manShow(@PathVariable String id, Model model) {
        model.addAttribute("manufacturer", manufacturerRepository.findOne(id));
        return "show";
    }

    @RequestMapping("/man/delete")
    public String manDelete(@RequestParam String id) {
        Manufacturer manufacturer = manufacturerRepository.findOne(id);
        manufacturerRepository.delete(manufacturer);

        return "redirect:/product";
    }

    @RequestMapping("/man/edit/{id}")
    public String manEdit(@PathVariable String id, Model model) {
        model.addAttribute("product", manufacturerRepository.findOne(id));
        return "edit";
    }

    @RequestMapping("/man/update")
    public String manUpdate(@RequestParam String id, @RequestParam String manName, @RequestParam String manDesc) {
        Manufacturer man = manufacturerRepository.findOne(id);
        man.setManName(manName);
        man.setManDesc(manDesc);
        manufacturerRepository.save(man);

        return man.getId();
    } */
}
