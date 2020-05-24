package org.persistence.tutorial

import spock.lang.Specification

class MyMovieUtilitiesSpec extends Specification{
		
	def setupMoviesPeopleAndLibrary() {
		def mov1
		def mov2
		def per1
		def per2
		def movLib
		mov1 = new Movie("Forest Gump", "Drama")
		mov2 = new Movie("Inception", "Action")
		per1 = new Person()
		per1.setName("Sam")
		per2 = new Person()
		per2.setName("Dana")
		movLib = new MyMovieLibrary()
		movLib.addPerson(per1)
		movLib.addPerson(per2)
		movLib.addMovie(mov1)
		movLib.addMovie(mov2)
		return movLib
	}
	
	def "Save string to file"() {
		given: "String testString which will written to the spockfile.txt"
			def testString = "Spock Framework\n"
			def spockfile = new File("spockfile.txt")
		when: "I save string to the file"
			def fileWithSavedString = MyMovieUtilities.saveStringToFile("spockfile.txt", testString)
		then: "spockfile.txt is exists and has the same value as testString"
			spockfile.exists()
			fileWithSavedString == true
			def stringFromFile = MyMovieUtilities.getStringFromFile("spockfile.txt")
			stringFromFile == testString
		cleanup:
		spockfile.delete()
	}
	
	def "Get string from file"() {
		given: "File with string value"
			def testString = "Hello World!!!\n"
			def spockfile = new File("testString1.txt")
			MyMovieUtilities.saveStringToFile("spockfile.txt", testString)
		when: "I read string from the spockfile.txt"
			def savedString = MyMovieUtilities.getStringFromFile("spockfile.txt")
		then: "Saved string should has Hello Word!!! value"
			savedString == testString
		cleanup:
			spockfile.delete()
	}
	
	def "Convert to XML and get from XML"() {
		given: "Movie library with two movies and two persons"
			def movLib = setupMoviesPeopleAndLibrary()
		when: "I convert movie library to the XML"
			def movLibXML = MyMovieUtilities.convertToXML(movLib)
		and: "I convert my movie library from XML to object"
			def movLibObj = MyMovieUtilities.convertFromXML(movLibXML)
		then: "My movie library has two movies and two persons"
			movLibObj.getMovies().size() == 2
			movLibObj.getPeople().size() == 2
	}
	
	def "Save movie library to XML file and get my movie from XML file"() {
		given: "Movie library with two movies and two persons"
			def myMovieLibrary = setupMoviesPeopleAndLibrary()
		when: "I save movie library to XML file"
			def testFile = new File("library.xml")
			MyMovieUtilities.saveMyMovieLibraryToXMLFile("library.xml", myMovieLibrary)
		and: "My movie library convert from XML file to object"
			def movLib1 = MyMovieUtilities.getMyMovieLibraryFromXMLFile("library.xml")
		then: "Movie library has two movies and two persons"
			movLib1.getMovies().size() == 2
			movLib1.getPeople().size() == 2
			movLib1.getPeople().get(0).getName() == "Sam"
			movLib1.getMovies().get(1).getTitle() == "Inception"
		cleanup:
			testFile.delete()
	}
			
}