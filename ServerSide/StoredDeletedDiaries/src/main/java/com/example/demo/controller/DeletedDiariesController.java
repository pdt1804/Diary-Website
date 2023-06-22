package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.DeletedDiaries;
import com.example.demo.service.DeletedDiariesService;

@RestController
@RequestMapping("api/deletedDiaries")
public class DeletedDiariesController {

	@Autowired 
	private DeletedDiariesService deletedDiariesService;
	
	@GetMapping("GetAllDeletedDiaries")
	public List<DeletedDiaries> getAllDeletedDiaries()
	{
		return deletedDiariesService.getAllDeletedDiaries();
	}
	
	@GetMapping("GetDeletedDiariesByUsername/{username}")
	public List<DeletedDiaries> getAllDeletedDiariesByUsername(@PathVariable("username") String username)
	{
		return deletedDiariesService.getDeletedDiariesByUsername(username);
	}
	
	@PostMapping("AddDeletedDiary")
	public void AddDeletedDiary(@RequestBody DeletedDiaries deletedDiary)
	{
		deletedDiariesService.addDeletedDiaries(deletedDiary);
	}
	
	@DeleteMapping("RecoverDeletedDiary")
	public void RecoverDeletedDiary(@RequestBody DeletedDiaries deletedDiary)
	{
		deletedDiariesService.recoverDiary(deletedDiary);
	}
	
	@DeleteMapping("DeletePermanently")
	public void DeletePermanently(@RequestBody DeletedDiaries deletedDiary)
	{
		deletedDiariesService.DeletedPermanently(deletedDiary);
	}
}
