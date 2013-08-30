/*
 * Copyright © 2006-2010. BSkyB Ltd All Rights reserved
 */

package com.johann.store.repository;

import com.johann.store.model.Item;

public interface ItemRepository {

	Item retrieveItem(String reference);
    
}
