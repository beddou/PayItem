package com.pay.payitem.business;

import java.util.List;
import java.util.Optional;

import com.pay.payitem.model.Article;
import com.pay.payitem.model.Chapter;
import com.pay.payitem.repository.ArticleRepository;
import com.pay.payitem.repository.ChapterRepository;
import com.pay.payitem.repository.RubricRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Optional<Article> article1 = articleRepository.findById(id);
        Article article2 = new Article();
        if (article1.isPresent()) {
            article2 = article1.get();
            if (article.getCode() > 0)
                article2.setCode(article.getCode());
            if (article.getDesign() != null && !article.getDesign().equals("")
                    && !article.getDesign().trim().equals(""))

                article2.setDesign(article.getDesign());

        }
        return articleRepository.save(article2);

    }

    @Transactional
    public boolean deleteArticle(int idArticle) {
        if (!rubricRepository.existsByArticle_id(idArticle)) {
            articleRepository.deleteById(idArticle);
            return true;
        } else
            return false;

    }
}
