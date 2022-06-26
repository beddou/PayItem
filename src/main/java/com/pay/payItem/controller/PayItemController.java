package com.pay.payItem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pay.payItem.business.PayItemBusiness;
import com.pay.payItem.exception.NoEntityAddedException;

@CrossOrigin(origins = "*")
@RestController
public class PayItemController {
    @Autowired
    private PayItemBusiness payItemBusiness;

    @PostMapping(value = "/PayItem/PayItem/InitializeOrganism/{idOrganism}")
    public ResponseEntity<Boolean> initializeOrganism(@PathVariable("idOrganism") int idOrganism) {
        boolean success = false;
        try {
            success = payItemBusiness.initializeOrganism(idOrganism);
            return new ResponseEntity<>(success, HttpStatus.CREATED);

        } catch (Exception e) {
            throw new NoEntityAddedException("entity not saved");
        }
        
    }

}
