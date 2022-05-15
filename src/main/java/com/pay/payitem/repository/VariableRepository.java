package com.pay.payitem.repository;

import java.util.List;

import com.pay.payitem.model.Variable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariableRepository extends JpaRepository<Variable, Integer>{

    boolean existsByRubric_id(int idRubric);
    List<Variable> findBySalaried(int idSalaried);

    
}
