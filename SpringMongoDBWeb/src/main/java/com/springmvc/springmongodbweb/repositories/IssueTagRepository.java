package com.springmvc.springmongodbweb.repositories;

import com.springmvc.springmongodbweb.models.IssueTag;
import org.springframework.data.repository.CrudRepository;

public interface IssueTagRepository extends CrudRepository<IssueTag, String> {
    @Override
    IssueTag findOne(String id);

    @Override
    void delete(IssueTag deleted);
    
    IssueTag findByName(String name);
}