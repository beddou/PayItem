package com.pay.payitem.controller;


import java.util.List;
import java.util.Optional;

import com.pay.payitem.business.ChapterBusiness;
import com.pay.payitem.exception.EntityNotFoundException;
import com.pay.payitem.exception.NoEntityAddedException;
import com.pay.payitem.model.Chapter;

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
public class ChapterController {
    @Autowired
    private ChapterBusiness chapterBusiness;

    @GetMapping(value = "/PayItem/Chapter/All/{idOrganism}")
    public List<Chapter> getChaptersOfOrganism(@PathVariable("idOrganism") int idOrganism) {
        List<Chapter> chapters = chapterBusiness.getChaptersOfOrganism(idOrganism);
        if (chapters.isEmpty()) {
            throw new EntityNotFoundException("organism not found");
        } else
            return chapters;

    }

    @GetMapping(value = "/PayItem/Chapter/GetCreatedByUser/{idOrganism}")
    public List<Chapter> getChaptersCreatedByUserOfOrganism(@PathVariable("idOrganism") int idOrganism) {
        List<Chapter> chapters = chapterBusiness.getChaptersOfOrganismCreatedByUser(idOrganism);
        if (chapters.isEmpty()) {
            throw new EntityNotFoundException("organism not found");
        } else
            return chapters;

    }

    @GetMapping(value = "/PayItem/Chapter/GetByCode/{code}/{idOrganism}")
    public ResponseEntity<Chapter> getChaptersByCodeOfOrganism(@PathVariable("code") int code,
            @PathVariable("idOrganism") int idOrganism) {
        Optional<Chapter> chapter = chapterBusiness.getChaptersOfOrganismByCode(code, idOrganism);
        if (chapter.isPresent()) {
            return new ResponseEntity<>(chapter.get(), HttpStatus.OK);
        }

        else {
            throw new EntityNotFoundException("chapter not found");

        }

    }

    @PostMapping(value = "/PayItem/Chapter/Create")
    public ResponseEntity<Chapter> createChapter(@RequestBody Chapter chapter) {
        try {
            Chapter chapter1 = chapterBusiness.createChapter(chapter);
            return new ResponseEntity<>(chapter1, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new NoEntityAddedException("chapter not saved");
        }

    }

    @PutMapping(value = "/PayItem/Chapter/Update/{idChapter}")
    public ResponseEntity<Chapter> upDateChapter(@PathVariable("idChapter") int idChapter,
            @RequestBody Chapter chapter) {

        Optional<Chapter> chapter1 = chapterBusiness.getChapter(idChapter);

        if (chapter1.isPresent()) {
            try {

                Chapter chapter2 = chapterBusiness.updateChapter(idChapter, chapter);
                return new ResponseEntity<>(chapter2, HttpStatus.CREATED);

            } catch (Exception e) {
                throw new NoEntityAddedException("entity not saved");
            }

        } else {
            throw new EntityNotFoundException("document introuvable");

        }

    }

    @DeleteMapping(value = "/PayItem/Chapter/Delete/{idChapter}")

    public ResponseEntity<Boolean> deleteChapter(@PathVariable("idChapter") int idChapter) {

        Optional<Chapter> chapter = chapterBusiness.getChapter(idChapter);
        if (!chapter.isPresent())
            throw new EntityNotFoundException("entity introuvable");

        return new ResponseEntity<>(chapterBusiness.deleteChapter(idChapter), HttpStatus.OK);

    }

}
