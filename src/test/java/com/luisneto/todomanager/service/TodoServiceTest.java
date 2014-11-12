package com.luisneto.todomanager.service;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.luisneto.todomanager.domain.Todo;
import com.luisneto.todomanager.repository.TodoRepository;

/**
 * @author Luis Neto
 */
public class TodoServiceTest {
	
	private TodoService todoService;
	
	private TodoRepository mockTodoRepository;
	private Todo mockTodo;
	
	@Before
	public void setUp() {
		mockTodoRepository = mock(TodoRepository.class);
		mockTodo = mock(Todo.class);
		
		todoService = new TodoService();
		todoService.setRepository(mockTodoRepository);
	}
	
	@Test
	public void shouldSaveTodo() {
		todoService.createTodo(mockTodo);
		
		verify(mockTodoRepository, times(1)).createTodo(mockTodo);
	}
	
	@Test
	public void shouldSetDateWhenSavingNewTodo() {
		todoService.createTodo(mockTodo);
		
		verify(mockTodo).setCreationDate((Date) any());
	}
	
	@Test
	public void shouldDeleteExistingTodo() throws Exception {
		when(mockTodoRepository.findTodo(anyInt())).thenReturn(mockTodo);
		
		todoService.deleteTodo(anyInt());
		
		verify(mockTodoRepository, times(1)).deleteTodo(mockTodo);
	}
	
	@Test
	public void shouldThrowExceptionIfTryingToDeleteUnexistingTodo() throws UnexistentObjectException {
		when(mockTodoRepository.findTodo(anyInt())).thenReturn(null);
		
		catchException(todoService).deleteTodo(anyInt());
		
		assertThat(caughtException()).isInstanceOf(UnexistentObjectException.class);
		verify(mockTodoRepository, never()).deleteTodo(any(Todo.class));
	}
	
	@Test
	public void shouldThrowExceptionIfTryingToRetrieveUnexistingTodo() throws UnexistentObjectException {
		when(mockTodoRepository.findTodo(anyInt())).thenReturn(null);
	
		catchException(todoService).retrieveTodo(anyInt());
		
		assertThat(caughtException()).isInstanceOf(UnexistentObjectException.class);
		
	}
}
