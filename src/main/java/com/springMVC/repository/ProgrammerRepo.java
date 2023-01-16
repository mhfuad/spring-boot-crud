package com.springMVC.repository;

import com.springMVC.model.Programmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProgrammerRepo extends JpaRepository<Programmer, Integer> {
    @Query("from Programmer where pName = ?1")
    List<Programmer> findP(String pLang);
}
