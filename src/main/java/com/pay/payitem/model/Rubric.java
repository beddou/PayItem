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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.pay.payitem.model.tools.ValueType;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(name = "UniqueRubric", columnNames = { "organism", "code" }),
        @UniqueConstraint(name = "UniqueMatrixColumnNumber", columnNames = { "organism", "matrixColumnNumber",
                "matrixFeuillet" }) })
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

    @NotNull
    private Boolean deduction;

    @Column(columnDefinition = "boolean default true")
    private boolean systemCreated;

    @Column(columnDefinition = "boolean default true")
    private boolean systemManaged;

    @NotNull
    private Boolean subjectIRG;

    @NotNull
    private Boolean subjectSS;

    @NotNull
    private Boolean retainedOfAbsence;

    private Integer matrixFeuillet;    

    private Integer matrixColumnNumber;

    @ManyToOne
    private Article article;

    @ManyToOne
    private Chapter chapter;

    @Min(value = 1, message = "organism must be input")
    private int organism;

    /*
     * public Rubric(@NotNull int code, @NotNull(message =
     * "Name cannot be null") @NotEmpty @NotBlank String design,
     * ValueType valueType, int matrixColumnNumber, Article article,
     * 
     * @Min(value = 1, message = "organism must be input") int organism) {
     * this.code = code;
     * this.design = design;
     * this.valueType = valueType;
     * this.matrixColumnNumber = matrixColumnNumber;
     * this.article = article;
     * this.organism = organism;
     * }
     */

    public Rubric(@NotNull int code, @NotNull(message = "Name cannot be null") @NotEmpty @NotBlank String design,
            ValueType valueType, @NotNull Boolean deduction, boolean systemCreated, boolean systemManaged,
            @NotNull Boolean subjectIRG, @NotNull Boolean subjectSS, @NotNull Boolean retainedOfAbsence,
            Integer matrixColumnNumber, Article article, Chapter chapter,
            @Min(value = 1, message = "organism must be input") int organism) {
        this.code = code;
        this.design = design;
        this.valueType = valueType;
        this.deduction = deduction;
        this.systemCreated = systemCreated;
        this.systemManaged = systemManaged;
        this.subjectIRG = subjectIRG;
        this.subjectSS = subjectSS;
        this.retainedOfAbsence = retainedOfAbsence;
        this.matrixColumnNumber = matrixColumnNumber;
        this.article = article;
        this.chapter = chapter;
        this.organism = organism;
    }

    public Rubric() {
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Rubric [article=" + article + ", code=" + code + ", deduction=" + deduction + ", design=" + design
                + ", id=" + id + ", matrixColumnNumber=" + matrixColumnNumber + ", organism=" + organism
                + ", retainedOfAbsence=" + retainedOfAbsence + ", subjectIRG=" + subjectIRG + ", subjectSS=" + subjectSS
                + ", systemCreated=" + systemCreated + ", systemManaged=" + systemManaged + ", valueType=" + valueType
                + "]";
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

    public Boolean isDeduction() {
        return deduction;
    }

    public void setDeduction(Boolean deduction) {
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

    public Boolean isSubjectIRG() {
        return subjectIRG;
    }

    public void setSubjectIRG(Boolean subjectIRG) {
        this.subjectIRG = subjectIRG;
    }

    public Boolean isSubjectSS() {
        return subjectSS;
    }

    public void setSubjectSS(Boolean subjectSS) {
        this.subjectSS = subjectSS;
    }

    public Boolean isRetainedOfAbsence() {
        return retainedOfAbsence;
    }

    public void setRetainedOfAbsence(Boolean retainedOfAbsence) {
        this.retainedOfAbsence = retainedOfAbsence;
    }

    public Integer getMatrixFeuillet() {
        return matrixFeuillet;
    }

    public void setMatrixFeuillet(Integer matrixFeuillet) {
        this.matrixFeuillet = matrixFeuillet;
    }

    public Integer getMatrixColumnNumber() {
        return matrixColumnNumber;
    }

    public void setMatrixColumnNumber(Integer matrixColumnNumber) {
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

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

}
