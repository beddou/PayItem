package com.pay.payItem.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.payItem.model.Chapter;
import com.pay.payItem.repository.ArticleRepository;
import com.pay.payItem.repository.ChapterRepository;

@Service
public class ChapterBusiness {

    @Autowired
    private ChapterRepository chapterRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public Optional<Chapter> getChapter(int idChapter) {
        return chapterRepository.findById(idChapter);
    }

    public List<Chapter> getChaptersOfOrganism(int idOrganism) {
        return chapterRepository.findByOrganism(idOrganism);
    }

    public List<Chapter> getChaptersOfOrganismCreatedByUser(int idOrganism) {
        return chapterRepository.findByOrganismAndSystemCreated(idOrganism, false);
    }

    public Optional<Chapter> getChaptersOfOrganismByCode(int code, int idOrganism) {
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

        Optional<Chapter> chapter1 = chapterRepository.findById(id);
        Chapter chapter2 = new Chapter();
        if (chapter1.isPresent()) {
            chapter2 = chapter1.get();
            if (chapter.getCode() > 0)
                chapter2.setCode(chapter.getCode());

            if (chapter.getDesign() != null && !chapter.getDesign().equals("")
                    && !chapter.getDesign().trim().equals(""))

                chapter2.setDesign(chapter.getDesign());

            if (chapter.getDescription() != null && !chapter.getDescription().equals("")
                    && !chapter.getDescription().trim().equals(""))

                chapter2.setDescription(chapter.getDescription());
        }

        return chapterRepository.save(chapter2);

    }

    public boolean deleteChapter(int idChapter) {

        Optional<Chapter> chapter = chapterRepository.findById(idChapter);
        if (!chapter.isPresent())
            return false;
        if (chapter.get().isSystemCreated())
            return false;
        if (!articleRepository.existsByChapter_id(idChapter)) {
            chapterRepository.deleteById(idChapter);
            return true;
        } else
            return false;

    }

}
