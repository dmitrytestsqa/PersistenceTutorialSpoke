package org.persistence.tutorial

import spock.lang.Specification

class MyMovieLibrarySpec extends Specification{
	
	def mov1
	def mov2
	def per1
	def per2
	def movLib
	
	def setupMoviesPeopleAndLibrary() {
		movLib = new MyMovieLibrary()
		mov1 = new Movie("Forest Gump", "Drama")
		mov2 = new Movie("Inception", "Action")
		per1 = new Person()
		per1.setName("Anna")
		per2 = new Person()
		per2.setName("Den")
	}
		
	def addItems() {
		movLib.addPerson(per1)
		movLib.addPerson(per2)
		movLib.addMovie(mov1)
		movLib.addMovie(mov2)
	}
	
//	public void testMyMovieLibrary() {
//		setup();
//		assertTrue(movLib.getMovies() instanceof ArrayList);
//		assertTrue(movLib.getPeople() instanceof ArrayList);
//		
//		assertEquals(0, movLib.getMovies().size());
//		assertEquals(0, movLib.getPeople().size());
//	}
	
	def "Test MyMovieLibrary in its default state"() {
		given: "My movie library without movies and people"
			setupMoviesPeopleAndLibrary()
		expect: "MyMovieLibrary has lists of movies and people, and their type is ArrayList"
			movLib.getMovies() instanceof ArrayList
			movLib.getPeople() instanceof ArrayList
		and: "Movie library hasn't movies and persons by default"
			movLib.getMovies().size() == 0
			movLib.getPeople().size() == 0
	}
	
//	public void testGetTitle() {
//		MyMovieLibrary myMovieLib = new MyMovieLibrary();
//		Movie movie = new Movie("Whiplash", "Drama");
//		assertEquals(0, myMovieLib.getMovies().size());
//		myMovieLib.addMovie(movie);
//		assertEquals(1, myMovieLib.getMovies().size());
//		assertEquals("Whiplash", myMovieLib.getTitle(movie));
//	}
	
	def "Get title of the movie from the MyMovieLibrary"(){
		given: "Setup movie library and movie with title and genre"
			def myMovieLib = new MyMovieLibrary()
			def movie = new Movie("Whiplash", "Drama")
		expect: "Movie library hasn't any movies"
			myMovieLib.getMovies().size() == 0
		when: "I add movie to the movie library"
			myMovieLib.addMovie(movie)
		then: "My movie library has one movie and its title is Whiplash"
			myMovieLib.getMovies().size() == 1
			myMovieLib.getTitle(movie) == "Whiplash"
	}
	
//	public void testGetGenre() {
//		MyMovieLibrary myMovieLib = new MyMovieLibrary();
//		Movie movie = new Movie("The Lion King", "Animation");
//		assertEquals(0, myMovieLib.getMovies().size());
//		myMovieLib.addMovie(movie);
//		assertEquals(1, myMovieLib.getMovies().size());
//		assertEquals("Animation", myMovieLib.getGenre(movie));
//		myMovieLib.getMovies().get(0).setGenre("Adventure");
//		assertEquals("Adventure", myMovieLib.getGenre(movie));
//	}
	
	def "Get genre of the movie from the my movie library"() {
		given: "My movie library and movie with title and genre"
			def myMovieLib = new MyMovieLibrary()
			def movie = new Movie("The Lion King", "Animation")
		expect: "My movie library hasn't movies by default"
			myMovieLib.getMovies().size() == 0
		when: "I add movie to the movie library"
			myMovieLib.addMovie(movie)
		then: "My movie library has one movie and movie's genre is Animation"
			myMovieLib.getMovies().size() == 1
			myMovieLib.getGenre(movie) == "Animation"
		when: "I set movie genre is Adventure"
			myMovieLib.getMovies().get(0).setGenre("Adventure")
		then: "Movie in my movie library has genre Adventure"
			myMovieLib.getGenre(movie) == "Adventure"
	}
	
//	public void testGetDirector() {
//		MyMovieLibrary myMovieLib = new MyMovieLibrary();
//		Movie movie = new Movie("The Prestige", "Mystery");
//		assertEquals(0, myMovieLib.getMovies().size());
//		myMovieLib.addMovie(movie);
//		assertEquals(1, myMovieLib.getMovies().size());
//		assertEquals("unknown director", myMovieLib.getDirector(movie));
//		myMovieLib.getMovies().get(0).setDirector("Christopher Nolan");
//		assertEquals("Christopher Nolan", myMovieLib.getDirector(movie));
//	}
	
