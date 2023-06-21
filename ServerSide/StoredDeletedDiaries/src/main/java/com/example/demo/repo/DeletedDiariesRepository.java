package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.DeletedDiaries;

public interface DeletedDiariesRepository extends JpaRepository<DeletedDiaries, Integer>{

}
