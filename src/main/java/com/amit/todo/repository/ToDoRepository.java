package com.amit.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amit.todo.model.ToDo;
import com.amit.todo.util.Status;

public interface ToDoRepository extends JpaRepository<ToDo, Long>{

	List<ToDo> findByStatus(Status status);
}
