package com.luisneto.todomanager.service;

/**
 * @author Luis Neto
 */
public class UnexistentObjectException extends Exception {

	private static final long serialVersionUID = 1117360138825174072L;
	
	public UnexistentObjectException(String message) {
		super(message);
	}
	
}
