package com.amit.todo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.amit.todo.model.ToDo;
import com.amit.todo.service.ToDoService;
import com.amit.todo.util.Status;

@Controller
public class ToDoController {

	
	private final ToDoService toDoService;
	
	public ToDoController(ToDoService service) {
		this.toDoService = service;
	}
	
	@GetMapping("/todo")
	public ResponseEntity<List<ToDo>> getAll(){
		List<ToDo> list = toDoService.findAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@PostMapping("/todo")
	public ResponseEntity<String> newToDo(@RequestParam String title){
		ToDo todo = toDoService.save(title);
		if(todo != null) {
			return ResponseEntity.ok("Save successfully.");
		}
		return ResponseEntity.ok("Error occurred while saving.");
	}
	
	@DeleteMapping("/todo/{id}")
	public ResponseEntity<String> deleteToDo(@PathVariable Long id){
		String deleted = toDoService.delete(id);
		return ResponseEntity.ok(deleted);
	}
	
	@PostMapping("/todo/{id}/status")
	public ResponseEntity<String> changeStatus(@PathVariable Long id){
		String msg = toDoService.changeStatus(id);
		return ResponseEntity.ok(msg);
	}
	
	@GetMapping("/todo/status/{status}")
	public ResponseEntity<List<ToDo>> getByStatus(@PathVariable String status){
		List<ToDo> list = toDoService.getByStatus(status);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}
