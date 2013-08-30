/*
 * Copyright © 2006-2010. BSkyB Ltd All Rights reserved
 */
package com.johann.store;

import static com.johann.store.StoreConstants.ITEM_REFERENCE_PREFIX;

import java.util.HashMap;
import java.util.Map;

import com.johann.store.model.Item;
import com.johann.store.repository.ItemRepository;

public class RepositoryStub implements ItemRepository {

	
	
	public static final String DVD_TOPGUN_REFERENCE = ITEM_REFERENCE_PREFIX + "TG423";
	public static final String DVD_DIRTYDANCING_REFERENCE = ITEM_REFERENCE_PREFIX + "DD878";
	public static final String DVD_SHREK_REFERENCE = ITEM_REFERENCE_PREFIX + "S765";
	
	private static final Map<String, Item> dvds;

	static {
		dvds = new HashMap<String, Item>();
		dvds.put(DVD_TOPGUN_REFERENCE, new Item(DVD_TOPGUN_REFERENCE, "Topgun", "All action film"));
		dvds.put(DVD_DIRTYDANCING_REFERENCE, new Item(DVD_DIRTYDANCING_REFERENCE, "Dirty Dancing", "Nobody leaves baby in the corner"));
		dvds.put(DVD_SHREK_REFERENCE, new Item(DVD_SHREK_REFERENCE, "Shrek", "Big green monsters, they're just all " +
					"the rage these days, what with Hulk, Yoda, and that big ugly troll " +
					"thingy out of the first Harry Potter movie. But Shrek, the flatulent " +
					"swamp-dwelling ogre with a heart of gold (well, silver at least), " +
					"is more than capable of rivalling any of them."));
	}
	
	public Item retrieveItem(String reference) {
		return dvds.get(reference);
	}

}
