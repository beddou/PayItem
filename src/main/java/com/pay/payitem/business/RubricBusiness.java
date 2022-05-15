package com.pay.payitem.business;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import com.pay.payitem.model.Article;
import com.pay.payitem.model.Rubric;
import com.pay.payitem.repository.ArticleRepository;
import com.pay.payitem.repository.RubricRepository;
import com.pay.payitem.repository.VariableRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RubricBusiness {
    @Autowired
    private RubricRepository rubricRepository;
    @Autowired
    private VariableRepository variableRepository;
    @Autowired
    private ArticleRepository articleRepository;

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
        Rubric rubric1 = rubricRepository.save(rubric);
        int id = rubric1.getArticle().getId();
        Article article = articleRepository.findById(id).get();
        rubric1.setArticle(article);
        return rubric1;
    }

    public Rubric updateRubric(int id, Rubric rubric) {
        Rubric rubric1 = rubricRepository.findById(id).get();

        if (rubric.getCode() > 0)
            rubric1.setCode(rubric.getCode());

        if (rubric.getDesign() != null && !rubric.getDesign().equals("") && !rubric.getDesign().trim().equals(""))
            rubric1.setDesign(rubric.getDesign());
        if (rubric.isDeduction() != rubric1.isDeduction())
            rubric1.setDeduction(rubric.isDeduction());

        if (rubric.isRetainedOfAbsence() != rubric1.isRetainedOfAbsence())
            rubric1.setRetainedOfAbsence(rubric.isRetainedOfAbsence());

        if (rubric.isSubjectIRG() != rubric1.isSubjectIRG())
            rubric1.setSubjectIRG(rubric.isSubjectIRG());

        if (rubric.isSubjectSS() != rubric1.isSubjectSS())
            rubric1.setSubjectSS(rubric.isSubjectSS());

        if (rubric.getValueType() != null)
            rubric1.setValueType(rubric.getValueType());

        if (rubric.getMatrixColumnNumber() > 0)
            rubric1.setMatrixColumnNumber(rubric.getMatrixColumnNumber());

        if (rubric.getArticle() != null) {
            int idArticle = rubric.getArticle().getId();
            if (idArticle > 0) {
                Article article = articleRepository.findById(idArticle).get();
                rubric1.setArticle(article);
            }
        }

        return rubricRepository.save(rubric1);

    }

    public boolean deleteRubric(int idRubric) {
        if (!variableRepository.existsByRubric_id(idRubric)) {
            rubricRepository.deleteById(idRubric);
            return true;
        } else
            return false;

    }

}
