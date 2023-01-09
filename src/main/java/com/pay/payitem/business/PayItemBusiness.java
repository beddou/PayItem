package com.pay.payitem.business;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.payitem.model.Article;
import com.pay.payitem.model.Chapter;
import com.pay.payitem.model.Rubric;
import com.pay.payitem.model.tools.ValueType;
import com.pay.payitem.repository.ArticleRepository;
import com.pay.payitem.repository.ChapterRepository;
import com.pay.payitem.repository.RubricRepository;

@Service
public class PayItemBusiness {
    @Autowired
    private ChapterRepository chapterRepository;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private RubricRepository rubricRepository;

    @Transactional
    public boolean initializeOrganism(int idOrganism) {
       boolean success = false;

       try {
        /* Création des chapitres */
        Chapter chapter3111 = new Chapter( "31-11",
                "rémunération principale", true, idOrganism);
        chapterRepository.save(chapter3111);

        Chapter chapter3112 = new Chapter( "31-12",
                "indemnités", true, idOrganism);
        chapterRepository.save(chapter3112);

        Chapter chapter3311 = new Chapter( "33-11",
                "allocations familiales", true, idOrganism);
        chapterRepository.save(chapter3311);

        /* Création des articles */
        Article art1 = new Article(1, "Article.1", true, chapter3111, idOrganism);
        articleRepository.save(art1);
        Article art2 = new Article(2, "Article.2", true, chapter3111, idOrganism);
        articleRepository.save(art2);
        Article art3 = new Article(3, "Article.3", true, chapter3111, idOrganism);
        articleRepository.save(art3);
        Article artN = new Article(4, "Article.N", true, chapter3112, idOrganism);
        articleRepository.save(artN);
        Article artU = new Article(5, "Article.U", true, chapter3311, idOrganism);
        articleRepository.save(artU);

        /* Création des rubriques */
        rubricRepository.save(new Rubric(1, "NJA", ValueType.NUMBER, false, true, false,
                false, false, false, -1, null, null, idOrganism));

        rubricRepository.save(new Rubric(2, "NJM", ValueType.NUMBER, false, true, false,
                false, false, false, -2, null, null, idOrganism));

        rubricRepository.save(new Rubric(10, "NJT", ValueType.NUMBER, false, true, true,
                false, false, false, -3, null, null, idOrganism));

        rubricRepository.save(new Rubric(11, "RJT", ValueType.NUMBER, false, true, true,
                false, false, false, -4, null, null, idOrganism));

        rubricRepository.save(new Rubric(101, "SB", ValueType.MOUNT, false, true, true,
                true, true, true, 1, art2, chapter3111, idOrganism));

        rubricRepository.save(new Rubric(108, "IEP", ValueType.MOUNT, false, true, true,
                true, true, true, 2, art2, chapter3111, idOrganism));

        rubricRepository.save(new Rubric(204, "Mutuelle", ValueType.MOUNT, true, true, false,
                false, false, false, -5, null, null, idOrganism));

        rubricRepository.save(new Rubric(506, "Salaire unique", ValueType.MOUNT, false, true, true,
                false, false, false, 1, artU, chapter3311, idOrganism));

        rubricRepository.save(new Rubric(504, "Allocation familiale", ValueType.MOUNT, false, true, true,
                false, false, false, 2, artU, chapter3311, idOrganism));

        rubricRepository.save(new Rubric(520, "Majoration d'enfants", ValueType.MOUNT, false, true, true,
                false, false, false, 3, artU, chapter3311, idOrganism));

        rubricRepository.save(new Rubric(400, "Retenu comptable", ValueType.MOUNT, true, true, false,
                false, false, false, 3, null, null, idOrganism));



        success = true;
       } catch (Exception e) {
        success = false;
       }
       return success;
        

    }

}
