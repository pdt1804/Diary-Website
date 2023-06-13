package com.example.demo.ImpleFeign;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entities.Writer;
import com.example.demo.feigns.WriterFeign;

@Service
public class WriterFeignImple implements WriterFeign {

	@Autowired
	private WriterFeign writerFeign;
	
	/*
	 * private RestTemplate restTemplate;
	 * 
	 * public WriterFeignImple() { restTemplate = new RestTemplate(); }
	 */
	
	@Override
	public List<Writer> GetAllWriter() {
		
		return writerFeign.GetAllWriter();
		
	}

	@Override
	public void CreateNewWriters(Writer writer) {
		
		writerFeign.CreateNewWriters(writer);
		
	}

	@Override
	public void UpdateWriter(String id, Writer writer) {

		writerFeign.UpdateWriter(id, writer);
		
	}

	@Override
	public void DeleteWriter(String id) {

		writerFeign.DeleteWriter(id);
		
	}

}
