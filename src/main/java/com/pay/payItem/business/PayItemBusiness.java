package com.pay.payItem.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.payItem.model.Article;
import com.pay.payItem.model.Chapter;
import com.pay.payItem.repository.ArticleRepository;
import com.pay.payItem.repository.ChapterRepository;
import com.pay.payItem.repository.RubricRepository;

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
