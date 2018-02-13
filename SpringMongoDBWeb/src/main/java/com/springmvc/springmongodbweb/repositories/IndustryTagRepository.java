package com.springmvc.springmongodbweb.repositories;

import com.springmvc.springmongodbweb.models.IndustryTag;
import org.springframework.data.repository.CrudRepository;

public interface IndustryTagRepository extends CrudRepository<IndustryTag, String> {
    @Override
    IndustryTag findOne(String id);

    @Override
    void delete(IndustryTag deleted);
    
    IndustryTag findByName(String name);
}