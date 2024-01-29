package com.example.demo.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Notes;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.NotesService;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/user")
public class UserController 
{
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private NotesService notesService;
	
	@ModelAttribute
	public User getUser(Principal p, Model model)  //front end pe dikhane ke lia Model Attribute  use krt he
	{												//Model is a interface
		String email = p.getName();
		User user = userRepo.findByEmail(email);
		model.addAttribute("user", user);
		return user;
	}
	
	
	@GetMapping("/addNotes")
	public String addNotes()
	{
		return "addNotes";
	}
	
	@GetMapping("/editNotes/{id}")
	public String editNotes(@PathVariable int id, Model model)
	{
		Notes notes = notesService.getNotesById(id);
		model.addAttribute("n", notes);
		return "editNotes";
	}
	
	@GetMapping("/viewNotes")
	public String viewNotes(Model model, Principal principal)
	{
		User user = getUser(principal, model);
		List<Notes> notes = notesService.getnotesByUser(user);
		model.addAttribute("notesList", notes);
		return "viewNotes";
	}
	
	@PostMapping("/saveNotes")
	public String savenotes(@ModelAttribute Notes notes, HttpSession session, Principal p, Model m)
	{
		notes.setDate(LocalDate.now());
		notes.setUser(getUser(p, m));
		Notes saveNotes = notesService.saveNotes(notes);
		if(saveNotes!=null)
		{
			session.setAttribute("msg", "Register Success");
		}
		else
		{
			session.setAttribute("msg", "Something Error....");
		}
		return "redirect:/user/addNotes";
	}
	
	@PostMapping("/updateNotes")
	public String updateNotes(@ModelAttribute Notes notes, HttpSession session, Principal p, Model m)
	{
		notes.setDate(LocalDate.now());
		notes.setUser(getUser(p, m));
		Notes saveNotes = notesService.saveNotes(notes);
		if(saveNotes!=null)
		{
			session.setAttribute("msg", "Notes Update Success");
		}
		else
		{
			session.setAttribute("msg", "Something Error....");
		}
		return "redirect:/user/viewNotes";
	}
	
	@GetMapping("/deleteNotes/{id}")
	public String deleteNotes(@PathVariable int id, HttpSession session)
	{
		boolean f = notesService.deleteNotes(id);
		if(f==true)
		{
			session.setAttribute("msg", "Notes delete Success");
		}
		else
		{
			session.setAttribute("msg", "Something Error....");
		}
		return "redirect:/user/viewNotes";
	}
}