	def "Get director of the movie from the my movie library"() {
		given: "My movie library and movie with title and genre"
			def myMovieLib = new MyMovieLibrary()
			def movie = new Movie("The Prestige", "Mystery")
		expect: "My movie library hasn't movies by default"
			myMovieLib.getMovies().size() == 0
		when: "I add movie to the library"
			myMovieLib.addMovie(movie)
		then: "My movie library has one movie and movie's director is unknown director"
			myMovieLib.getMovies().size() == 1
			myMovieLib.getDirector(movie) == "unknown director"
		when: "I set movie's director is Christopher Nolan in my movie library"
			myMovieLib.getMovies().get(0).setDirector("Christopher Nolan")
		then: "Director of movie in my movie library is Christopher Nolan"
			myMovieLib.getDirector(movie) == "Christopher Nolan"
	}
	
//	public void testAddMovie() {
//		MyMovieLibrary movLib = new MyMovieLibrary();
//		Movie m1 = new Movie("Cidade de Deus", "Crime");
//		movLib.addMovie(m1);
//		assertEquals("Cidade de Deus", movLib.getMovies().get(0).getTitle());
//	}
	
	def "Add movie to my movie library"() {
		given: "My movie library and movie with title and genre"
			def myMovieLib = new MyMovieLibrary()
			def movie = new Movie("Cidade de Deus", "Crime")
		when: "I add movie to my movie library"
			myMovieLib.addMovie(movie)
		then: "Title of the movie in my movie library is Cidade de Deus"
			myMovieLib.getMovies().get(0).getTitle() == "Cidade de Deus"
	}
	
//	public void testRemoveMovies(){
//		setup();
//		addItems();
//		movLib.removeMovie(mov1);
//		assertEquals(1, movLib.getMovies().size());
//		assertEquals(0, movLib.getMovies().indexOf(mov2));
//		
//		movLib.removeMovie(mov2);
//		assertEquals(0, movLib.getMovies().size());
//		
//	}
	
	def "Remove movies from my movie library"() {
		given: "My movie library without movies and people"
			setupMoviesPeopleAndLibrary()
		when: "I add two movies and two persons to my movie libtrary"
			addItems()
		and: "Delete first movie from my movie library"
			movLib.removeMovie(mov1)
		then: "My movie library has one movie and index of the second movie is 0"
			movLib.getMovies().size() == 1
			movLib.getMovies().indexOf(mov2) == 0
		when: "I delete the second movie from my movie library"
			movLib.removeMovie(mov2)
		then: "Quantity of movies should be 0 in my movie library"
			movLib.getMovies().size() == 0
	}
	
//	public void testGetMovie() {
//		MyMovieLibrary movLib = new MyMovieLibrary();
//		Movie m1 = new Movie("Saving Private Ryan", "War");
//		movLib.addMovie(m1);
//		assertEquals("Saving Private Ryan", movLib.getMovies().get(0).getTitle());
//		assertEquals("War", movLib.getMovies().get(0).getGenre());
//	}
	
