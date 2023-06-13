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

import com.example.demo.entities.Writer;
import com.example.demo.service.WriterService;

@RestController
@RequestMapping("/Writer")
public class WriterController {

	@Autowired
	private WriterService writerService;
	
	@GetMapping("/Writers")
	public List<Writer> GetAllWriters()
	{
		return writerService.GetAllWriters();
	}
	
	@GetMapping("/Writers/{username}")
	public Writer GetWritersByUsername(@PathVariable("username") String username)
	{
		return writerService.GetWriterByUsername(username);
	}
	
	@PostMapping("/Add")
	public void UpdateWriters(@RequestBody Writer writer)
	{
		writerService.CreateNewWriter(writer);
	}
	
	@PutMapping("/Update/{username}")
	public void UpdateWriters(@RequestBody Writer writer, @PathVariable("username") String username)
	{
		writerService.UpdateWriter(username, writer);
	}
	
	@DeleteMapping("/Delete/{username}")
	public void UpdateWriters(@PathVariable("username") String username)
	{
		writerService.DeleteWriter(username);
	}
	
}
