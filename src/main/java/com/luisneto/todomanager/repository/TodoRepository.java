package com.luisneto.todomanager.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luisneto.todomanager.domain.Todo;

/**
 * @author Luis Neto
 */
@Repository
@Transactional
public class TodoRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public Todo createTodo(Todo todo) {
		entityManager.persist(todo);

		return todo;
	}

	public void deleteTodo(Todo todo) {
		entityManager.remove(todo);
	}
	
	public Todo findTodo(Integer todoId) {
		Todo todo = entityManager.find(Todo.class, todoId);
		
		return todo;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	
}
