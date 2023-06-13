package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Diary;

public interface DiaryRepo extends JpaRepository<Diary, Long> {

}
