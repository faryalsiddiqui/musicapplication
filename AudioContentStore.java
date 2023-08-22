  // Name: Faryal Siddiqui
  // Student ID: 501157845

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;





// Simulation of audio content in an online store
// The songs, podcasts, audiobooks listed here can be "downloaded" to your library

public class AudioContentStore
{

		private ArrayList<AudioContent> contents; 
		private Map<String, Integer> contentsMap;
		private Map<String, ArrayList<Integer>> artistsAuthors;
		private Map<String, ArrayList<Integer>> genres;
		String errMsg;

		public String getErrorMessage() {
			return errMsg;
		}
		
		public AudioContentStore()
		{
			contents = new ArrayList<AudioContent>();
			
		  // Create some songs audiobooks and podcasts and to store

			
		  	try {
				File store = new File("store.txt");
				Scanner scan = new Scanner(store);

				// Loop through file contents
				while (scan.hasNextLine()) {
					String type = scan.nextLine();
					
					// If the line represents a SONG type object
					while (type.equals("SONG")) {
						// Read in the SONG object attributes
						String id = scan.nextLine();
						String title = scan.nextLine();
						int year = scan.nextInt();
						int length = scan.nextInt();
						// Remove any newline characters from buffer
						scan.nextLine();
						String artist = scan.nextLine();
						String composer = scan.nextLine();
						String stringGenre = scan.nextLine();

						// Map genre string to corresponding Song.Genre enum value
						Song.Genre genre = Song.Genre.valueOf(stringGenre);
	
						int numLyrics = scan.nextInt();
						String lyrics = "";
						
						// Read in lyrics line by line
						for (int i = 0; i < numLyrics; i++) {
							lyrics += scan.nextLine() + "\n";
						}
						
						// Create new Song object and add it to contents list
						Song tempSong = new Song(title, year, id, type, lyrics, length, artist, composer, genre, lyrics);
						
						contents.add(tempSong);
						System.out.println("Loading SONG");
						
						// Check for next type of object in file
						type = scan.nextLine();
					}
					
					// If the line represents an AUDIOBOOK type object
					while (type.equals("AUDIOBOOK")) {
						// Read in the AUDIOBOOK object attributes
						String id = scan.nextLine();
						String title = scan.nextLine();
						int year = scan.nextInt();
						int length = scan.nextInt();
						// getting rid of /n
						scan.nextLine();
						String author = scan.nextLine();
						String narrator = scan.nextLine();

						int numChapters = scan.nextInt();
						 scan.next();

						 // Read in chapter titles
						ArrayList<String> titles = new ArrayList<String>();
						for (int i = 0; i < numChapters; i++) {
							titles.add(scan.nextLine());
						}
						
						int	chapLines;
						
						 // Read in chapter content line by line until end of file is reached
						ArrayList<String> chapters = new ArrayList<String>();
	
						while (scan.hasNextInt()) {
							chapLines = scan.nextInt();
	
							String chapter = "";
							for (int j = 0; j <= chapLines; j++) {
								chapter += scan.nextLine() + "\n";
							}
							
							chapters.add(chapter);
						}
	
						// Create new AudioBook object and add it to contents list
						AudioBook tempBook = new AudioBook(title, year, id, type, "", length, author, narrator, titles, chapters);

						System.out.println("Loading AUDIOBOOK");
						
						contents.add(tempBook);
						
						 // Check for next type of object in file
						type = scan.nextLine();
					}
				}
				scan.close();

			} 
			catch (FileNotFoundException e) {
				// handle the exception, print an error message
				System.err.println("File not found: " + e.getMessage());
				System.exit(0);
			}
		


			// Making maps

			contentsMap = new HashMap<String, Integer>();

			for (AudioContent content: contents) {
				contentsMap.put(content.getTitle(), contents.indexOf(content));
			}
					
	
			artistsAuthors = new TreeMap<String, ArrayList<Integer>>();

			for (AudioContent content: contents) {
				// Checking if the content is of type "SONG"
				if (content.getType() == "SONG") {
					Song newContent = (Song) content;

					// If the TreeMap already contains the artist of the song, add the index of the content to their ArrayList
					if (artistsAuthors.containsKey(newContent.getArtist())) {
						artistsAuthors.get(newContent.getArtist()).add(contents.indexOf(content));
					}
					// If the TreeMap does not yet contain the artist of the song, create a new ArrayList and add the index of the content to it
					else {
						ArrayList<Integer> tempList = new ArrayList<Integer>();
						tempList.add(contents.indexOf(content));

						artistsAuthors.put(newContent.getArtist(), tempList);
					}
				}
				// Checking if the content is of type "AUDIOBOOK"
				else if (content.getType() == "AUDIOBOOK") {
					AudioBook newBook = (AudioBook) content;

					// If the TreeMap already contains the author of the audiobook, add the index of the content to their ArrayList
					if (artistsAuthors.containsKey(newBook.getAuthor())) {
						artistsAuthors.get(newBook.getAuthor()).add(contents.indexOf(content));
					}
					else {
						// If the TreeMap does not yet contain the author of the audiobook, create a new ArrayList and add the index of the content to it
						ArrayList<Integer> tempList = new ArrayList<Integer>();
						tempList.add(contents.indexOf(content));

						artistsAuthors.put(newBook.getAuthor(), tempList);
					}
				}
			}
			genres = new TreeMap<String, ArrayList<Integer>>();

			for (AudioContent content: contents) {
				
				if (content.getType() == "SONG") {
					Song newContent = (Song) content;

					// Get the genre of the Song and convert it to a string for storage in the TreeMap
					Song.Genre genre = newContent.getGenre();
					String stringGenre = String.valueOf(genre);

					// Check if the genre key already exists in the TreeMap
					if (genres.containsKey(stringGenre)) {
						// If it does, add the index of the current object to the existing ArrayList under the genre key
						genres.get(stringGenre).add(contents.indexOf(content));
					}
					else {
						// If it does not, create a new ArrayList with the index of the current object and add it to the TreeMap under the new genre key
						ArrayList<Integer> tempList = new ArrayList<Integer>();

						tempList.add(contents.indexOf(content));

						genres.put(stringGenre, tempList);

					}
				}
			}
		}


