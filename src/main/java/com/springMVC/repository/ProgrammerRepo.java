package com.springMVC.repository;

import com.springMVC.model.Programmer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgrammerRepo extends JpaRepository<Programmer, Integer> {
    List<Programmer> findBypLang(String pLang);
}
