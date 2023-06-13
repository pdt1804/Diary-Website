package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Writer;
import com.example.demo.repo.WriterRepo;

public interface WriterService {

	public List<Writer> GetAllWriters();
	public Writer GetWriterByUsername(String username);
	public void CreateNewWriter(Writer writer);
	public void UpdateWriter(String id, Writer writer);
	public void DeleteWriter(String id);
	
}
