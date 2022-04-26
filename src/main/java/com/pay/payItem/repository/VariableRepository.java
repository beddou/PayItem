package com.pay.payItem.repository;

import com.pay.payItem.model.Variable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariableRepository extends JpaRepository<Variable, Integer>{

    boolean existByRubric(int idRubric);
    
}
