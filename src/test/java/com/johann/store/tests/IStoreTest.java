package com.johann.store.tests;

import org.junit.Test;

import com.johann.store.exceptions.ItemNotFoundException;

public interface IStoreTest {

	public abstract void validReferenceRetrival() throws ItemNotFoundException;

	public abstract void failDVDNotFound() throws ItemNotFoundException;

	public abstract void failIllegalArgument() throws ItemNotFoundException;

	public abstract void validGetShortSummary() throws ItemNotFoundException;

	public abstract void validGetLargeSummary() throws ItemNotFoundException;

}