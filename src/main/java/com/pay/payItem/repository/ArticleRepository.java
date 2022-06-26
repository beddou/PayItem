package com.pay.payItem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pay.payItem.model.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    boolean existsByChapter_id(int idChapter);
    List<Article> findByOrganism(int idOrganism);
    List<Article> findByOrganismAndSystemCreated(int idOrganism, boolean systemCreated);
    List<Article> findByOrganismAndCode(int idOrganism, int code);
    
}
