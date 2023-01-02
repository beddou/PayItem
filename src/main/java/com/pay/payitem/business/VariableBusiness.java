package com.pay.payitem.business;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.payitem.dto.VariableDto;
import com.pay.payitem.model.Variable;
import com.pay.payitem.repository.RubricRepository;
import com.pay.payitem.repository.VariableRepository;

@Service
public class VariableBusiness {
    @Autowired
    private VariableRepository variableRepository;
    @Autowired
    private RubricRepository rubricRepository;

    public Optional<Variable> getVariable(int idVariable) {
        return variableRepository.findById(idVariable);
    }

    public List<VariableDto> getVariablesOfSalaried(int idSalaried) {
        ModelMapper modelMapper = new ModelMapper();

        return variableRepository.findBySalaried(idSalaried).stream()
                .map(temp -> modelMapper.map(temp, VariableDto.class)

                ).collect(Collectors.toList());
    }

    public Variable createVariable(Variable variable) {
        Variable variable1 = variableRepository.save(variable);
        int id = variable1.getRubric().getId();

        rubricRepository.findById(id).ifPresent(

                variable1::setRubric);

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
