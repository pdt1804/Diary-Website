package com.example.demo.feigns;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entities.DeletedDiary;

@FeignClient(value="DeletedDiary-Feigns", url="http://localhost:8079/api/deletedDiaries")
public interface DeletedDiaryFeign {
	
	@GetMapping("GetAllDeletedDiaries")
	public List<DeletedDiary> getAllDeleted();
	
	@GetMapping("GetDeletedDiariesByUsername/{username}")
	public List<DeletedDiary> getAllDeleted(@PathVariable String username);
	
	@PostMapping("AddDeletedDiary")
	public void AddDeletedDiary(DeletedDiary deletedDiary);
	
	@DeleteMapping("RecoverDeletedDiary")
	public void RecoverDeletedDiary(DeletedDiary deletedDiary);
	
	@DeleteMapping("DeletePermanently")
	public void DeletePermanently(DeletedDiary deleteDiary);
}
