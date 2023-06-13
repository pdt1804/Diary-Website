package com.example.demo.service;

import java.util.List;

import com.example.demo.entities.Diary;
import com.example.demo.entities.Writer;

public interface DiaryService {

	public List<Diary> GetAllDiaries();
	public Diary GetDiaryById(Long id);
	public void CreateDiary(Diary diary);
	public void UpdateDiary(Long id, Diary diary);
	public void DeleteDiary(Long id);
	
}
