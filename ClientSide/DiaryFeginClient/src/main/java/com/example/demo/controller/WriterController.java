package com.example.demo.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Writer;
import com.example.demo.feigns.DiaryFeign;
import com.example.demo.feigns.WriterFeign;
import com.example.demo.entities.ChangePassword;
import com.example.demo.entities.Diary;

@Controller
public class WriterController {

	@Autowired
	private WriterFeign writerFeign;

	@Autowired
	private DiaryFeign diaryFeign;
	
	@GetMapping("/")
	public String Login(Model model)
	{
		try 
		{
            File file = new File("..\\DiaryFeginClient\\src\\main\\java\\com\\example\\demo\\controller\\GetUsername.txt");
            if (file.length() == 0)
            {
        		Writer writer = new Writer();
        		model.addAttribute("writer", writer);
            	return "Login";
            }
            else
            {
            	return "MainWeb";
            }
        }
        catch (Exception e) 
		{
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
		return "Login";
	} 
	
	@GetMapping("/SignUp")
	public String SignUp(Model model)
	{
		Writer createWriter = new Writer();
		model.addAttribute("createWriter", createWriter);
		return "SignUp";
	}
	
	@PostMapping("/login")
	public String SubmitLogin(@ModelAttribute("writer") Writer writer, Model model)
	{
		List<Writer> listWriter = writerFeign.GetAllWriter();
		for (Writer p : listWriter)
		{
			if (p.getUsername().equals(writer.getUsername()))
			{
				if (p.getPassword().equals(writer.getPassword()))
				{
					try 
					{
				         FileWriter fileWriter = new FileWriter("..\\DiaryFeginClient\\src\\main\\java\\com\\example\\demo\\controller\\GetUsername.txt");
				         fileWriter.write(writer.getUsername());
				         fileWriter.close();
					   	 return "MainWeb";
				    }
					catch (IOException e) 
					{
						System.out.println(e.getMessage());
				         e.printStackTrace();
				    }
				}
				else
				{
					continue;
				}
			}
		}		
		model.addAttribute("Error", "Invalid username or password");
		return "Login";
	}
	
	@PostMapping("/SignUp")
	public String CreateWriter(@ModelAttribute("createWriter") Writer writer, Model model)
	{
		if (writer.getUsername().isBlank() || writer.getUsername().isEmpty())
		{
			return "redirect:/SignUp";
		}
		else
		{
			List<Writer> listWriters = writerFeign.GetAllWriter();
			for (Writer p : listWriters)
			{
				if (writer.getUsername().equals(p.getUsername()))
				{
					model.addAttribute("msgSignUp", "Username already exists");
					return "SignUp";
				}
			}
			writerFeign.CreateNewWriters(writer);
			return "redirect:/";
		}
	}
	
	@GetMapping("/MainWeb")
	public String Home(Model model)
	{
		return "MainWeb";
	}
	
	@GetMapping("/Profile")
	public String Profile(Model model)
	{
		try 
		{
            File file = new File("..\\DiaryFeginClient\\src\\main\\java\\com\\example\\demo\\controller\\GetUsername.txt"); 
            Scanner scanner = new Scanner(file);
            String username = "";
            while (scanner.hasNextLine()) 
            {
                username = scanner.nextLine();
            }
            List<Writer> listWriter = writerFeign.GetAllWriter();
            for (Writer p : listWriter)
            {
            	if (p.getUsername().equals(username))
            	{
            		model.addAttribute("currentWriter", p);
            		return "Profile";
            	}
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
		return "Profile";
	}
	
	@PostMapping("/UpdateWriter")
	public String UpdateWriter(@ModelAttribute("currentWriter") Writer writer)
	{
		try
		{
			 File file = new File("..\\DiaryFeginClient\\src\\main\\java\\com\\example\\demo\\controller\\GetUsername.txt"); 
	         Scanner scanner = new Scanner(file);
	         String username = "";
	         while (scanner.hasNextLine()) 
	         {
	             username = scanner.nextLine();
	         }
	         if (!username.isEmpty() && !username.isBlank())
	         {
	        	 for (Writer p : writerFeign.GetAllWriter())
	        	 {
	        		 if (p.getUsername().equals(username))
	        		 {
	        			 writer.setPassword(p.getPassword());
	        			 writerFeign.UpdateWriter(username, writer);
	        			 break;
	        		 }
	        	 }
	         }
	         scanner.close();
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return "redirect:/MainWeb";
	}
	
	@GetMapping("/Setting")
	public String Setting(Model model)
	{
		ChangePassword changePassword = new ChangePassword();
		model.addAttribute("changePassword", changePassword);
		return "Setting";
	}
	
	@PostMapping("/ChangePassword")
	public String ChangePassword(@ModelAttribute("changePassword") ChangePassword changePassword, Model model)
	{

		if (changePassword.getVerifyNewPassword().equals(changePassword.getNewPassword()))
		{
			try 
			{
	            File file = new File("..\\DiaryFeginClient\\src\\main\\java\\com\\example\\demo\\controller\\GetUsername.txt"); 
	            Scanner scanner = new Scanner(file);
	            String username = "";
	            while (scanner.hasNextLine()) 
	            {
	                username = scanner.nextLine();
	            }
	            if (!username.isBlank() && !username.isEmpty())
	            {
		            List<Writer> listWriter = writerFeign.GetAllWriter();
        			model.addAttribute("msg","Current password isn't correct");
		            for (Writer p : listWriter)
		            {
		            	if (p.getUsername().equals(username))
		            	{
		            		if (changePassword.getCurrentPassword().equals(p.getPassword()))
		            		{
		            			p.setPassword(changePassword.getVerifyNewPassword());
		            			writerFeign.UpdateWriter(p.getUsername(), p);
		            			model.addAttribute("msg","Changed successfully");
		            			break;
		            		}
		            	}
		            }
	            }
	            scanner.close();
	        } 
			catch (FileNotFoundException e) 
			{
	            System.out.println(e.getMessage());
	            e.printStackTrace();
	        }
		}
		else
		{
			model.addAttribute("msg","New password and verify new password are not same");
		}
		return "Setting";
	}
	
	@GetMapping("/Logout")
	public String LogOut(Model model)
	{
		 try 
		 {
	            FileWriter fileWriter = new FileWriter("..\\DiaryFeginClient\\src\\main\\java\\com\\example\\demo\\controller\\GetUsername.txt", false);
	            fileWriter.write("");
	            fileWriter.close();
	     } 
		 catch (IOException e) 
		 {
	            System.out.println(e.getMessage());
	     }
		 return "redirect:/";
	}
	
	@GetMapping("/DeleteUser")
	public String DeleteUser(Model model)
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
            	Writer writer = new Writer();
            	List<Writer> listWriters = writerFeign.GetAllWriter();
            	for (Writer p : listWriters)
            	{
            		if (p.getUsername().equals(username))
            		{
            			writer = p;
            			break;
            		}
            	}
            	List<Diary> listDiaries = diaryFeign.GetAllDiaries();
            	for (Diary p : listDiaries)
            	{
            		if (p.getWriter().getUsername().equals(writer.getUsername()))
            		{
            			diaryFeign.DeleteDiary(p.getID());
            		}
            	}
            	writerFeign.DeleteWriter(username);
            }
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
    	return LogOut(model); // Call Logout function to return Login form and delete username stored in file txt
	}
}
