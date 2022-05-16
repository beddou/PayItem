package com.pay.payitem.controller;

import java.util.List;
import java.util.Optional;

import com.pay.payitem.business.VariableBusiness;
import com.pay.payitem.exception.EntityNotFoundException;
import com.pay.payitem.exception.NoEntityAddedException;
import com.pay.payitem.model.Variable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class VariableController {

    @Autowired
    private VariableBusiness variableBusiness;
    private String variableNotFound = "Variable not found";
    private String variableNotSaved = "Variable not saved";

    @GetMapping(value = "/PayItem/Variable/getVariablesOfSalaried/{idSalaried}")
    public List<Variable> getVariablesOfSalaried(@PathVariable("idSalaried") int idSalaried) {
        List<Variable> variables = variableBusiness.getVariablesOfSalaried(idSalaried);
        if (variables.isEmpty()) {
            throw new EntityNotFoundException(variableNotFound);
        } else
            return variables;

    }

    @PostMapping(value = "/PayItem/Variable/create")
    public ResponseEntity<Variable> createVariable(@RequestBody Variable variable) {
        try {
            Variable variable1 = variableBusiness.createVariable(variable);
            return new ResponseEntity<>(variable1, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new NoEntityAddedException(variableNotSaved);
        }

    }

    @DeleteMapping(value = "/PayItem/Variable/delete/{idVariable}")

    public ResponseEntity<Boolean> deleteVariable(@PathVariable int idVariable) {

        Optional<Variable> variable = variableBusiness.getVariable(idVariable);
        if (!variable.isPresent())
            throw new EntityNotFoundException(variableNotFound);

        return new ResponseEntity<>(variableBusiness.deleteVariable(idVariable), HttpStatus.OK);

    }
}