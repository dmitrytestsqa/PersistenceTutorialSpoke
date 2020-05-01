package org.persistence.tutorial

import spock.lang.*

/**
 * @Author: QA Mentor   //Always important to author the class.  This allows people later to be able to find the person
 *                        who created the class for questions.
 */

class SpockDocumentationCompanion extends Specification{

/*
 * These are fields that are not shared between feature methods.  This means that every
 * feature method can use these field names, but any changes to the fields within a
 * feature method will be thrown away and not available to the next feature method that
 * uses the same field name.
 */
	def b1
	def b2
	def p1
	def p2
	def ml

/*
 * This is a field that is shared among the feature methods.  This means that any changes to this field
 * "stick" after the feature method is complete.  The changes to this field are available to all
 * feature methods that use this field after it is changed.
 *
 * You should use fields when you want to set a field up and then never change it.  That way you only have
 * to set it up once instead of over and over.
 *
 * Shared fields must use @Shared annotation.
 *
 */
	@Shared mml

////Fixture Method that is run once at the top of the suite before any of the feature methods are run.
//	def setupSpec() {
//		mml = MyMovieUtilities.getMyMovieLibraryFromXMLFile("cnxmovielibrary.xml")
//	}


//Implicit Helper Method that is run before each feature method (multiple times) so if you change mml in one test it would not affect the following tests.
	def setup() {
		mml = MyMovieUtilities.getMyMovieLibraryFromXMLFile("cnxmovielibrary.xml")
	}


//First Feature Method using the Given When Then type.
	def "Set a person with a book"(){  // Notice that the feature method naming convention is like documentation.
		given: "A book and a person"   // This "given" is an example of a setup block.
			b2 = new Book ("Running with Power")
			p2 = new Person()
			p2.setName("Usain Bolt")

		when: "A book is checked out to a person"
			b2.setPerson(p2)  // Example of an interaction within the when clause.
			def WhoHasTheBook = b2.getPerson().getName()  // Note that you can save values to fields for later use

		then: "The person has the book checked out"
			WhoHasTheBook == "Usain Bolt"
	}

//Second Feature Method.  You can use when/then over and over.  When this; then that, when this; then that.
	def "Adding and Removing Books to a New Empty Library"() {
		given: "An empty library and two books waiting to be added to the library"
			setupBooksAndPeople()  // See the helper method at the bottom of the class

		when: "The two books are added to the library"
			ml.addBook(b1)
			ml.addBook(b2)

		then: "The number of books in the library is 2"
			ml.getBooks().size() == 2

		when: "A book is removed from the library"
			ml.removeBook(b1)

		then: "The number of books in the library is 1"
			ml.getBooks().size() == 1
			ml.getBooks().indexOf(b2) == 0

	}

	//Example of using 'Expect' Block only.
	def "Test Book Constructor"() {
		given: "A new book"
			def b1 = new Book("Great Expectations")

		expect:"the title is set up correctly and the author has default value"
			b1.getAuthor() == "unknown author"
			b1.getTitle() == "Great Expectations"
	   }

  //Example of using When/Then pair with Where block
	def "Test searching with one text string using the test harness"(){
		when: "to perform searches in the library with one string"
			def foundResults = MyMovieTestHarness.searchLibrary(mml, searchString).size()

		then: "number of found results matches expected number of results"
			foundResults == expectedResults

		where:
		searchString				| expectedResults
		"drama"							| 60
		"Quentin Tarantino"	| 5
		"bromance"					| 0
	}

// ------------------------ "Try This" Section ------------------------ //

/*Try this: 1. Comment out the setup() from above.  Run the test.  The "Test searching with two text strings using the test harness"() will fail.
*               This is due to mml being changed in "Change the mml to something different".
*
*            2. Now comment out setupSpec() and just run setup().  You will see that all the test pass because the setup() is run before each
*		       and every feature method.  This means that the changes we made to mml were reset by the setup() because
*		       the full library got read in again.
*/

	def "Change the mml to something different"() {
		expect:"The existing movie library has many movies in it"
			mml.getMovies().size() > 1

		when: "New library is set up with 1 movie in it"
			def mml = new MyMovieLibrary()
			def m1 = new Movie("The Shawshank Redemption","drama")
			mml.addMovie(m1)

		and: "I replace what was in default library with a new library"
			MyMovieUtilities.saveMyMovieLibraryToXMLFile("testmylibrarytwo.xml", mml)
			mml = MyMovieUtilities.getMyMovieLibraryFromXMLFile("testmylibrarytwo.xml")

		then: "There is one movie in the library insted of the many ones that were there"
			mml.getMovies().size() == 1
	}

//Example of using 'Expect' Block when using a 'where' block after.
/*Try this:  Run the test suit.  Notice the @Unroll provides clarity in how the test is using the where block.
 *	         Comment out "@Unroll" and see the difference in the JUnit reporting.
 *
 *Note: in this test we use searchLibrary() method in 'Expect' block. This method is an action method.
 *And so it is better to put it into 'When' block and use 'Then' for verification. In this example
 *we didn't follow the best practice just to show you how 'Expect' and 'Where' blocks can be combined in a Specification
 */

	@Unroll    //Use the @Unroll if you would like to see each part of the table tested.
	def "Test searching with two text strings using the test harness"(){
		expect: "perform searches in the library with two strings"
			MyMovieTestHarness.searchLibrary(mml, searchString1, searchString2).size() == results

		where: "This is using a data table to test various variables, in this case searchString and attach a result to that string"
		searchString1				| searchString2	| results
		"drama"							| "Taxi Driver"	| 1
		"Quentin Tarantino" | "thriller"		| 2
		"Quentin Tarantino" | "romance"			| 0
		"Quentin Tarantino" | "bromance"		| 0
		"Not a Director" 		| "romance"			| 0
		"Not a Director" 		| "bromance"		| 0

	}

	//Helper Method
	def setupBooksAndPeople() {
		b1 = new Book("Book1")
		b2 = new Book("Book2")
		p1 = new Person()
		p1.setName("Fred")
		p2 = new Person()
		p2.setName("Sue")
		ml = new MyLibrary("Test")
	}
}
