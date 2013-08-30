package com.johann.store.service.impl;

import static com.johann.store.StoreConstants.ITEM_REFERENCE_PREFIX;

import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;

import com.johann.store.exceptions.ItemNotFoundException;
import com.johann.store.model.Item;
import com.johann.store.repository.ItemRepository;
import com.johann.store.service.ItemService;
public class ItemServiceImpl implements ItemService {

    
	private ItemRepository dvdRepository;

    
    public Item retrieveItem(String dvdReference) throws ItemNotFoundException {
        validateDvdReference(dvdReference);
        return get(dvdReference);
    }

    
    public String getItemSummary(String dvdReference) throws ItemNotFoundException {
        validateDvdReference(dvdReference);
        Item dvdResult = get(dvdReference);
        return getSummaryFrom(dvdResult);
    }

      

    private void validateDvdReference(String dvdReference) throws IllegalArgumentException {
        if (!dvdReference.startsWith(ITEM_REFERENCE_PREFIX)) {
            throw new IllegalArgumentException(String.format("DVD reference must start with %s", ITEM_REFERENCE_PREFIX));
        }
    }

    private Item get(String dvdReference) throws ItemNotFoundException {
        Item result = dvdRepository.retrieveItem(dvdReference);
        if (result == null) {
            throw new ItemNotFoundException( String.format("DVD %s not found.", dvdReference ));
        }
        return result;
    }

    private String getSummaryFrom(Item dvd) {
        return String.format("[%s] %s - %s", dvd.getReference(),dvd.getTitle(),getNFirstWords(dvd.getReview(), 10));
    }

    private String getNFirstWords(String source, int numWords) {
        StringBuilder result = new StringBuilder();
        StringTokenizer st = new StringTokenizer(source, " ");
        for(int i = 0; i < numWords && st.hasMoreTokens(); i++) {
            result.append(st.nextToken());
            result.append(" ");
        }
        String words = result.substring (0,result.length() - 1);

        if (st.hasMoreTokens()) {
        	words += "...";
        }

        return words;
    }
    public void setItemRepository(ItemRepository dvdRepository) {
        this.dvdRepository = dvdRepository;
    }

}
