package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Writer;

public interface WriterRepo extends JpaRepository<Writer, String>{

}
