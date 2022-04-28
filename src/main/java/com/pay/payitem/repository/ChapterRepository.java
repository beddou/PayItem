package com.pay.payitem.repository;

import java.util.List;

import com.pay.payitem.model.Chapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Integer> {

    List<Chapter> findByOrganism(int idOrganism);
    List<Chapter> findByOrganismAndSystemCreated(int idOrganism, boolean systemCreated);
    List<Chapter> findByOrganismAndCode(int idOrganism, int code);
    
}
