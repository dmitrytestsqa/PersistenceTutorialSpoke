package org.persistence.tutorial;

import java.util.ArrayList;
import junit.framework.TestCase;

public class MyLibraryTest extends TestCase {

	private Book b1;
	private Book b2;
	private Person p1;
	private Person p2;
	private MyLibrary ml;

	public void addItems() {
		ml.addBook(b1);
		ml.addBook(b2);
		ml.addPerson(p1);
		ml.addPerson(p2);
	}
	
	public void setup() {
		b1 = new Book("Book1");
		b2 = new Book("Book2");
		p1 = new Person();
		p1.setName("Fred");
		p2 = new Person();
		p2.setName("Sue");
		ml = new MyLibrary("Test");
	}

	public void testMyLibrary() {
		setup();
		assertEquals("Test", ml.getName());
		assertEquals(0, ml.getBooks().size());
		assertEquals(0, ml.getPeople().size());
		assertTrue(ml.books instanceof ArrayList);
		assertTrue(ml.people instanceof ArrayList);
	}

	public void testAddBook() {
		setup();
		ml.addBook(b1);
		ml.addBook(b2);
		
		assertEquals(2, ml.getBooks().size());
		assertEquals(0, ml.getBooks().indexOf(b1));
		assertEquals(1, ml.getBooks().indexOf(b2));
	}

	public void testRemoveBook(){
		setup();
		addItems();
		ml.removeBook(b1);
		assertEquals(1, ml.getBooks().size());
		assertEquals(0, ml.getBooks().indexOf(b2));
		
		ml.removeBook(b2);
		assertEquals(0, ml.getBooks().size());
		
	}

	public void testCheckOutBook() {
		setup();
		addItems();
	
		assertTrue("Book did not check out correctly", ml.checkOut(b1, p1));
	}

	public void testBookShouldNotBeCheckedOutTwice(){
		setup();
		addItems();
		ml.checkOut(b1, p1);

		assertFalse("Book was alread checked out", ml.checkOut(b1,p2));
	}

	public void testCheckInBook(){
		setup();
		addItems();
		ml.checkOut(b1, p1);
		
		assertTrue("Book check in failed", ml.checkIn(b1));
	}

	public void testBookShouldNotBeCheckedInTwice(){
		setup();
		addItems();
		ml.checkOut(b1, p1);
		ml.checkIn(b1);
		assertFalse("Book was already checked in", ml.checkIn(b1));
	}

	public void testBookShouldNotBeChekedInIfItWasNeverCheckedOut(){
		setup();
		addItems();
		
		assertFalse("Book was never checked out", ml.checkIn(b2));
	}

	public void testPersonShoulNotExceedMaximumBooksLimit(){
		setup();
		addItems();
		p1.setMaximumBooks(1);
		ml.checkOut(b1, p1);
		
		assertFalse("Second book should not have checked out", ml.checkOut(b2, p1));
	}

	public void testGetBooksForPerson() {
		setup();
		addItems();
		assertEquals(0, ml.getBooksForPerson(p1).size());
		
		ml.checkOut(b1, p1);
		ArrayList<Book> testBooks = ml.getBooksForPerson(p1);
		assertEquals(1, testBooks.size());
		assertEquals(0, testBooks.indexOf(b1));
		
		ml.checkOut(b2, p1);
		testBooks = ml.getBooksForPerson(p1);
		assertEquals(2, testBooks.size());
		assertEquals(1, testBooks.indexOf(b2));
		
	}

	public void testGetAvailableBooks() {
		setup();
		addItems();
				
		ArrayList<Book> testList = ml.getAvailableBooks();
		assertEquals(2,testList.size());
		assertEquals(0, testList.indexOf(b1));
		assertEquals(1, testList.indexOf(b2));
		
		ml.checkOut(b1, p1);
		testList = ml.getAvailableBooks();
		assertEquals(1,testList.size());
		assertEquals(0, testList.indexOf(b2));
		
		ml.checkOut(b2, p1);
		testList = ml.getAvailableBooks();
		assertEquals(0,testList.size());

	}

	public void testGetUnavailableBooks() {
		setup();
		addItems();
				
		ArrayList<Book> testList = ml.getUnavailableBooks();
		assertEquals(0,testList.size());

		
		ml.checkOut(b1, p1);
		testList = ml.getUnavailableBooks();
		assertEquals(1,testList.size());
		assertEquals(0, testList.indexOf(b1));
		
		ml.checkOut(b2, p1);
		testList = ml.getUnavailableBooks();
		assertEquals(2,testList.size());
		assertEquals(0, testList.indexOf(b1));
		assertEquals(1, testList.indexOf(b2));

	}
	
	public void testToString() {
		setup();
		addItems();
		assertEquals("Test: 2 books; 2 people.", ml.toString());
	}
		
}