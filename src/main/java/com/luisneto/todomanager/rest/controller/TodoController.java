package com.luisneto.todomanager.rest.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.luisneto.todomanager.domain.Todo;
import com.luisneto.todomanager.service.TodoService;
import com.luisneto.todomanager.service.UnexistentObjectException;

/**
 * @author Luis Neto
 */
@RestController
@RequestMapping("/todos")
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
		todo = todoService.createTodo(todo);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(linkTo(TodoController.class).slash(todo.getId()).toUri());

        return new ResponseEntity<Todo>(todo, headers, HttpStatus.CREATED);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/{todoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Todo> viewTodo(@PathVariable Integer todoId) {
		Todo todo;
		
		try {
			todo = todoService.retrieveTodo(todoId);
		} catch (UnexistentObjectException e) {
			return new ResponseEntity<Todo>(HttpStatus.NOT_FOUND);
		}
        
        return new ResponseEntity<Todo>(todo, HttpStatus.OK);
    }
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{todoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> deleteTodo(@PathVariable Integer todoId) {		
		try {
			todoService.deleteTodo(todoId);
		} catch (UnexistentObjectException e) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
        
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
