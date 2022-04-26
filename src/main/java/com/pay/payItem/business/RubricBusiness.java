package com.pay.payItem.business;

import java.util.List;
import java.util.Optional;

import com.pay.payItem.model.Rubric;
import com.pay.payItem.repository.RubricRepository;
import com.pay.payItem.repository.VariableRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RubricBusiness {
    @Autowired 
    private RubricRepository rubricRepository;
    @Autowired 
    private VariableRepository variableRepository;

    public List<Rubric> getRubricsOfOrganism(int idOrganism) {
        return rubricRepository.findByOrganism(idOrganism);
    }

    public List<Rubric> getRubricsOfOrganismCreatedByUser(int idOrganism) {
        return rubricRepository.findByOrganismAndSystemcreated(idOrganism, false);
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
        return rubricRepository.save(rubric);
    }

    public Rubric updateRubric(int id, Rubric rubric) {
        Rubric rubric1 = rubricRepository.findById(id).get();
        
        if (rubric.getCode() > 0)
            rubric1.setCode(rubric.getCode());
        if (rubric.getDesign() != null)
            rubric1.setDesign(rubric.getDesign());
        if (rubric.isDeduction() != rubric1.isDeduction())
            rubric1.setDeduction(rubric.isDeduction());

            if (rubric.isRetainedOfAbsence() != rubric1.isRetainedOfAbsence())
            rubric1.setRetainedOfAbsence(rubric.isRetainedOfAbsence());

            if (rubric.isSubjectIRG() != rubric1.isSubjectIRG())
            rubric1.setSubjectIRG(rubric.isSubjectIRG());

            if (rubric.isSubjectSS() != rubric1.isSubjectSS())
            rubric1.setSubjectSS(rubric.isSubjectSS());

            if(rubric.getValueType()!=null)
            rubric1.setValueType(rubric.getValueType());

            if(rubric.getMatrixColumnNumber()>0)
            rubric1.setMatrixColumnNumber(rubric.getMatrixColumnNumber());
            

        return rubricRepository.save(rubric1);

    }

    public boolean deleteRubric(int idRubric) {
        if (!variableRepository.existByRubric(idRubric)) {
            rubricRepository.deleteById(idRubric);
            return true;
        } else
            return false;

    }
}
    
}
