package com.pay.payItem.business;

import com.pay.payItem.model.Article;
import com.pay.payItem.repository.ArticleRepository;
import com.pay.payItem.repository.ChapterRepository;
import com.pay.payItem.repository.RubricRepository;

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
    return true;
}
    
}
