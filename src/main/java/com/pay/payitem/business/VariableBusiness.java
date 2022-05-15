package com.pay.payitem.business;

import java.util.List;
import java.util.Optional;

import com.pay.payitem.model.Rubric;
import com.pay.payitem.model.Variable;
import com.pay.payitem.repository.RubricRepository;
import com.pay.payitem.repository.VariableRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VariableBusiness {
    @Autowired
    private VariableRepository variableRepository;
    @Autowired
    private RubricRepository rubricRepository;

    public Optional<Variable> getVariable(int idVariable) {
        return variableRepository.findById(idVariable);
    }

    public List<Variable> getVariablesOfSalaried(int idSalaried) {
        return variableRepository.findBySalaried(idSalaried);
    }

    public Variable createVariable(Variable variable) {
        Variable variable1 = variableRepository.save(variable);
        int id = variable1.getRubric().getId();
        Rubric rubric = rubricRepository.findById(id).get();
        variable1.setRubric(rubric);
        return variable1;
    }

    public boolean deleteVariable(int idVariable) {

        try {
            variableRepository.deleteById(idVariable);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}
