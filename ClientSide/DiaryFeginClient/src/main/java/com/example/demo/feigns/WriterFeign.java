package com.example.demo.feigns;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.demo.entities.Writer;

@FeignClient(value = "Writer-Feigns", url = "http://localhost:8080/Writer")
public interface WriterFeign {
	
	@GetMapping("/Writers")
	public List<Writer> GetAllWriter();
	
	@PostMapping("/Add")
	public void CreateNewWriters(Writer writer);
	
	@PutMapping("/Update/{id}")
	public void UpdateWriter(@PathVariable("id") String id, Writer writer);
	
	@DeleteMapping("/Delete/{id}")
	public void DeleteWriter(@PathVariable("id") String id);
	
}
