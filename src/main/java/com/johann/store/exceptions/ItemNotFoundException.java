/*
 * Copyright © 2006-2010. BSkyB Ltd All Rights reserved
 */

package com.johann.store.exceptions;

public class ItemNotFoundException extends Exception {
    
	private static final long serialVersionUID = 1L;

	public ItemNotFoundException(String message){
    	super(message);
    }
}
