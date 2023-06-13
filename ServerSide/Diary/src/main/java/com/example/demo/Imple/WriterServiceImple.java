package com.example.demo.Imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Writer;
import com.example.demo.repo.WriterRepo;
import com.example.demo.service.WriterService;

@Service
public class WriterServiceImple implements WriterService {

	@Autowired
	private WriterRepo writerRepo;
	
	@Override
	public List<Writer> GetAllWriters() {
	
		return writerRepo.findAll();
		
	}

	@Override
	public void CreateNewWriter(Writer writer) {
		
		writerRepo.save(writer);
	
	}

	@Override
	public void UpdateWriter(String id, Writer writer) {
		
		Writer existingWriter = writerRepo.getById(id);
		
		existingWriter.setEmail(writer.getEmail());
		existingWriter.setFulName(writer.getFulName());
		existingWriter.setPassword(writer.getPassword());
		existingWriter.setPhoneNumber(writer.getPhoneNumber());
		
		writerRepo.save(existingWriter);
		
	}

	@Override
	public void DeleteWriter(String id) {

		Writer existingWriter = writerRepo.getById(id);
		writerRepo.delete(existingWriter);
		
	}

	@Override
	public Writer GetWriterByUsername(String username) {

		return writerRepo.getById(username);
		
	}

}
