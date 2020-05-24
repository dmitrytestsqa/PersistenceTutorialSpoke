package org.persistence.tutorial

import org.spockframework.compiler.model.ExpectBlock

import spock.lang.Specification

class MyMovieSpec extends Specification{
	
//	public void testMovie() {
//		Movie m1 = new Movie("Matrix", "Action");
//		assertEquals("Matrix", m1.getTitle());
//		assertEquals("Action", m1.getGenre());
//		assertEquals("unknown director", m1.getDirector());
//		}
	
	def "Test of MyMovie constructor with initialized title and genre"() {
		given: "Movie with title and genre"
			def m1 = new Movie("Matrix", "Action")
		expect: "Movie has its title Matrix and genre Action"
			m1.getTitle() == "Matrix"
			m1.getGenre() == "Action"
	}
		
//	public void testGetTitle() {
//		Movie m1 = new Movie("Goodfellas", "Crime");
//		assertEquals("Goodfellas", m1.getTitle());
//	}
	
	def "Test of the movie's title"() {
		given: "Movie with its title and genre"
			def m1 = new Movie("Goodfellas", "Crime")
		expect: "Movie title should be the Goodfellas"
			m1.getTitle() == "Goodfellas"
	}
	
//	public void testGetGenre() {
//		Movie m1 = new Movie("Joker", "Drama");
//		assertEquals("Drama", m1.getGenre());
//	}
	
	def "Test of the movie's genre"() {
		given: "Movie with title and genre"
			def m1 = new Movie("Joker", "Drama")
		expect: "Movie's genre should be the Drama"
			m1.getGenre() == "Drama"
	}
	
//	public void testGetDirector() {
//		Movie m1 = new Movie("Joker", "Drama");
//		assertEquals("unknown director", m1.getDirector());
//	}
	
	def "Test of the movie's director"() {
		given: "Movie with its default value of the Director"
			def m1 = new Movie("Joker", "Drama")
		expect: "Movie's genre by default should be unknown director"
			m1.getDirector() == "unknown director"
	}
	
//	public void testSetTitle() {
//		Movie m1 = new Movie("Matrix", "Action");
//		m1.setTitle("Interstellar");
//		assertEquals("Interstellar", m1.getTitle());
//	}
	
	def "Set a title to the movie"() {
		given: "Movie with a title and a genre"
			def m1 = new Movie("Matrix", "Action")
		when: "I set new title to the movie"
			m1.setTitle("Interstellar")
		then: "Movie has the new title"
			m1.getTitle() == "Interstellar"
	}
	
//	public void testSetGenre() {
//		Movie m1 = new Movie("Goodfellas", "Crime");
//		m1.setGenre("Drama");
//		assertEquals("Drama", m1.getGenre());
//	}
	
	def "Set a genre to the movie"() {
		given: "Movie with a title and a genre"
			def m1 = new Movie("Goodfellas", "Crime")
		when: "Set a new genre to the movie"
			m1.setGenre("Crime")
		then: "Movie has new genre"
			m1.getGenre() == "Crime"
	}
	
//	public void testSetDirector() {
//		Movie m1 = new Movie("Goodfellas", "Crime");
//		m1.setDirector("Martin Scorsese");
//		assertEquals("Martin Scorsese", m1.getDirector());
//	}
	
	def "Set a director to the movie"() {
		given: "Movie with a title and a genre"
			def m1 = new Movie("Goodfellas", "Crime")
		when: "Set a director to the movie"
			m1.setDirector("Martin Scorsese")
		then: "Movie has a new director"
			m1.getDirector() == "Martin Scorsese"
		}
		
//	public void testSetPerson() {
//		Movie m1 = new Movie("Joker", "Drama");
//		Person p1 = new Person();
//		p1.setName("Daria");
//		m1.setPerson(p1);
//			
//		assertTrue(m1.getPerson() instanceof Person);
//		assertEquals("Daria", m1.getPerson().getName());
//		}
		
	def "Set a person to the movie"(){
		given: "Movie with title, genre and a person"
			def m1 = new Movie("Joker", "Drama")
			def p1 = new Person()
		when: "I set name to the person"
			p1.setName("Daria")
		and: "I set person with name Daria to the movie"
			m1.setPerson(p1)
		then: "Person with name Daria is person type"
			m1.getPerson() instanceof Person
		and: "Person with name Daria has the movie"
			m1.getPerson().getName() == "Daria"
		}
		
//	public void testGetPerson() {
//		Movie m1 = new Movie("Matrix", "Action");
//		Person p1 = new Person();
//		m1.setPerson(p1);
//			
//		assertTrue(m1.getPerson() instanceof Person);
//		assertEquals("unknown name", m1.getPerson().getName());
//		}
		
	def "Get person from the movie"() {
		given: "Movie with title, genre and person"
			def m1 = new Movie("Matrix", "Action")
			def p1 = new Person()
		when: "I set person to the movie"
			m1.setPerson(p1)
		then: "Person has type Person"
			m1.getPerson() instanceof Person
		and: "Person has unknown name"
			m1.getPerson().getName() == "unknown name"
	}
	
//	public void testToString() {
//		Movie m1 = new Movie("Goodfellas", "Crime");
//		assertEquals("Goodfellas directed by unknown director; The genre of this movie is Crime; Available.", m1.toString());
//		
//		Person p1 = new Person();
//		m1.setPerson(p1);
//		p1.setName("Arnold");
//		assertEquals("Goodfellas directed by unknown director; The genre of this movie is Crime; Checked out to Arnold.", m1.toString());
//	}
	
	def "Test toString() method"() {
		given: "Movie with title and genre"
			def m1 = new Movie("Goodfellas", "Crime")
		expect: "Method toString() returns Goodfellas directed by unknown director; The genre of this movie is Crime; Available"
			m1.toString() == "Goodfellas directed by unknown director; The genre of this movie is Crime; Available."
		when: "I add person to the movie and set name of the person Arnold"
			Person p1 = new Person()
			m1.setPerson(p1)
			p1.setName("Arnold")
		then: "Method toString() returns Goodfellas directed by unknown director; The genre of this movie is Crime; Checked out to Arnold."
			m1.toString() == "Goodfellas directed by unknown director; The genre of this movie is Crime; Checked out to Arnold."
	}
	
}
