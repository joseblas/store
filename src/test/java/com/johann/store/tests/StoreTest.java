package com.johann.store.tests;

import static com.johann.store.StoreConstants.ITEM_REFERENCE_PREFIX;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.johann.store.exceptions.ItemNotFoundException;
import com.johann.store.model.Item;
import com.johann.store.repository.ItemRepository;
import com.johann.store.service.ItemService;
import com.johann.store.service.impl.ItemServiceImpl;

public class StoreTest implements IStoreTest {
	//@Mock
	@Mock ItemRepository mockRepository;
    ItemService dvdService;

    @Before
    public void setup() {
        mockRepository = mock(ItemRepository.class);
        dvdService = new ItemServiceImpl();
        dvdService.setItemRepository(mockRepository);
    }

    /* (non-Javadoc)
	 * @see com.sky.dvdstore.tests.IDvdStoreTest#validReferenceRetrival()
	 */
    @Override
	@Test
    public void validReferenceRetrival() throws ItemNotFoundException {
        String dvdReference = ITEM_REFERENCE_PREFIX+"TG423";
        Item sample1 = new Item(dvdReference, "Topgun", "All action film");

        when(mockRepository.retrieveItem(dvdReference)).thenReturn(sample1);
        
        Item result = dvdService.retrieveItem(dvdReference);

        assertNotNull("Dvd is null.", result);
        assertTrue("Dvd Title is not equals.", result.getTitle().equals(sample1.getTitle()));
    }

    
    /* (non-Javadoc)
	 * @see com.sky.dvdstore.tests.IDvdStoreTest#failDVDNotFound()
	 */
    @Override
	@Test(expected = ItemNotFoundException.class)
    public void failDVDNotFound() throws ItemNotFoundException {
        String dvdReference = ITEM_REFERENCE_PREFIX+"999";
        
        when(mockRepository.retrieveItem(dvdReference)).thenReturn(null);

        dvdService.retrieveItem(dvdReference);
        
     
    }

    /* (non-Javadoc)
	 * @see com.sky.dvdstore.tests.IDvdStoreTest#failIllegalArgument()
	 */
    @Override
	@Test(expected = IllegalArgumentException.class)
    public void failIllegalArgument() throws ItemNotFoundException {
        String dvdReference = "INVALID-TEXT";
        //Dvd sample1 = new Dvd(dvdReference, "Topgun", "All action film");

        //when(mockRepository.retrieveDvd(dvdReference)).thenReturn(null);

        dvdService.retrieveItem(dvdReference);
    }

    /* (non-Javadoc)
	 * @see com.sky.dvdstore.tests.IDvdStoreTest#validGetShortSummary()
	 */
    @Override
	@Test
    public void validGetShortSummary() throws ItemNotFoundException {
        String dvdReference = ITEM_REFERENCE_PREFIX+"TG423";
        Item sample1 = new Item(dvdReference, "Topgun", "All action film");

        when(mockRepository.retrieveItem(anyString())).thenReturn(sample1);

      //  dvdService.setDvdRepository(mockRepository);
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
        Item sample1 = new Item(dvdReference, "Shrek", "Big green monsters, they're just all " +
                "the rage these days, what with Hulk, Yoda, and that big ugly troll " +
                "thingy out of the first Harry Potter movie. But Shrek, the flatulent " +
                "swamp-dwelling ogre with a heart of gold (well, silver at least), " +
                "is more than capable of rivalling any of them.");

        when(mockRepository.retrieveItem(dvdReference)).thenReturn(sample1);

        String result = dvdService.getItemSummary(dvdReference);

        assertNotNull("Summary is null.", result);
        assertTrue("The summary not match with the expected result.", result.equals("[DVD-S765] Shrek - Big green monsters, they're just all the rage these days,..."));
    }

   


}
