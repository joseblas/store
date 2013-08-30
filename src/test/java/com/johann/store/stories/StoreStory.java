package com.johann.store.stories;

import java.util.logging.Logger;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.Assert;

import com.johann.store.RepositoryStub;
import com.johann.store.exceptions.ItemNotFoundException;
import com.johann.store.model.Item;
import com.johann.store.repository.ItemRepository;
import com.johann.store.service.ItemService;
import com.johann.store.service.impl.ItemServiceImpl;

public class StoreStory extends JUnitStory{

	private static Logger log = Logger.getLogger("StoreStory");
	ItemRepository repo;
    ItemService itemService;
    String searchElement;
	
	 
	    @Given("a store")
	    public void givenAFreeDvdStore() {
	    	repo = new RepositoryStub();
	        itemService = new ItemServiceImpl();
	        itemService.setItemRepository(repo);
	    }
	     
	    
	    
	    @When("the element $element is searched for")
	    public void searchForElement(String element) {
	        searchElement = element;
	        
	    }
	    
	    
	    @Then("the resulting element should be $result")
	    public void theResultingElementShouldBe(String result) throws ItemNotFoundException {
	    	Assert.assertEquals(itemService.retrieveItem(searchElement).getTitle(), result);
	    	
	    }
	    
	    
	    @Then("the resulting summary should be $result")
	    public void theResultingSummaryShouldBe(String result) throws ItemNotFoundException {
	    	Assert.assertEquals(itemService.getItemSummary(searchElement), result);
	    	
	    }
	    
	    
	     
	    @Then("excepted exception $result")
	    public void exceptedException(String result) throws ItemNotFoundException {
	    	try{
	    		itemService.retrieveItem(searchElement);
	    		Assert.fail("One exception expected: DvdNotFoundException");
	    	}catch(IllegalArgumentException iae){
	    		Assert.assertEquals(iae.getMessage(), result);
	    	}
	    	
	    }
	    
	    @Then("not founded DVD")
	    public void notFounded(){
	    	Item dvd = null;
	    	try{
	    		dvd = itemService.retrieveItem(searchElement);
	    		Assert.fail("One exception expected: DvdNotFoundException");
	    	}catch(ItemNotFoundException e){

	    	}
	    	Assert.assertNull(dvd);
	    }
	    
	
	    
	 // Here we specify the configuration, starting from default MostUsefulConfiguration, and changing only what is needed
	    @Override
	    public Configuration configuration() {
	        return new MostUsefulConfiguration()
	            // where to find the stories
	            .useStoryLoader(new LoadFromClasspath(this.getClass()))  
	            // CONSOLE and TXT reporting
	            .useStoryReporterBuilder(new StoryReporterBuilder().withDefaultFormats()); 
	    }
	 
	    // Here we specify the steps classes
	    @Override
	    public InjectableStepsFactory stepsFactory() {        
	        // varargs, can have more that one steps classes
	        return new InstanceStepsFactory(configuration(), new StoreStory());
	    }
	    
	   
}
