package com.pay.payitem.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pay.payitem.model.Article;
import com.pay.payitem.repository.ArticleRepository;
import com.pay.payitem.repository.ChapterRepository;
import com.pay.payitem.repository.RubricRepository;

@Service
public class ArticleBusiness {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private RubricRepository rubricRepository;
    @Autowired
    private ChapterRepository chapterRepository;

    public Optional<Article> getArticle(int idArticle) {
        return articleRepository.findById(idArticle);
    }

    public Article findArticle(int idArticle) {
        return articleRepository.getById(idArticle);
    }

    public List<Article> getArticlesOfOrganism(int idOrganism) {
        return articleRepository.findByOrganism(idOrganism);
    }

    public List<Article> getArticlesOfOrganismCreatedByUser(int idOrganism) {
        return articleRepository.findByOrganismAndSystemCreated(idOrganism, false);
    }

    public Optional<Article> getArticleOfOrganismByCode(int idOrganism, int code) {
        List<Article> list = articleRepository.findByOrganismAndCode(idOrganism, code);
        if (!list.isEmpty()) {
            return Optional.of(list.get(0));
        } else
            return Optional.empty();
    }

    @Transactional
    public Article createArticle(Article article) {
        article.setSystemCreated(false);
        Article article1 = articleRepository.save(article);
        int id = article1.getChapter().getId();
        chapterRepository.findById(id).ifPresent(article1::setChapter);
        return article1;
    }

    @Transactional
    public Article updateArticle(int id, Article article) {

        article.setId(id);
        return articleRepository.save(article);

    }

    @Transactional
    public boolean deleteArticle(int idArticle) {

        Optional<Article> article = articleRepository.findById(idArticle);
        if (!article.isPresent())
            return false;

        if (article.get().isSystemCreated())
            return false;

        if (!rubricRepository.existsByArticle_id(idArticle)) {
            try {
                articleRepository.deleteById(idArticle);
                return true;
            } catch (Exception e) {
                return false;
            }

        } else
            return false;

    }
}
