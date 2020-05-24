package org.persistence.tutorial

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class MyMovieTestHarnessSpec extends Specification{
	
	def setup() {
		library = MyMovieUtilities.getMyMovieLibraryFromXMLFile("cnxmovielibrary.xml")
	}
	
	@Shared library
		
	@Unroll
	def "Search movies from library using only one string"() {
		expect: "Perform search using only one string"
			MyMovieTestHarness.searchLibrary(library, value).size() == result
		where: "Data table to test various variables"
			value                 | result
			"Alien"               | 1
			"City Lights"         | 1
			"Die Hard"            | 1
			"drama"               | 60
			"thriller"            | 46
			"comedy"              | 15
			"Christopher Nolan"   | 6
			"Stanley Kubrick"     | 8
			"Akira Kurosawa"      | 5
			}
	
	@Unroll
	def "Search movies from library using two strings"() {
		expect: "Perform search using two strings"
			MyMovieTestHarness.searchLibrary(library, searchString1, searchString2).size() == result
		where: "Data table to test various variables"
			searchString1              | searchString2      | result
			"drama"                    | "Frank Darabont"   | 2
			"biography"                | "Goodfellas"       | 1
			"Quentin Tarantino"        | "Django Unchained" | 1
			"Billy Wilder"             | "comedy"           | 2
			"WALL-E"                   | "Andrew Stanton"   | 1
			"Oldboy"                   | "thriller"         | 1
			"family"                   | "sci-fi"           | 0
			"Princess Mononoke"        | "The General"      | 0
			"Elia Kazan"               | "Ingmar Bergman"   | 0
			}
			
	@Unroll
	def "Negative search of movies from library using two strings"() {
		expect: "Perform search using two strings"
			MyMovieTestHarness.searchLibrary(library, searchString1, searchString2).size() == result
		where: "Data table to test various variables"
			searchString1              | searchString2      | result
			"Cats"                     | "Tom Hooper"       | 0
			"Pride & Prejudice"        | "Romance"          | 0
			"melodrama"                | "Dan Fogelman"     | 0
			"bromance"                 | "TV Show"          | 0
			"Frank Darabont"           | "Joe Wright"       | 0
			"Jonathan Nolan"           | "Westworld"        | 0
			"Paolo Sorrentino"         | "TV Series"        | 0
			}
	
}