	def "Get movie from my movie library"() {
		given: "My movie library and movie with title and genre"
			def mov = new MyMovieLibrary()
			def m = new Movie("Saving Private Ryan", "War")
		when: "I add movie to my movie library"
			mov.addMovie(m)
		then: "Movie in my movie library has title Saving Private Ryan and its genre is War"
			mov.getMovies().get(0).getTitle() == "Saving Private Ryan"
			mov.getMovies().get(0).getGenre() == "War"
	}
	
//	public void testAddPerson() {
//		setup();
//		addItems();
//		Person p3 = new Person();
//		p3.setName("Jack");
//		movLib.addPerson(p3);
//		assertEquals(3, movLib.getPeople().size());
//		assertEquals("Jack", movLib.getPeople().get(2).getName());
//	}
	
	def "Add person to my movie library"() {
		given: "My movie library without movies and people"
			setupMoviesPeopleAndLibrary()
		when: "I add two movies and three persons to my movie library"
			addItems()
			def p3 = new Person()
			p3.setName("Jack")
			movLib.addPerson(p3)
		then: "My movie library has three persons and name of the third person is Jack"
			movLib.getPeople().size() == 3
			movLib.getPeople().get(2).getName() == "Jack"
	}
	
//	public void testGetPeople() {
//		setup();
//		addItems();
//		Person p4 = new Person();
//		p4.setName("Bob");
//		movLib.addPerson(p4);
//		assertEquals(3, movLib.getPeople().size());
//		assertEquals("Bob", movLib.getPeople().get(2).getName());
//	}
	
	def "Get people from my movie library"() {
		given: "My movie library without movies and people"
			setupMoviesPeopleAndLibrary()
		when: "I add two movies and three persons to the my movie library"
			addItems()
			def p4 = new Person()
			p4.setName("Bob")
			movLib.addPerson(p4)
		then: "Quantity of the persons in my movie library is 3 and name of the third person is Bob"
			movLib.getPeople().size() == 3
			movLib.getPeople().get(2).getName() == "Bob"
	}
	
//	public void testCheckOutMovie() {
//		setup();
//		addItems();
//	
//		assertTrue("Book did not check out correctly", movLib.checkOut(mov1, pers1));
//		assertEquals("Anna", movLib.getMovies().get(0).getPerson().getName());
//		
//		assertTrue("Book did not check out correctly", movLib.checkOut(mov2, pers2));
//		assertEquals("Den", movLib.getPeople().get(1).getName());
//		assertEquals("Inception", movLib.getTitle(mov2));
//		assertEquals("Action", movLib.getGenre(mov2));
//		assertEquals("unknown director", movLib.getDirector(mov2));
//		mov2.setDirector("Christopher Nolan");
//		assertEquals("Christopher Nolan", movLib.getDirector(mov2));
//		
//	}
	
	def "Check out movie from my movie library"() {
		given: "My movie library with two persons and two movies"
			setupMoviesPeopleAndLibrary()
			addItems()
		when: "The first movie is checked out by the first person with name Anna"
			movLib.checkOut(mov1, per1)
			movLib.getMovies().get(0).getPerson().getName() == "Anna"
		then: "The second movie is checked out by the second person with name Den and movie's title is Inception"
			movLib.checkOut(mov2, per2)
			movLib.getPeople().get(1).getName() == "Den"
			movLib.getTitle(mov2) == "Inception"
		when: "I set director to the second movie"
			mov2.setDirector("Christopher Nolan")
		then: "Director of the second movie is Christopher Nolan"
			 movLib.getDirector(mov2) == "Christopher Nolan"
	}
	
//	public void testMovieShouldNotBeCheckedOutTwice(){
//		setup();
//		addItems();
//		movLib.checkOut(mov1, pers1);
//
//		assertFalse("Book was alread checked out", movLib.checkOut(mov1, pers1));
//	}
	
