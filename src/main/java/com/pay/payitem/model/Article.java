package com.pay.payitem.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(name = "UniqueArticle", columnNames = { "organism", "code" }) })
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private int code;

    @NotNull(message = "Name cannot be null") @NotEmpty @NotBlank
    private String design;

    @Column(columnDefinition = "boolean default true")
    private boolean systemCreated;

    @ManyToOne
	@NotNull
    
    private Chapter chapter;

    @Min(value = 1, message = "organism must be input")    
    private int organism;
    

   // @OneToMany(targetEntity=Rubric.class, mappedBy="article", fetch = FetchType.LAZY)   
   //@JsonIgnore 
//private List<Rubric> rubrics;

    @Override
    public String toString() {
        return "Article [chapter=" + chapter + ", code=" + code + ", design=" + design + ", id=" + id + ", organism="
                + organism +  ", systemCreated=" + systemCreated + "]";
    }

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

    public boolean isSystemCreated() {
        return systemCreated;
    }

    public void setSystemCreated(boolean systemCreated) {
        this.systemCreated = systemCreated;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public int getOrganism() {
        return organism;
    }

    public void setOrganism(int organism) {
        this.organism = organism;
    }

    


    
}
