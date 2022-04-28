package com.pay.payitem.business;

import java.util.List;

import com.pay.payitem.model.Variable;
import com.pay.payitem.repository.VariableRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VariableBusiness {
    @Autowired
    private VariableRepository variableRepository;

    public List<Variable> getVariablesOfSalaried(int idSalaried) {
        return variableRepository.findBySalaried(idSalaried);
    }

    public Variable createVariable(Variable variable) {

        return variableRepository.save(variable);
    }

    public Variable updateVariable(int id, Variable variable) {
        Variable variable1 = variableRepository.findById(id).get();

        if (variable.getValue() > 0)
            variable1.setValue(variable.getValue());

        return variableRepository.save(variable1);

    }

    public void deleteVariable(int idVariable) {

        variableRepository.deleteById(idVariable);

    }

}
