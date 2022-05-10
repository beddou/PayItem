package com.pay.payitem.controller;

import java.util.List;
import java.util.Optional;

import com.pay.payitem.business.ArticleBusiness;
import com.pay.payitem.exception.EntityNotFoundException;
import com.pay.payitem.exception.NoEntityAddedException;
import com.pay.payitem.model.Article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class ArticleController {
    @Autowired
    private ArticleBusiness articleBusiness;

    @GetMapping(value = "/PayItem/Article/get/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable("id") int id) {
        Optional<Article> article = articleBusiness.getArticle(id);
        if (article.isPresent()) {
            return new ResponseEntity<>(article.get(), HttpStatus.OK);
        }

        else {
            throw new EntityNotFoundException("Article not found");

        }

    }


    @GetMapping(value = "/PayItem/Article/getArticlesOfOrganism/{idOrganism}")
    public List<Article> getArticlesOfOrganism(@PathVariable("idOrganism") int idOrganism) {
        List<Article> articles = articleBusiness.getArticlesOfOrganism(idOrganism);
        if (articles.isEmpty()) {
            throw new EntityNotFoundException("article not found");
        } else
            return articles;

    }

    @GetMapping(value = "/PayItem/Article/getArticlesCreatedByUserOfOrganism/{idOrganism}")
    public List<Article> getArticlesCreatedByUserOfOrganism(@PathVariable("idOrganism") int idOrganism) {
        List<Article> articles = articleBusiness.getArticlesOfOrganismCreatedByUser(idOrganism);
        if (articles.isEmpty()) {
            throw new EntityNotFoundException("article not found");
        } else
            return articles;

    }

    @GetMapping(value = "/PayItem/Article/getArticlesByCodeOfOrganism/{code}/{idOrganism}")
    public ResponseEntity<Article> getArticlesByCodeOfOrganism(@PathVariable("code") int code,
            @PathVariable("idOrganism") int idOrganism) {
        Optional<Article> article = articleBusiness.getArticleOfOrganismByCode(idOrganism, code);
        if (article.isPresent()) {
            return new ResponseEntity<>(article.get(), HttpStatus.OK);
        }

        else {
            throw new EntityNotFoundException("Article not found");

        }

    }

    @PostMapping(value = "/PayItem/Article/create")
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        try {
            Article article1 = articleBusiness.createArticle(article);
            return new ResponseEntity<>(article1 , HttpStatus.CREATED);
        } catch (Exception e) {
            throw new NoEntityAddedException("Article not saved");
        }

    }

    @PutMapping(value = "/PayItem/Article/update/{idArticle}")
    public ResponseEntity<Article> upDateArticle(@PathVariable("idArticle") int idArticle,
            @RequestBody Article article) {

        Optional<Article> article1 = articleBusiness.getArticle(idArticle);

        if (article1.isPresent()) {
            try {

                Article Article2 = articleBusiness.updateArticle(idArticle, article);
                return new ResponseEntity<>(Article2, HttpStatus.CREATED);

            } catch (Exception e) {
                throw new NoEntityAddedException("entity not saved");
            }

        } else {
            throw new EntityNotFoundException("document introuvable");

        }

    }

    @DeleteMapping(value = "/PayItem/Article/delete/{idArticle}")

    public ResponseEntity<Boolean> deleteArticle(@PathVariable("idArticle") int idArticle) {

        Optional<Article> article =  articleBusiness.getArticle(idArticle);
        if (!article.isPresent())
            throw new EntityNotFoundException("entity introuvable");

        return new ResponseEntity<>( articleBusiness.deleteArticle(idArticle), HttpStatus.OK);

    }

}