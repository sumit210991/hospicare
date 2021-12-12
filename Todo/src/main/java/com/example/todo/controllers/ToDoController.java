package com.example.todo.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.todo.models.ToDoList;
import com.example.todo.models.User;
import com.example.todo.repositories.ToDoRepository;
import com.example.todo.services.CustomUserDetailsService;

@Controller
public class ToDoController {

@Autowired
private CustomUserDetailsService userService;
	    
@Autowired
private ToDoRepository toDoRepository;
	    
	    @RequestMapping("/todo")
	    public ModelAndView notes() {
	        ModelAndView modelAndView = new ModelAndView();
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        User user = userService.findUserByEmail(auth.getName());
	        modelAndView.addObject("todo", toDoRepository.findAll());
	        modelAndView.addObject("currentUser", user);
	        modelAndView.addObject("fullName", "Welcome " + user.getFullname());
	        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
	        modelAndView.setViewName("todo");
	        return modelAndView;
	    }

	    @RequestMapping("/todo/create")
	    public ModelAndView create() {
	        ModelAndView modelAndView = new ModelAndView();
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        User user = userService.findUserByEmail(auth.getName());
	        modelAndView.addObject("currentUser", user);
	        modelAndView.addObject("fullName", "Welcome " + user.getFullname());
	        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
	        modelAndView.setViewName("create");
	        return modelAndView;
	    }

	    @RequestMapping("/todo/save")
	    public String save(@RequestParam String title, @RequestParam String content) {
	        ToDoList toDo = new ToDoList();
	        toDo.setTitle(title);
	        toDo.setContent(content);
	        toDo.setUpdated(new Date());
	        toDo.setStatus("In-Progress");
	        toDoRepository.save(toDo);

	        return "redirect:./show/" + toDo.getId();
	    }

	    @RequestMapping("/todo/show/{id}")
	    public ModelAndView show(@PathVariable Long id) {
	        ModelAndView modelAndView = new ModelAndView();
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        User user = userService.findUserByEmail(auth.getName());
	        modelAndView.addObject("currentUser", user);
	        modelAndView.addObject("fullName", "Welcome " + user.getFullname());
	        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
	        modelAndView.addObject("todo", toDoRepository.findById(id).orElse(null));
	        modelAndView.setViewName("show");
	        return modelAndView;
	    }

	    @RequestMapping("/todo/delete")
	    public String delete(@RequestParam Long id) {
	        ToDoList toDo = toDoRepository.findById(id).orElse(null);
	        toDoRepository.delete(toDo);

	        return "redirect:/todo";
	    }

	    @RequestMapping("/todo/edit/{id}")
	    public ModelAndView edit(@PathVariable Long id) {
	        ModelAndView modelAndView = new ModelAndView();
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        User user = userService.findUserByEmail(auth.getName());
	        modelAndView.addObject("currentUser", user);
	        modelAndView.addObject("fullName", "Welcome " + user.getFullname());
	        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
	        modelAndView.addObject("todo", toDoRepository.findById(id).orElse(null));
	        modelAndView.setViewName("edit");
	        return modelAndView;
	    }

	    @RequestMapping("/todo/update")
	    public String update(@RequestParam Long id, @RequestParam String title, @RequestParam String content,@RequestParam String status) {
	        ToDoList todo = toDoRepository.findById(id).orElse(null);
	        todo.setTitle(title);
	        todo.setContent(content);
	        todo.setUpdated(new Date());
	        todo.setStatus(status);
	        toDoRepository.save(todo);

	        return "redirect:./show/" + todo.getId();
	    }
}
