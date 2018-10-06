package com.amit.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amit.todo.model.ToDo;
import com.amit.todo.repository.ToDoRepository;
import com.amit.todo.util.Status;

@Service
public class ToDoService {

	private final ToDoRepository toDoRepository;
	
	public ToDoService(ToDoRepository toDoRepository) {
		this.toDoRepository = toDoRepository;
	}

	public List<ToDo> findAll() {
		return toDoRepository.findAll();
	}

	public ToDo save(String title) {
		ToDo toDo = null;
		try {
			toDo = new ToDo();
			toDo.setTitle(title);
			toDo.setStatus(Status.NEW);
			toDo = toDoRepository.save(toDo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toDo;
	}

	public String delete(Long id) {
		String msg = "Todo with id " + id + " doesn't exits";
		try {
			ToDo toDo = toDoRepository.getOne(id);
			if(toDo != null) {
				toDoRepository.deleteById(id);
				msg = "Deleted successfully.";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	public String changeStatus(Long id) {
		String msg = "Todo with id " + id + " doesn't exits"; 
		try {
			ToDo toDo = toDoRepository.getOne(id);
			if(toDo != null) {
				toDo.setStatus(Status.COMPLETED);
				toDoRepository.save(toDo);
				msg = "Task completed successfully.";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	public List<ToDo> getByStatus(String status) {
		return toDoRepository.findByStatus(Status.valueOf(status));
	}
}
