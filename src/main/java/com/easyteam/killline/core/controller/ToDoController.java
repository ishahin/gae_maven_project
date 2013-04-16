package com.easyteam.killline.core.controller;


import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.easyteam.killline.core.model.ToDo;
import com.easyteam.killline.core.repository.ToDoDAO;
import com.googlecode.objectify.Key;


/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/todo")
public class ToDoController {
	
	@Inject
	private ToDoDAO todoDAO;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public String home(Model model) {
		// TODO: need to figure out logging in a GAE world
		System.out.println("ToDo Controller");
		
		// the UI has both a list of current todos and a form for adding a new one
		List<ToDo> todoList = (List<ToDo>) todoDAO.listAll();
		model.addAttribute("todoList",todoList);
		model.addAttribute("todo",new ToDo());
		
		return "ToDoApplication"; // name of the .jsp under WEB-INF/views
	}

	@RequestMapping(value="/all", method=RequestMethod.POST)
	public String newtodo(Model model, @ModelAttribute("todo") ToDo todo) {
		// TODO: need to figure out logging in a GAE world
		System.out.println("newtodo handler");
		System.out.println("owner: " + todo.getOwner());
		System.out.println("summary: " + todo.getSummary());
		System.out.println("longDescription: " + todo.getSummary());
		System.out.println("url: " + todo.getUrl());		
		
		System.out.println("Persist? wait for it");

		try {
			Key<ToDo> key = todoDAO.save(todo);
			
			System.out.println("Newtodo");
			System.out.println("key: " + key.getId());
			System.out.println("Kind: " + key.getKind());
			System.out.println("Name: " + key.getName());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		List<ToDo> todoList = (List<ToDo>) todoDAO.listAll();
		System.out.println(todoList.size());
		model.addAttribute("todoList",todoList);
		model.addAttribute("todo",new ToDo());
		
		return "ToDoApplication";
	}

	@RequestMapping(value = "/delete/{key}", method = RequestMethod.GET)
	public String deleteToDo(Model model, @PathVariable("key") Long key) {
		System.out.println("Delete: " + key);
		
		try {
			todoDAO.removeId(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<ToDo> todoList = (List<ToDo>) todoDAO.listAll();
		model.addAttribute("todoList",todoList);
		model.addAttribute("todo",new ToDo());
		
		return "redirect:/todo/all";
	}
}

