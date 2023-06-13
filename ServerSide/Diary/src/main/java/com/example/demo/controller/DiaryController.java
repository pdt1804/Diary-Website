package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Diary;
import com.example.demo.entities.Writer;
import com.example.demo.service.DiaryService;

@RestController
@RequestMapping("/Diary")
public class DiaryController {

	@Autowired
	private DiaryService diaryService;
	
	@GetMapping("/Diaries")
	public List<Diary> GetAllDiaries()
	{
		return diaryService.GetAllDiaries();
	}
	
	@GetMapping("/Diaries/{id}")
	public Diary GetDiaryById(@PathVariable("id") Long id)
	{
		return diaryService.GetDiaryById(id);
	}
	
	@PostMapping("/Add")
	public void CreateDiary(@RequestBody Diary diary)
	{
		diaryService.CreateDiary(diary);
	}
	
	@PutMapping("/Update/{id}")
	public void UpdateDiary(@RequestBody Diary diary, @PathVariable("id") Long id)
	{
		diaryService.UpdateDiary(id, diary);
	}
	
	@DeleteMapping("/Delete/{id}")
	public void UpdateWriters(@PathVariable("id") Long id)
	{
		diaryService.DeleteDiary(id);
	}
}
