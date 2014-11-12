package com.luisneto.todomanager.rest.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.luisneto.todomanager.domain.Category;
import com.luisneto.todomanager.domain.Todo;
import com.luisneto.todomanager.service.TodoService;
import com.luisneto.todomanager.service.UnexistentObjectException;

/**
 * @author Luis Neto
 */
public class TodoControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private TodoController controller;

	@Mock
	private TodoService mockTodoService;
	
	private String TEST_TODO_TITLE = "Test to-do title";
	private String TEST_TODO_DESCRIPTION = "Test to-do description.";
	private Integer TEST_TODO_ID = 123;
	
	private Todo testTodo;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		this.mockMvc = standaloneSetup(controller).build();

		testTodo = new Todo(TEST_TODO_TITLE, TEST_TODO_DESCRIPTION);
		testTodo.setId(TEST_TODO_ID);
	}

	@Test
	public void shouldUseHttpCreatedWhenCreatingTodo() throws Exception {

		when(mockTodoService.createTodo(any(Todo.class))).thenReturn(testTodo);

		this.mockMvc.perform(
				post("/todos").content(standardTodoJSON())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isCreated());
	}

	@Test
	public void shouldReturnLocationHeaderWhenCreatingTodo() throws Exception {

		when(mockTodoService.createTodo(any(Todo.class))).thenReturn(testTodo);

		this.mockMvc.perform(post("/todos").content(standardTodoJSON())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(header().string("Location", Matchers.endsWith("/todos/" + testTodo.getId())));
	}
	
	@Test
	  public void shouldUseHttpOKWhenViewingTodo() throws Exception {

	    when(mockTodoService.retrieveTodo(anyInt())).thenReturn(testTodo);

	    this.mockMvc.perform(
				get("/todos/{id}", TEST_TODO_ID)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void shouldUseHttpNotFoundWhenViewingInvalidTodo() throws Exception {

		when(mockTodoService.retrieveTodo(anyInt())).thenThrow(UnexistentObjectException.class);

		this.mockMvc.perform(
				get("/todos/{id}", anyInt())
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void todoShouldBeRenderedCorrectlyOnView() throws Exception {

		Category category = new Category("Test category");
		category.setId(456);
		testTodo.setCategory(category);

		when(mockTodoService.retrieveTodo(anyInt())).thenReturn(testTodo);

		this.mockMvc.perform(
				get("/todos/{id}", TEST_TODO_ID)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(jsonPath("$.title").value(TEST_TODO_TITLE))
				.andExpect(jsonPath("$.description").value(TEST_TODO_DESCRIPTION))
				.andExpect(jsonPath("$.id").value(TEST_TODO_ID))
				.andExpect(jsonPath("$..category.title").value("Test category"))
				.andExpect(jsonPath("$..category.id").value(456));
		
		//TODO: When the API gets HATEOAS, links must also be tested.
	}

	private String standardTodoJSON() {
		return "{ \"title\": \"" + TEST_TODO_TITLE + "\", "
				+ "\"description\": \"" + TEST_TODO_DESCRIPTION + "\" }";
	}

}
