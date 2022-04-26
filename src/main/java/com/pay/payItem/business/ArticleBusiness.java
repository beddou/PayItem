package com.pay.payItem.business;

import java.util.List;
import java.util.Optional;

import com.pay.payItem.model.Article;
import com.pay.payItem.model.article;
import com.pay.payItem.repository.ArticleRepository;
import com.pay.payItem.repository.RubricRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleBusiness {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private RubricRepository rubricRepository;

    public List<Article> getArticlesOfOrganism(int idOrganism) {
        return articleRepository.findByOrganism(idOrganism);
    }

    public List<Article> getArticlesOfOrganismCreatedByUser(int idOrganism) {
        return articleRepository.findByOrganismAndSystemcreated(idOrganism, false);
    }

    public Optional<Article> getArticleOfOrganismByCode(int idOrganism, int code) {
        List<Article> list = articleRepository.findByOrganismAndCode(idOrganism, code);
        if (!list.isEmpty()) {
            return Optional.of(list.get(0));
        } else
            return Optional.empty();
    }

    public Article createArticle(Article article) {
        article.setSystemCreated(false);
        return articleRepository.save(article);
    }

    public Article updateArticle(int id, Article article) {
        Article article1 = articleRepository.findById(id).get();
        
        if (article.getCode() > 0)
            article1.setCode(article.getCode());
        if (article.getDesign() != null)
            article1.setDesign(article.getDesign());

        return articleRepository.save(article1);

    }

    public boolean deleteArticle(int idArticle) {
        if (!rubricRepository.existByArticle(idArticle)) {
            articleRepository.deleteById(idArticle);
            return true;
        } else
            return false;

    }
}
