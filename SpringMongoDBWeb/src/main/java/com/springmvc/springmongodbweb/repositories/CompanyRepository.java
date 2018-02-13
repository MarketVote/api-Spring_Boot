package com.springmvc.springmongodbweb.repositories;


import com.springmvc.springmongodbweb.models.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, String> {
    @Override
    Company findOne(String id);

    @Override
    void delete(Company deleted);
    
    Company findByName(String Name);
}