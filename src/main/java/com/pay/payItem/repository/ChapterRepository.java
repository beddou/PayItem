package com.pay.payItem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pay.payItem.model.Chapter;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Integer> {

    List<Chapter> findByOrganism(int idOrganism);
    List<Chapter> findByOrganismAndSystemCreated(int idOrganism, boolean systemCreated);
    List<Chapter> findByOrganismAndCode(int idOrganism, int code);
    
}
