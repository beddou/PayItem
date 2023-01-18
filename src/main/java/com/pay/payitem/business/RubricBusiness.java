package com.pay.payitem.business;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.payitem.model.Article;
import com.pay.payitem.model.Rubric;
import com.pay.payitem.repository.ArticleRepository;
import com.pay.payitem.repository.ChapterRepository;
import com.pay.payitem.repository.RubricRepository;
import com.pay.payitem.repository.VariableRepository;

@Service
public class RubricBusiness {
    @Autowired
    private RubricRepository rubricRepository;
    @Autowired
    private VariableRepository variableRepository;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ChapterRepository chapterRepository;

    public Optional<Rubric> getRubric(int idRubric) {
        return rubricRepository.findById(idRubric);
    }

    public Rubric findRubric(int idRubric) {
        return rubricRepository.getById(idRubric);
    }

    public List<Rubric> getRubricsOfOrganism(int idOrganism) {
        return rubricRepository.findByOrganism(idOrganism);
    }

    public List<Rubric> getRubricsOfOrganismCreatedByUser(int idOrganism) {
        return rubricRepository.findByOrganismAndSystemCreated(idOrganism, false);
    }

    public List<Rubric> getRubricsOfOrganismManagedByUser(int idOrganism) {
        return rubricRepository.findByOrganismAndSystemManaged(idOrganism, false);
    }

    public Optional<Rubric> getRubricOfOrganismByCode(int idOrganism, int code) {
        List<Rubric> list = rubricRepository.findByOrganismAndCode(idOrganism, code);
        if (!list.isEmpty()) {
            return Optional.of(list.get(0));
        } else
            return Optional.empty();
    }

    public Rubric createRubric(Rubric rubric) {
        rubric.setSystemCreated(false);
        rubric.setSystemManaged(false);

         if (rubric.isDeduction() == null)
            rubric.setDeduction(false);

        if (rubric.isRetainedOfAbsence() == null)
            rubric.setRetainedOfAbsence(false);

        if (rubric.isSubjectIRG() == null)
            rubric.setSubjectIRG(false);

        if (rubric.isSubjectSS() == null)
            rubric.setSubjectSS(false);

        Rubric rubric1 = rubricRepository.save(rubric);
        if (rubric1.getArticle()!=null){
            int idArticle = rubric1.getArticle().getId();
            articleRepository.findById(idArticle).ifPresent(rubric1::setArticle);
        }
        
        if (rubric1.getChapter()!=null){
            int idChapter = rubric1.getChapter().getId();
            chapterRepository.findById(idChapter).ifPresent(rubric1::setChapter);
        }
        

        return rubric1;
    }

    public Rubric updateRubric(int id, Rubric rubric) {

        Rubric rubric1 = new Rubric();

        Optional<Rubric> rubric2 = rubricRepository.findById(id);
        if (rubric2.isPresent()) {
            rubric1 = rubric2.get();
            if (rubric.getCode() > 0)
                rubric1.setCode(rubric.getCode());

            if (rubric.getDesign() != null && !rubric.getDesign().equals("") && !rubric.getDesign().trim().equals(""))
                rubric1.setDesign(rubric.getDesign());

            if (rubric.isDeduction() != null)
                rubric1.setDeduction(rubric.isDeduction());

            if (rubric.isRetainedOfAbsence() != null)
                rubric1.setRetainedOfAbsence(rubric.isRetainedOfAbsence());

            if (rubric.isSubjectIRG() != null)
                rubric1.setSubjectIRG(rubric.isSubjectIRG());

            if (rubric.isSubjectSS() != null)
                rubric1.setSubjectSS(rubric.isSubjectSS());

            if (rubric.getValueType() != null)
                rubric1.setValueType(rubric.getValueType());

            if (rubric.getMatrixColumnNumber() > 0)
                rubric1.setMatrixColumnNumber(rubric.getMatrixColumnNumber());

            if (rubric.getArticle() != null) {
                int idArticle = rubric.getArticle().getId();
                if (idArticle > 0) {

                    articleRepository.findById(idArticle).ifPresent(rubric1::setArticle);

                }
            }
            if (rubric.getChapter() != null) {
                int idChapter = rubric.getChapter().getId();
                if (idChapter > 0) {
                    chapterRepository.findById(idChapter).ifPresent(rubric1::setChapter);
                }
            }

        }

        return rubricRepository.save(rubric1);

    }

    public boolean deleteRubric(int idRubric) {

        Optional<Rubric> rubric = rubricRepository.findById(idRubric);

        if (!rubric.isPresent())
            return false;
        if (rubric.get().isSystemCreated())
            return false;
        if (!variableRepository.existsByRubric_id(idRubric)) {
            try {
                rubricRepository.deleteById(idRubric);
                return true;
            } catch (Exception e) {
                return false;
            }

        } else {
            return false;
        }
    }

}
