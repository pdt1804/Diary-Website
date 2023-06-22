package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entities.DeletedDiary;
import com.example.demo.entities.Diary;
import com.example.demo.entities.Writer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.feigns.DeletedDiaryFeign;
import com.example.demo.feigns.DiaryFeign;
import com.example.demo.feigns.WriterFeign;

@Controller
public class DiaryController {

	@Autowired
	private DiaryFeign diaryFeign;
	
	@Autowired
	private WriterFeign writerFeign;
	
	@Autowired
	private DeletedDiaryFeign deletedDiaryFeign;
	
	@GetMapping("/Diary")
	public String Diary(Model model)
	{
		List<Diary> ListDiary = diaryFeign.GetAllDiaries();
		List<Diary> userAccountListDiary = new ArrayList<>();
		try
		{
			File file = new File("..\\DiaryFeginClient\\src\\main\\java\\com\\example\\demo\\controller\\GetUsername.txt");
			Scanner sc = new Scanner(file);
			String username = "";
			while (sc.hasNextLine())
			{
				username = sc.nextLine();
			}
			if (!username.isBlank() && !username.isEmpty())
			{
				for (Diary p : diaryFeign.GetAllDiaries())
				{
					if (p.getWriter().getUsername().equals(username))
					{
						userAccountListDiary.add(p);
						
						// Sắp xếp theo thời gian gần nhất
						Collections.sort(userAccountListDiary, new Comparator<Diary>() {

							@Override
							public int compare(Diary o1, Diary o2) {
								// TODO Auto-generated method stub
								return o2.getDateWrittenOrUpdated().compareTo(o1.getDateWrittenOrUpdated());
							}
							
						});
						model.addAttribute("listDiaries", userAccountListDiary);
					}
				}
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return "Diary";
	}
	
	@GetMapping("/Diary/WriteDiary/{id}")
	public String WriteDiary(@PathVariable("id") Long ID, Model model)
	{
		List<Diary> listDiary = diaryFeign.GetAllDiaries();
		for (Diary p : listDiary)
		{
			if (p.getID() == ID)
			{
				model.addAttribute("selectedDiary", p);
				break;
			}
		}
		return "WriteDiary";
	}
	
	@PostMapping("/Diary/WriteDiary/{id}")
	public String UpdateDiary(@ModelAttribute("selectedDiary") Diary diary, @PathVariable("id") Long ID)
	{
		List<Diary> listDiary = diaryFeign.GetAllDiaries();
		for (Diary p : listDiary)
		{
			if (p.getID() == diary.getID())
			{
				Date date = new Date();
				diary.setDateWrittenOrUpdated(date);
				diaryFeign.UpdateDiary(diary, p.getID());
				break;
			}
		}
		return "redirect:/Diary";
	}
	
	@GetMapping("/Diary/CreateDiary")
	public String CreateNewDiary(Model model)
	{
		Diary diary = new Diary();
		try
		{
			File file = new File("..\\DiaryFeginClient\\src\\main\\java\\com\\example\\demo\\controller\\GetUsername.txt");
			Scanner sc = new Scanner(file);
			String username = "";
			while (sc.hasNextLine())
			{
				username = sc.nextLine();
			}
			if (!username.isBlank() && !username.isEmpty())
			{
				List<Writer> listWriter = writerFeign.GetAllWriter(); 
				for (Writer p : listWriter)
				{
					if (p.getUsername().equals(username))
					{
						diary.setWriter(p);
						model.addAttribute("newDiary", diary);
						break;
					}
				}
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return "CreateDiary";
	}
	
	@GetMapping("/Diary/RecoverDiary")
	public String RecoverDiary(Model model)
	{
		try
		{
			File file = new File("..\\DiaryFeginClient\\src\\main\\java\\com\\example\\demo\\controller\\GetUsername.txt");
			Scanner sc = new Scanner(file);
			String username = "";
			while (sc.hasNextLine())
			{
				username = sc.nextLine();
			}
			if (!username.isBlank() && !username.isEmpty())
			{
				List<DeletedDiary> list = deletedDiaryFeign.getAllDeleted(username);
				
				// Sắp xếp theo thứ tự thời gian xoá
				Collections.sort(list, new Comparator<DeletedDiary>() {

					@Override
					public int compare(DeletedDiary o1, DeletedDiary o2) {
						// TODO Auto-generated method stub
						return o2.getDateDeleted().compareTo(o1.getDateDeleted());
					}
					
				});
				model.addAttribute("ListDeletedDiary", list);
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return "RecoverDiary";
	}
	
	@PostMapping("/Diary/CreateDiary")
	public String CreateDiary(@ModelAttribute("newDiary") Diary diary)
	{
		// Date date = new Date();
		try
		{
			File file = new File("..\\DiaryFeginClient\\src\\main\\java\\com\\example\\demo\\controller\\GetUsername.txt");
			Scanner sc = new Scanner(file);
			String username = "";
			while (sc.hasNextLine())
			{
				username = sc.nextLine();
			}
			if (!username.isBlank() && !username.isEmpty())
			{
				List<Writer> listWriter = writerFeign.GetAllWriter(); 
				for (Writer p : listWriter)
				{
					if (p.getUsername().equals(username))
					{
						diary.setWriter(p);
						diaryFeign.CreateDiary(diary);
						break;
					}
				}
			}
			sc.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return "redirect:/Diary";
	}
	
	@GetMapping("/Diary/DeleteDiary/{id}")
	public String DeleteDiary(@PathVariable("id") Long ID)
	{
		Optional<Diary> diary = diaryFeign.GetAllDiaries()
										  .stream()
										  .filter(p -> p.getID().equals(ID))
										  .findFirst();
		
		deletedDiaryFeign.AddDeletedDiary(new DeletedDiary(diary.get().getID(), 
										            	   diary.get().getWriter().getUsername(), 
													       diary.get().getHeader(),
													       new Date(),
													       diary.get().getDateWrittenOrUpdated(),
													       diary.get().getContent()));
		diaryFeign.DeleteDiary(ID);
		return "redirect:/Diary";
	}
	
	@GetMapping("/Diary/RecoverDiary/{id}")
	public String SeenRecoverDeletedDiary(Model model, @PathVariable("id") Long id)
	{
		model.addAttribute("selectedDiary", deletedDiaryFeign.getAllDeleted()
															 .stream()
															 .filter(p -> p.getID().equals(id))
															 .findFirst()
															 .get());
		return "SeenDeletedDiary";
	}
	
	@PostMapping("/Diary/RecoverDiary/Recover/{id}")
	public String RecoverDeletedDiary(@PathVariable("id") Long id)
	{
		Optional<DeletedDiary> deletedDiary = deletedDiaryFeign.getAllDeleted()
				   .stream()
				   .filter(p -> p.getID().equals(id))
				   .findFirst();
		Diary diary = new Diary();
		diary.setID(deletedDiary.get().getID());
		diary.setHeader(deletedDiary.get().getHeader());
		diary.setDateWrittenOrUpdated(deletedDiary.get().getLastEdited());
		diary.setContent(deletedDiary.get().getContent());
		diary.setWriter(writerFeign.GetAllWriter()
								   .stream()
								   .filter(p -> p.getUsername().equals(deletedDiary.get().getUsername()))
								   .findFirst()
								   .get());
		diaryFeign.CreateDiary(diary);
		deletedDiaryFeign.RecoverDeletedDiary(deletedDiary.get());
		return "redirect:/Diary/RecoverDiary";
	}
	
	@GetMapping("/Diary/RecoverDiary/SeenDeletedDiary/DeletedPermanently/{id}")
	public String DeletePermanently(@PathVariable("id") Long id)
	{
		deletedDiaryFeign.DeletePermanently(deletedDiaryFeign.getAllDeleted()
															 .stream()
															 .filter(p -> p.getID().equals(id))
															 .findFirst()
															 .get());
		return "redirect:/Diary/RecoverDiary";
	}
}
