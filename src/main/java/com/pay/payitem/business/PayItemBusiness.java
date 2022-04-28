package com.pay.payitem.business;

import com.pay.payitem.model.Article;
import com.pay.payitem.model.Chapter;
import com.pay.payitem.repository.ArticleRepository;
import com.pay.payitem.repository.ChapterRepository;
import com.pay.payitem.repository.RubricRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayItemBusiness {
    @Autowired
    private ChapterRepository chapterRepository;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private RubricRepository rubricRepository;

public boolean initializeOrganism(int idOrganism){
    Chapter chapter3111 = new Chapter();
    chapter3111.setCode(1);
    chapter3111.setDesign("design");
    chapter3111.setDescription("description");
    chapter3111.setOrganism(idOrganism);
    chapter3111.setSystemCreated(true);
    chapterRepository.save(chapter3111);
    return true;
}
    
}
