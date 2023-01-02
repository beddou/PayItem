package com.pay.payitem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pay.payitem.model.Chapter;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Integer> {

    List<Chapter> findByOrganism(int idOrganism);
    List<Chapter> findByOrganismAndSystemCreated(int idOrganism, boolean systemCreated);
    
    
}