	def "Movie shouldn't be checked out twice from my movie library"() {
		given: "My movie library with two persons and two movies"
			setupMoviesPeopleAndLibrary()
			addItems()
		when: "Person checked out first movie from my movie library"
			movLib.checkOut(mov1, per1)
		then: "Person can't checked out the same movie twice"
			!movLib.checkOut(mov1, per1)
	}
	
//	public void testCheckInMovie(){
//		setup();
//		addItems();
//		movLib.checkOut(mov1, pers1);
//		
//		assertTrue("Book check in failed", movLib.checkIn(mov1));
//	}
	
	def "Movie could be checked in to my movie library"() {
		given: "My movie library with two persons and two movies"
			setupMoviesPeopleAndLibrary()
			addItems()
		when: "Person checked out first movie from my movie library"
			movLib.checkOut(mov1, per1)
		then: "Check in movie to my library"
			movLib.checkIn(mov1)
	}
	
//	public void testMovieShouldNotBeCheckedInTwice(){
//		setup();
//		addItems();
//		movLib.checkOut(mov1, pers1);
//		movLib.checkIn(mov1);
//		assertFalse("Book was already checked in", movLib.checkIn(mov1));
//	}
	
	def "Movie shoudn't be checked in to my movie library twice"() {
		given: "My movie library with two persons and two movies"
			setupMoviesPeopleAndLibrary()
			addItems()
		when: "Person checked out the first movie from my movie library and then cheked in it to movie library"
			movLib.checkOut(mov1, per1)
			movLib.checkIn(mov1)
		then: "Movie shouldn't be check in to my library twice"
			!movLib.checkIn(mov1)
	}
	
//	public void testMovieShouldNotBeChekedInIfItWasNeverCheckedOut(){
//		setup();
//		addItems();
//		
//		assertFalse("Movie was never checked out", movLib.checkIn(mov2));
//	}
	
	def "Movie should not be cheked in, if it has never been checked out"() {
		given: "My movie library with two persons and two movies"
			setupMoviesPeopleAndLibrary()
			addItems()
		expect: "Movie was never checked out and can't be cheked in"
			!movLib.checkIn(mov2)
	}
	
//	public void testPersonShouldNotExceedMaximumMoviesLimit(){
//		setup();
//		addItems();
//		pers1.setMaximumMovies(1);
//		movLib.checkOut(mov1, pers1);
//		
//		assertFalse("Second movie should not have checked out", movLib.checkOut(mov2, pers1));
//	}
	
	def "Person should not exceed maximum movies limit"() {
		given: "My movie library with two persons and two movies"
			setupMoviesPeopleAndLibrary()
			addItems()
		when: "I set maximum movies limit as 1 for the first person and this person checked out the first movie"
			per1.setMaximumMovies(1)
			movLib.checkOut(mov1, per1)
		then: "Second movie should not have checked out"
			!movLib.checkOut(mov2, per1)
	}
	
//	public void testGetMoviesForPerson() {
//		setup();
//		addItems();
//		assertEquals(0, movLib.getMoviesForPerson(pers1).size());
//		
//		movLib.checkOut(mov1, pers1);
//		ArrayList<Movie> testMovies = movLib.getMoviesForPerson(pers1);
//		assertEquals(1, testMovies.size());
//		assertEquals(0, testMovies.indexOf(mov1));
//		
//		movLib.checkOut(mov1, pers1);
//		testMovies = movLib.getMoviesForPerson(pers1);
//	}
	