		//getter methods for all the maps
		
		public Map<String, Integer> getContentsMap() {
			return contentsMap;
		}

		public Map<String, ArrayList<Integer>> getArtistsAuthorMap() {
			return artistsAuthors;
		}

		public Map<String, ArrayList<Integer>> getGenres() {
			return genres;
		}

		public ArrayList<AudioContent> getContents() {
			return contents;
		}
		
		public void search(String title) throws AudioContentNotFoundException {
			// Check if the title exists in the contentsMap
			if (getContentsMap().containsKey(title)) 
			{
				// If it does, print the index and information of the audio content
				System.out.print(getContentsMap().get(title) + 1);
				System.out.print(". ");
				getContents().get(getContentsMap().get(title)).printInfo();
			}
			else 
			{
				 // If it doesn't, throw an AudioContentNotFoundException with an error message
				throw new AudioContentNotFoundException("No matches for " + title);
			}
		}
		
		public void searchA(String artist) throws AudioContentNotFoundException
		{
			// Check if the artist/author exists in the artistsAuthors
			if (artistsAuthors.containsKey(artist)) 
			{
				ArrayList<Integer> tempContents = artistsAuthors.get(artist);

				for (Integer index: tempContents) 
				{
					// If it does, print the index and information of the audio content
					System.out.print(index+1 + ". ");
					contents.get(index).printInfo();
					System.out.println("\n");

				}
			}
			else 
			{
				// If it doesn't, throw an AudioContentNotFoundException with an error message
				throw new AudioContentNotFoundException("No matches for " + artist);
			}
		}

		public void searchG(String genre) throws AudioContentNotFoundException 
		{
			//Check if the genre exists in the genres

			if(genres.containsKey(genre)) 
			{
				// If it does, print the index and information of the audio content
				ArrayList<Integer> tempContents = genres.get(genre);

				for (Integer index: tempContents) 
				{
					System.out.print(index+1 + ". ");
					contents.get(index).printInfo();
					System.out.println("\n");

				}
			}
			else 
			{
				// If it doesn't, throw an AudioContentNotFoundException with an error message
				throw new AudioContentNotFoundException("No matches for " + genre);
			}
		}

		// This method takes an artist string as input and checks if the artist exists in the audio content library. If the artist is found,
		// the method returns an ArrayList of integers containing the indexes of all audio contents by that artist. If the artist is not found,
		// the method throws an AudioContentNotFoundException with a message stating that no matches were found for that artist.
		public ArrayList<Integer> checkArtists(String artist) throws AudioContentNotFoundException 
		{
			if (getArtistsAuthorMap().containsKey(artist)) 
			{
				ArrayList<Integer> indexes = getArtistsAuthorMap().get(artist);
				return indexes;
			}
			else 
			{
				throw new AudioContentNotFoundException("No matches for " + artist);
			}
		}

		// This method takes a genre string as input and checks if the genre exists in the audio content library. If the genre is found,
		// the method returns an ArrayList of integers containing the indexes of all audio contents with that genre. If the genre is not found,
		// the method throws an AudioContentNotFoundException with a message stating that no matches were found for that genre.
		public ArrayList<Integer> checkGenre(String genre) throws AudioContentNotFoundException 
		{
			if (getGenres().containsKey(genre)) 
			{
				ArrayList<Integer> indexes = getGenres().get(genre);
				return indexes;
			}
			else 
			{
				throw new AudioContentNotFoundException("No matches for " + genre);
			}
		}

		


		public AudioContent getContent(int index)
		{
			if (index < 1 || index > contents.size())
			{
				return null;
			}
			return contents.get(index-1);
		}
		
		public void listAll()
		{
			for (int i = 0; i < contents.size(); i++)
			{
				int index = i + 1;
				System.out.print("" + index + ". ");
				contents.get(i).printInfo();
				System.out.println();
			}
		}
		
}