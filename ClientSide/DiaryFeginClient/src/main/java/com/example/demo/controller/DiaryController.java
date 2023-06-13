package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.entities.Diary;
import com.example.demo.entities.Writer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.feigns.DiaryFeign;
import com.example.demo.feigns.WriterFeign;

@Controller
public class DiaryController {

	@Autowired
	private DiaryFeign diaryFeign;
	
	@Autowired
	private WriterFeign writerFeign;
	
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
		diaryFeign.DeleteDiary(ID);
		return "redirect:/Diary";
	}
}
