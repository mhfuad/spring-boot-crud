package com.springMVC.repository;

import com.springMVC.model.Programmer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgrammerRepo extends JpaRepository<Programmer, Integer> {
}
