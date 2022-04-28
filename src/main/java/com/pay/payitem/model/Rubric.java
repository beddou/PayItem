package com.pay.payitem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.pay.payitem.model.tools.ValueType;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(name = "UniqueRubric", columnNames = { "organism", "code" }) })
public class Rubric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private int code;

    @NotNull(message = "Name cannot be null")
    @NotEmpty
    @NotBlank
    private String design;

    @Enumerated(EnumType.STRING)
    private ValueType valueType;

    @Column(columnDefinition = "boolean default false")
    private boolean deduction;

    @Column(columnDefinition = "boolean default true")
    private boolean systemCreated;

    @Column(columnDefinition = "boolean default true")
    private boolean systemManaged;

    @Column(columnDefinition = "boolean default true")
    private boolean subjectIRG;

    @Column(columnDefinition = "boolean default true")
    private boolean subjectSS;

    @Column(columnDefinition = "boolean default true")
    private boolean retainedOfAbsence;

    private int matrixColumnNumber;

    @ManyToOne
    private Article article;

    @NotNull
    private int organism;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public ValueType getValueType() {
        return valueType;
    }

    public void setValueType(ValueType valueType) {
        this.valueType = valueType;
    }

    public boolean isDeduction() {
        return deduction;
    }

    public void setDeduction(boolean deduction) {
        this.deduction = deduction;
    }

    public boolean isSystemCreated() {
        return systemCreated;
    }

    public void setSystemCreated(boolean systemCreated) {
        this.systemCreated = systemCreated;
    }

    public boolean isSystemManaged() {
        return systemManaged;
    }

    public void setSystemManaged(boolean systemManaged) {
        this.systemManaged = systemManaged;
    }

    public boolean isSubjectIRG() {
        return subjectIRG;
    }

    public void setSubjectIRG(boolean subjectIRG) {
        this.subjectIRG = subjectIRG;
    }

    public boolean isSubjectSS() {
        return subjectSS;
    }

    public void setSubjectSS(boolean subjectSS) {
        this.subjectSS = subjectSS;
    }

    public boolean isRetainedOfAbsence() {
        return retainedOfAbsence;
    }

    public void setRetainedOfAbsence(boolean retainedOfAbsence) {
        this.retainedOfAbsence = retainedOfAbsence;
    }

    public int getMatrixColumnNumber() {
        return matrixColumnNumber;
    }

    public void setMatrixColumnNumber(int matrixColumnNumber) {
        this.matrixColumnNumber = matrixColumnNumber;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getOrganism() {
        return organism;
    }

    public void setOrganism(int organism) {
        this.organism = organism;
    }

    

}
