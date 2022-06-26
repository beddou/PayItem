package com.pay.payItem.dto;

import com.pay.payItem.model.tools.ValueType;

public class VariableDto {

    private int id;

    private float value;

    private int rubricId;

    private int rubricCode;

    private String rubricDesign;

    private ValueType rubricValueType;

    private Boolean rubricDeduction;

    private boolean rubricSystemCreated;

    private boolean rubricSystemManaged;

    private Boolean rubricSubjectIRG;

    private Boolean rubricSubjectSS;

    private Boolean rubricRetainedOfAbsence;

    private int rubricMatrixColumnNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    

    public int getRubricId() {
        return rubricId;
    }

    public void setRubricId(int rubricId) {
        this.rubricId = rubricId;
    }

    public int getRubricCode() {
        return rubricCode;
    }

    public void setRubricCode(int rubricCode) {
        this.rubricCode = rubricCode;
    }

    public String getRubricDesign() {
        return rubricDesign;
    }

    public void setRubricDesign(String rubricDesign) {
        this.rubricDesign = rubricDesign;
    }

    public ValueType getRubricValueType() {
        return rubricValueType;
    }

    public void setRubricValueType(ValueType rubricValueType) {
        this.rubricValueType = rubricValueType;
    }

    public Boolean getRubricDeduction() {
        return rubricDeduction;
    }

    public void setRubricDeduction(Boolean rubricDeduction) {
        this.rubricDeduction = rubricDeduction;
    }

    public boolean isRubricSystemCreated() {
        return rubricSystemCreated;
    }

    public void setRubricSystemCreated(boolean rubricSystemCreated) {
        this.rubricSystemCreated = rubricSystemCreated;
    }

    public boolean isRubricSystemManaged() {
        return rubricSystemManaged;
    }

    public void setRubricSystemManaged(boolean rubricSystemManaged) {
        this.rubricSystemManaged = rubricSystemManaged;
    }

    public Boolean getRubricSubjectIRG() {
        return rubricSubjectIRG;
    }

    public void setRubricSubjectIRG(Boolean rubricSubjectIRG) {
        this.rubricSubjectIRG = rubricSubjectIRG;
    }

    public Boolean getRubricSubjectSS() {
        return rubricSubjectSS;
    }

    public void setRubricSubjectSS(Boolean rubricSubjectSS) {
        this.rubricSubjectSS = rubricSubjectSS;
    }

    public Boolean getRubricRetainedOfAbsence() {
        return rubricRetainedOfAbsence;
    }

    public void setRubricRetainedOfAbsence(Boolean rubricRetainedOfAbsence) {
        this.rubricRetainedOfAbsence = rubricRetainedOfAbsence;
    }

    public int getRubricMatrixColumnNumber() {
        return rubricMatrixColumnNumber;
    }

    public void setRubricMatrixColumnNumber(int rubricMatrixColumnNumber) {
        this.rubricMatrixColumnNumber = rubricMatrixColumnNumber;
    }

    
}
