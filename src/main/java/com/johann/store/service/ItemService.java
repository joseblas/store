/*
 * Copyright © 2006-2010. BSkyB Ltd All Rights reserved
 */
package com.johann.store.service;

import com.johann.store.exceptions.ItemNotFoundException;
import com.johann.store.model.Item;
import com.johann.store.repository.ItemRepository;

public interface ItemService {

	Item retrieveItem(String dvdReference) throws ItemNotFoundException;

	String getItemSummary(String dvdReference) throws ItemNotFoundException;

	void setItemRepository(ItemRepository dvdRepository);
    
}
