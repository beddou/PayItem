package com.pay.payitem.controller;

import java.util.List;
import java.util.Optional;

import com.pay.payitem.business.RubricBusiness;
import com.pay.payitem.exception.EntityNotFoundException;
import com.pay.payitem.exception.NoEntityAddedException;
import com.pay.payitem.model.Rubric;

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
public class RubricController {
    @Autowired
    private RubricBusiness rubricBusiness;
    private String rubricNotFound = "rubric not found"; 
    private String rubricNotSaved = "rubric not saved";

    @GetMapping(value = "/PayItem/Rubric/Get/{id}")
    public ResponseEntity<Rubric> getRubric(@PathVariable("id") int id) {
        Optional<Rubric> rubric = rubricBusiness.getRubric(id);
        if (rubric.isPresent()) {
            return new ResponseEntity<>(rubric.get(), HttpStatus.OK);
        }

        else {
            throw new EntityNotFoundException(rubricNotFound);

        }

    }


    @GetMapping(value = "/PayItem/Rubric/All/{idOrganism}")
    public List<Rubric> getRubricsOfOrganism(@PathVariable("idOrganism") int idOrganism) {
        List<Rubric> rubrics = rubricBusiness.getRubricsOfOrganism(idOrganism);
        if (rubrics.isEmpty()) {
            throw new EntityNotFoundException(rubricNotFound);
        } else
            return rubrics;

    }

    @GetMapping(value = "/PayItem/Rubric/GetCreatedByUser/{idOrganism}")
    public List<Rubric> getRubricsCreatedByUserOfOrganism(@PathVariable("idOrganism") int idOrganism) {
        List<Rubric> rubrics = rubricBusiness.getRubricsOfOrganismCreatedByUser(idOrganism);
        if (rubrics.isEmpty()) {
            throw new EntityNotFoundException(rubricNotFound);
        } else
            return rubrics;

    }

    @GetMapping(value = "/PayItem/Rubric/GetManagedByUser/{idOrganism}")
    public List<Rubric> getRubricsManagedByUserOfOrganism(@PathVariable("idOrganism") int idOrganism) {
        List<Rubric> rubrics = rubricBusiness.getRubricsOfOrganismManagedByUser(idOrganism);
        if (rubrics.isEmpty()) {
            throw new EntityNotFoundException(rubricNotFound);
        } else
            return rubrics;

    }

    @GetMapping(value = "/PayItem/Rubric/GetByCode/{code}/{idOrganism}")
    public ResponseEntity<Rubric> getRubricsByCodeOfOrganism(@PathVariable("code") int code,
            @PathVariable("idOrganism") int idOrganism) {
        Optional<Rubric> rubric = rubricBusiness.getRubricOfOrganismByCode(idOrganism, code);
        if (rubric.isPresent()) {
            return new ResponseEntity<>(rubric.get(), HttpStatus.OK);
        }

        else {
            throw new EntityNotFoundException(rubricNotFound);

        }

    }

    @PostMapping(value = "/PayItem/Rubric/Create")
    public ResponseEntity<Rubric> createRubric(@RequestBody Rubric rubric) {
        try {
            Rubric rubric1 = rubricBusiness.createRubric(rubric);
            return new ResponseEntity<>(rubric1 , HttpStatus.CREATED);
        } catch (Exception e) {
            throw new NoEntityAddedException(rubricNotSaved);
        }

    }

    @PutMapping(value = "/PayItem/Rubric/Update/{idRubric}")
    public ResponseEntity<Rubric> upDateRubric(@PathVariable("idRubric") int idRubric,
            @RequestBody Rubric rubric) {

        Optional<Rubric> rubric1 = rubricBusiness.getRubric(idRubric);

        if (rubric1.isPresent()) {
            try {

                Rubric rubric2 = rubricBusiness.updateRubric(idRubric, rubric);
                return new ResponseEntity<>(rubric2, HttpStatus.CREATED);

            } catch (Exception e) {
                throw new NoEntityAddedException(rubricNotSaved);
            }

        } else {
            throw new EntityNotFoundException(rubricNotFound);

        }

    }

    @DeleteMapping(value = "/PayItem/Rubric/Delete/{idRubric}")

    public ResponseEntity<Boolean> deleteRubric(@PathVariable int idRubric) {

        Optional<Rubric> rubric =  rubricBusiness.getRubric(idRubric);
        if (!rubric.isPresent())
            throw new EntityNotFoundException(rubricNotFound);

        return new ResponseEntity<>( rubricBusiness.deleteRubric(idRubric), HttpStatus.OK);

    }

}
