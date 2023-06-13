package com.example.demo.Imple;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Diary;
import com.example.demo.entities.Writer;
import com.example.demo.repo.DiaryRepo;
import com.example.demo.service.DiaryService;

@Service
public class DiaryServiceImple implements DiaryService{

	@Autowired
	private DiaryRepo diaryRepo;
	
	@Override
	public List<Diary> GetAllDiaries() {
		
		return diaryRepo.findAll();
		
	}

	@Override
	public Diary GetDiaryById(Long id) {

		return diaryRepo.getById(id);
		
	}


	@Override
	public void UpdateDiary(Long id, Diary diary) {

		Diary existingDiary = diaryRepo.getById(id);
		
		existingDiary.setHeader(diary.getHeader());
		existingDiary.setMarkup(diary.getMarkup());
		existingDiary.setContent(diary.getContent());
		Date dt = new Date();
		existingDiary.setDateWrittenOrUpdated(dt);
		diaryRepo.save(existingDiary);
		
	}

	@Override
	public void DeleteDiary(Long id) {

		Diary existingDiary = diaryRepo.getById(id);
		diaryRepo.delete(existingDiary);
		
	}

	@Override
	public void CreateDiary(Diary diary) {
		
		Date dt = new Date();
		diary.setDateWrittenOrUpdated(dt);
		diaryRepo.save(diary);
		
	}

}
