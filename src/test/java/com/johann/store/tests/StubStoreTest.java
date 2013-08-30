package com.johann.store.tests;

import static com.johann.store.RepositoryStub.DVD_TOPGUN_REFERENCE;
import static com.johann.store.StoreConstants.ITEM_REFERENCE_PREFIX;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.johann.store.RepositoryStub;
import com.johann.store.exceptions.ItemNotFoundException;
import com.johann.store.model.Item;
import com.johann.store.repository.ItemRepository;
import com.johann.store.service.ItemService;
import com.johann.store.service.impl.ItemServiceImpl;

public class StubStoreTest implements IStoreTest 
{

	ItemRepository repo;
    ItemService dvdService;
	
	@Before
    public void setup() {
		repo = new RepositoryStub();
        dvdService = new ItemServiceImpl();
        dvdService.setItemRepository(repo);
    }
	
	
	 /* (non-Javadoc)
		 * @see com.sky.dvdstore.tests.IDvdStoreTest#validReferenceRetrival()
		 */
	@Override
	@Test
    public void validReferenceRetrival() throws ItemNotFoundException {
        String dvdReference = DVD_TOPGUN_REFERENCE;
        Item dvd = new Item(dvdReference, "Topgun", "All action film");

        Item result = dvdService.retrieveItem(dvdReference);

        assertNotNull("Dvd is null.", result);
        assertTrue("Dvd Title is not equals.", result.getTitle().equals(dvd.getTitle()));
    }

	 /* (non-Javadoc)
		 * @see com.sky.dvdstore.tests.IDvdStoreTest#failDVDNotFound()
		 */
    @Override
	@Test(expected = ItemNotFoundException.class)
	public void failDVDNotFound() throws ItemNotFoundException {
        String dvdReference = ITEM_REFERENCE_PREFIX+"999";

        dvdService.setItemRepository(repo);
        dvdService.retrieveItem(dvdReference);
    }

    /* (non-Javadoc)
	 * @see com.sky.dvdstore.tests.IDvdStoreTest#failIllegalArgument()
	 */
    @Override
    @Test(expected = IllegalArgumentException.class)
    public void failIllegalArgument() throws ItemNotFoundException {
        String dvdReference = "INVALID-TEXT";
        Item dvd = new Item(dvdReference, "Topgun", "All action film");

        dvdService.retrieveItem(dvdReference);
    }

    /* (non-Javadoc)
   	 * @see com.sky.dvdstore.tests.IDvdStoreTest#validGetShortSummary()
   	 */
    @Override
   	@Test
    public void validGetShortSummary() throws ItemNotFoundException {
        String dvdReference =DVD_TOPGUN_REFERENCE;
        Item dvd = new Item(dvdReference, "Topgun", "All action film");

        String result = dvdService.getItemSummary(dvdReference);

        assertNotNull("Summary is null.", result);
        assertTrue("The summary not match with the expected result.", result.equals("[DVD-TG423] Topgun - All action film"));
    }

    /* (non-Javadoc)
   	 * @see com.sky.dvdstore.tests.IDvdStoreTest#validGetLargeSummary()
   	 */
    @Override
   	@Test
    public void validGetLargeSummary() throws ItemNotFoundException {
        String dvdReference = ITEM_REFERENCE_PREFIX+"S765";

        String result = dvdService.getItemSummary(dvdReference);

        assertNotNull("Summary is null.", result);
        assertTrue("The summary not match with the expected result.", result.equals("[DVD-S765] Shrek - Big green monsters, they're just all the rage these days,..."));
    }

    	
}
