package com.pay.payitem.business;

import java.util.List;
import java.util.Optional;

import com.pay.payitem.model.Chapter;
import com.pay.payitem.repository.ArticleRepository;
import com.pay.payitem.repository.ChapterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChapterBusiness {

    @Autowired
    private ChapterRepository chapterRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public List<Chapter> getChaptersOfOrganism(int idOrganism) {
        return chapterRepository.findByOrganism(idOrganism);
    }

    public List<Chapter> getChaptersOfOrganismCreatedByUser(int idOrganism) {
        return chapterRepository.findByOrganismAndSystemCreated(idOrganism, false);
    }

    public Optional<Chapter> getChaptersOfOrganismByCode(int idOrganism, int code) {
        List<Chapter> list = chapterRepository.findByOrganismAndCode(idOrganism, code);
        if (!list.isEmpty()) {
            return Optional.of(list.get(0));
        } else
            return Optional.empty();

    }

    public Chapter createChapter(Chapter chapter) {
        chapter.setSystemCreated(false);
        return chapterRepository.save(chapter);
    }

    public Chapter updateChapter(int id, Chapter chapter) {
        Chapter chapter1 = chapterRepository.findById(id).get();
       
        if (chapter.getCode() > 0)
            chapter1.setCode(chapter.getCode());
        if (chapter.getDesign() != null)
            chapter1.setDesign(chapter.getDesign());
        if (chapter.getDescription() != null)
            chapter1.setDescription(chapter.getDescription());
        return chapterRepository.save(chapter1);

    }

    public boolean deleteChapter(int idChapter) {
        if (!articleRepository.existsArticleByChapter(idChapter)) {
            chapterRepository.deleteById(idChapter);
            return true;
        } else
            return false;

    }

}
