package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.DeletedDiaries;
import com.example.demo.repo.DeletedDiariesRepository;

@Service
public class DeletedDiariesService {
	
	@Autowired
	private DeletedDiariesRepository deletedDiariesRepo;
	
	public List<DeletedDiaries> getAllDeletedDiaries()
	{
		return deletedDiariesRepo.findAll();
	}
	
	
	public List<DeletedDiaries> getDeletedDiariesByUsername(String username)
	{
		try
		{
			return getAllDeletedDiaries()
				  .stream()
				  .filter(p -> p.getUsername().equals(username))
				  .collect(Collectors.toList());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	
	public void addDeletedDiaries(DeletedDiaries deletedDiaries)
	{
		deletedDiariesRepo.save(deletedDiaries);
	}

	public void recoverDiary(DeletedDiaries deletedDiaries)
	{
		deletedDiariesRepo.delete(deletedDiaries);
	}
	
	public void DeletedPermanently(DeletedDiaries deletedDiary)
	{
		deletedDiariesRepo.delete(deletedDiary);
	}

}
