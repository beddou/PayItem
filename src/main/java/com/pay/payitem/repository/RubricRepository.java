package com.pay.payitem.repository;

import java.util.List;

import com.pay.payitem.model.Rubric;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RubricRepository extends JpaRepository<Rubric, Integer> {

    boolean existsByArticle_id(int idArticle);
    List<Rubric> findByOrganism(int idOrganism);
    List<Rubric> findByOrganismAndSystemCreated(int idOrganism, boolean systemCreated);
    List<Rubric> findByOrganismAndSystemManaged(int idOrganism, boolean systemManaged);
    List<Rubric> findByOrganismAndCode(int idOrganism, int code);
    
}
