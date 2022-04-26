package com.pay.payItem.repository;

import java.util.List;

import com.pay.payItem.model.Article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    boolean existByChapter(int idChapter);
    List<Article> findByOrganism(int idOrganism);
    List<Article> findByOrganismAndSystemcreated(int idOrganism, boolean systemCreated);
    List<Article> findByOrganismAndCode(int idOrganism, int code);
    
}
