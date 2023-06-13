package com.example.demo.ImpleFeign;

import java.util.List;

import com.example.demo.entities.Diary;
import com.example.demo.feigns.DiaryFeign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DiaryFeignImple implements DiaryFeign {

	@Autowired
	private DiaryFeign diaryFeign;

	@Override
	public List<Diary> GetAllDiaries() {
		
		return diaryFeign.GetAllDiaries();
		
	}


	@Override
	public void UpdateDiary(Diary diary, Long id) {

		diaryFeign.UpdateDiary(diary, id);
		
	}

	@Override
	public void DeleteDiary(Long id) {

		diaryFeign.DeleteDiary(id);
		
	}

	@Override
	public void CreateDiary(Diary diary) {
		
		diaryFeign.CreateDiary(diary);
		
	}
	
}
