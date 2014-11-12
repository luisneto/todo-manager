package com.luisneto.todomanager.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luisneto.todomanager.domain.Todo;
import com.luisneto.todomanager.repository.TodoRepository;

/**
 * @author Luis Neto
 */
@Service
public class TodoService {

	@Autowired
	private TodoRepository todoRepository;

	public Todo createTodo(Todo todo) {

		todo.setCreationDate(new Date());

		todoRepository.createTodo(todo);
		
		return todo;
	}

	public void setRepository(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	public void deleteTodo(Integer todoId) throws UnexistentObjectException {
		Todo todo = todoRepository.findTodo(todoId);

		if (todo != null) {
			todoRepository.deleteTodo(todo);
		} else {
			throw new UnexistentObjectException("No To-do found with ID = " + todoId);
		}
	}

	public Todo retrieveTodo(Integer todoId) throws UnexistentObjectException{
		Todo todo = todoRepository.findTodo(todoId); 
		
		if (todo == null) {
			throw new UnexistentObjectException("No To-do found with ID = " + todoId);
		}
		
		return todo;
	}
}
