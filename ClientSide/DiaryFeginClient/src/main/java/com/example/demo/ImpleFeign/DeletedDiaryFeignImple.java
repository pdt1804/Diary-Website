package com.example.demo.ImpleFeign;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.DeletedDiary;
import com.example.demo.feigns.DeletedDiaryFeign;

@Service
public class DeletedDiaryFeignImple implements DeletedDiaryFeign{

	@Autowired
	private DeletedDiaryFeign deletedDiaryFeign;

	@Override
	public List<DeletedDiary> getAllDeleted() {
		return deletedDiaryFeign.getAllDeleted();
	}

	@Override
	public List<DeletedDiary> getAllDeleted(String username) {
		return deletedDiaryFeign.getAllDeleted(username);
	}

	@Override
	public void AddDeletedDiary(DeletedDiary deletedDiary) {
		deletedDiaryFeign.AddDeletedDiary(deletedDiary);
	}

	@Override
	public void RecoverDeletedDiary(DeletedDiary deletedDiary) {
		deletedDiaryFeign.RecoverDeletedDiary(deletedDiary);
	}

	@Override
	public void DeletePermanently(DeletedDiary deleteDiary) {
		deletedDiaryFeign.DeletePermanently(deleteDiary);
	}
	
	
}
