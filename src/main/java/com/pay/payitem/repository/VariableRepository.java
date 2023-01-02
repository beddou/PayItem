package com.pay.payitem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pay.payitem.model.Variable;

@Repository
public interface VariableRepository extends JpaRepository<Variable, Integer>{

    boolean existsByRubric_id(int idRubric);
    List<Variable> findBySalaried(int idSalaried);

    
}
