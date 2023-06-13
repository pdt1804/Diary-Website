package com.example.demo.feigns;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.demo.entities.Diary;
import com.example.demo.entities.Writer;

@FeignClient(value="Diary-Feigns", url="http://localhost:8080/Diary")
public interface DiaryFeign {

	@GetMapping("/Diaries")
	public List<Diary> GetAllDiaries();
	
	@PostMapping("/Add")
	public void CreateDiary(Diary diary);
	
	@PutMapping("/Update/{id}")
	public void UpdateDiary(Diary diary, @PathVariable("id") Long id);
	
	@DeleteMapping("/Delete/{id}")
	public void DeleteDiary(@PathVariable("id") Long id);
	
}
