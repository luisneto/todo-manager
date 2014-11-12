package com.luisneto.todomanager.repository;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.luisneto.todomanager.config.CoreConfig;
import com.luisneto.todomanager.domain.Todo;

/**
 * @author Luis Neto
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CoreConfig.class})
public class TodoRepositoryIntegrationTest {
	
	private Todo todo;
	
	@Autowired
	private TodoRepository repository;
	
	@Before
	public void setUp() {
		String title = "Test Todo";
		String description = "Test description";
		todo = new Todo(title, description);
	}
	
	@Test
	public void shouldPersistTodo() throws Exception {
		repository.createTodo(todo);
		
		//TO BE COMPLETED		
	}
}
