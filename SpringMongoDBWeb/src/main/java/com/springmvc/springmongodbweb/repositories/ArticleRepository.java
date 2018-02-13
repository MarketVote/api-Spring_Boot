package com.springmvc.springmongodbweb.repositories;

import com.springmvc.springmongodbweb.models.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, String> {
    @Override
    Article findOne(String id);

    @Override
    void delete(Article deleted);
}