	def "Get quantity of movies that person has"() {
		given: "My movie library with two persons and two movies"
			setupMoviesPeopleAndLibrary()
			addItems()
		expect: "Person hasn't movie while doesn't checked out it"
			movLib.getMoviesForPerson(per1).size() == 0
		when: "Person checked out 1 movie"
			movLib.checkOut(mov1, per1)
			def testMovie = movLib.getMoviesForPerson(per1).size()
		then: "Person has 1 movie"
			testMovie == 1
		when: "Movie checked in to the movie library"
			movLib.checkIn(mov1)
		then: "Person hasn't any movies"
			movLib.getMoviesForPerson(per1).size() == 0
	}
	
//	public void testGetAvailableMovies() {
//		setup();
//		addItems();
//				
//		ArrayList<Movie> testList = movLib.getAvailableMovies();
//		assertEquals(2,testList.size());
//		assertEquals(0, testList.indexOf(mov1));
//		assertEquals(1, testList.indexOf(mov2));
//		
//		movLib.checkOut(mov1, pers1);
//		testList = movLib.getAvailableMovies();
//		assertEquals(1, testList.size());
//		
//		assertEquals("Anna", movLib.getMovies().get(0).getPerson().getName());
//		
//		movLib.checkOut(mov2, pers1);
//		testList = movLib.getAvailableMovies();
//		assertEquals(1, testList.size());
//	}
	
	def "Get available movies from my movie library"() {
		given: "My movie library with two persons and two movies"
			setupMoviesPeopleAndLibrary()
			addItems()
		expect: "Two movies should be available by default"
			movLib.getAvailableMovies().size() == 2
		when: "Person checked out one movie"
			movLib.checkOut(mov1, per1)
		then: "One movie shoud be available and person with name Anna had checked out it"
			movLib.getAvailableMovies().size() == 1
			movLib.getMovies().get(0).getPerson().getName() == "Anna"
		when: "Person with name Anna try to checked out second movie"
			movLib.checkOut(mov2, per1)
		then: "Person with name Anna may has only one book, because only one book available for one person"
			movLib.getAvailableMovies().size() == 1
	}
	
//	public void testGetUnavailableMovies() {
//		setup();
//		addItems();
//		
//		ArrayList<Movie> testList = movLib.getUnavailableMovies();
//		assertEquals(0,testList.size());
//		
//		movLib.checkOut(mov1, pers1);
//		testList = movLib.getUnavailableMovies();
//		assertEquals(1,testList.size());
//		assertEquals(0, testList.indexOf(mov1));
//		
//		movLib.checkOut(mov2, pers1);
//		testList = movLib.getUnavailableMovies();
//		assertEquals(1,testList.size());
//		assertEquals(0, testList.indexOf(mov1));
//	}
	
	def "Get unavailable movies from my movie library"() {
		given: "My movie library with two persons and two movies"
			setupMoviesPeopleAndLibrary()
			addItems()
		expect: "Zero movies should be unavailable by default"
			movLib.getUnavailableMovies().size() == 0
		when: "Person checked out the first movie"
			movLib.checkOut(mov1, per1)
		then: "One movie should be unavailable"
			movLib.getUnavailableMovies().size() == 1
		when: "Person try to check out the second book"
			movLib.checkOut(mov2, per1)
		then: "Quantity of available movies is still one, because person has not ability to checkout more than one book"
			movLib.getUnavailableMovies().size() == 1
	}
	
//	public void testGetPersonWithMovie() {
//		setup();
//		addItems();
//		movLib.checkOut(mov1, pers1);
//		assertEquals("Anna", movLib.getPersonWithMovie(mov1).get(0).getName());
//		movLib.checkOut(mov2, pers2);
//		assertEquals("Den", movLib.getPersonWithMovie(mov2).get(1).getName());
//			
//	}
	
	def "Get person who has the movie"() {
		given: "My movie library with two persons and two movies"
			setupMoviesPeopleAndLibrary()
			addItems()
		when: "The first person checked out the movie"
			movLib.checkOut(mov1, per1)
		then: "Name of person who checked out the movie is Anna"
			movLib.getPersonWithMovie(mov1).get(0).getName() == "Anna"
		when: "The second person checked out the movie"
			movLib.checkOut(mov2, per2)
		then: "Name of the second person who checked out the movie is Den"
			movLib.getPersonWithMovie(mov2).get(1).getName() == "Den"
	}
	
//	public void testToString() {
//		setup();
//		addItems();
//		assertEquals("My Movie Library: 2 movies; 2 people.", movLib.toString());
//	}
	
	def "Test toString() method"() {
		given: "My movie library with two persons and two movies"
			setupMoviesPeopleAndLibrary()
			addItems()
		expect: "My Movie Library: 2 movies; 2 people."
			movLib.toString() == "My Movie Library: 2 movies; 2 people."
	}
	
//	public void testGetMoviesByGenre() {
//		setup();
//		addItems();
//		assertEquals(1, movLib.getMoviesByGenre("Drama").size());
//		assertEquals(1, movLib.getMoviesByGenre("Action").size());
//		Movie mov3 = new Movie("The Matrix", "Action");
//		Movie mov4 = new Movie("The Green Mile", "Drama");
//		movLib.addMovie(mov3);
//		movLib.addMovie(mov4);
//		assertEquals(2, movLib.getMoviesByGenre("Drama").size());
//		assertEquals(2, movLib.getMoviesByGenre("Action").size());
//	}
	
	def "Get movies from movie library by its genre"(){
	given: "My movie library with two persons and two movies"
		setupMoviesPeopleAndLibrary()
		addItems()
	expect: "Movie library has one movie with genre Drama and one movie with genre Action"
		movLib.getMoviesByGenre("Drama").size() == 1
		movLib.getMoviesByGenre("Action").size() == 1
	when: "User adds two new movies to the movie library with the same genres like existing movies in the movie library"
		def mov3 = new Movie("The Matrix", "Action")
		def mov4 = new Movie("The Green Mile", "Drama")
		movLib.addMovie(mov3)
		movLib.addMovie(mov4)
	then: "Movie library has two movies with genre Drama and two movies with genre Action"
		movLib.getMoviesByGenre("Drama").size() == 2
		movLib.getMoviesByGenre("Action").size() == 2
	}
	
//	public void testGetMoviesByDirector() {
//		setup();
//		addItems();
//		mov1.setDirector("Robert Zemeckis");
//		mov2.setDirector("Christopher Nolan");
//		assertEquals(1, movLib.getMoviesByDirector("Robert Zemeckis").size());
//		assertEquals(1, movLib.getMoviesByDirector("Christopher Nolan").size());
//		Movie mov3 = new Movie("Contact", "Mystery");
//		mov3.setDirector("Robert Zemeckis");
//		Movie mov4 = new Movie("Interstellar", "Adventure");
//		mov4.setDirector("Christopher Nolan");
//		movLib.addMovie(mov3);
//		movLib.addMovie(mov4);
//		assertEquals(2, movLib.getMoviesByDirector("Robert Zemeckis").size());
//		assertEquals(2, movLib.getMoviesByDirector("Christopher Nolan").size());
//	}
	
	def "Get movies from movie library by its director"() {
		given: "My movie library with two persons and two movies"
			setupMoviesPeopleAndLibrary()
			addItems()
		when: "User sets the director for each movie in the library"
			mov1.setDirector("Robert Zemeckis")
			mov2.setDirector("Christopher Nolan")
		then: "Movie library has one movie with Robert Zemeckis as director and one movie with Christopher Nolan as director"
			movLib.getMoviesByDirector("Robert Zemeckis").size() == 1
			movLib.getMoviesByDirector("Christopher Nolan").size() == 1
		when: "User adds two new movies to the movie library with its directors Robert Zemeckis and Christopher Nolan"
			def mov3 = new Movie("Contact", "Mystery")
			mov3.setDirector("Robert Zemeckis")
			def mov4 = new Movie("Interstellar", "Adventure")
			mov4.setDirector("Christopher Nolan")
			movLib.addMovie(mov3)
			movLib.addMovie(mov4)
		then: "Movie library has two movies with Robert Zemeckis as director and two movies with Christopher Nolan as director"
			movLib.getMoviesByDirector("Robert Zemeckis").size() == 2
			movLib.getMoviesByDirector("Christopher Nolan").size() == 2
	}
	
}