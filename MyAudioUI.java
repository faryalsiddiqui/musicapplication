  // Name: Faryal Siddiqui
  // Student ID: 501157845

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;


// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI
{

	public static void main(String[] args)
	{
		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your mylibrary
		AudioContentStore store = new AudioContentStore();
		
		// Create my music mylibrary
		Library mylibrary = new Library();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
			while (scanner.hasNextLine())
			{
			
				String action = scanner.nextLine();

				try {
					if (action == null || action.equals("")) 
					{
						System.out.print("\n>");
						continue;
					}
					else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
						return;
					
					else if (action.equalsIgnoreCase("STORE"))	// List all songs
					{
						store.listAll(); 
					}
					else if (action.equalsIgnoreCase("SONGS"))	// List all songs
					{
						mylibrary.listAllSongs(); 
					}
					else if (action.equalsIgnoreCase("BOOKS"))	// List all books
					{
						mylibrary.listAllAudioBooks(); 
					}
					else if (action.equalsIgnoreCase("PODCASTS"))	// List all songs
					{
						mylibrary.listAllPodcasts(); 
					}
					else if (action.equalsIgnoreCase("ARTISTS"))	// List all songs
					{
						mylibrary.listAllArtists(); 
					}
					else if (action.equalsIgnoreCase("PLAYLISTS"))	// List all play lists
					{
						mylibrary.listAllPlaylists(); 
					}
					// Download audiocontent (song/audiobook/podcast) from the store 
					// Specify the index of the content
					else if (action.equalsIgnoreCase("DOWNLOAD")) 
					{
						int fromIndex = 0;
						int toIndex = 0;

						System.out.print("From Store Content #: ");

						if (scanner.hasNextInt())
						{
							fromIndex = scanner.nextInt();
							scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
						}

						System.out.print("To Store Content #: ");

						if (scanner.hasNextInt())
						{
							toIndex = scanner.nextInt();
							scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
						}

						for (int i = fromIndex; i <= toIndex; i++) {
							AudioContent content = store.getContent(i);

							if (content == null) {
								System.out.println("Content Not Found in Store");
							}
							else {
								// attempt to download the content from `mylibrary`
								try {
									mylibrary.download(content);
								} 
								// If an `AudioContentNotFoundException` is thrown during the download process, print the exception message to the console
								catch (AudioContentNotFoundException e) {
									
									System.out.println(e.getMessage());
								}
							}
					
						}

						
					}
					// Get the *library* index (index of a song based on the songs list)
					// of a song from the keyboard and play the song 
					else if (action.equalsIgnoreCase("PLAYSONG")) 
					{
						int index = 0;

						System.out.print("Song Number: ");

						if (scanner.hasNextInt()) 
						{
							index = scanner.nextInt();
							scanner.nextLine();
						}
						
						// Print error message if the song doesn't exist in the library

						mylibrary.playSong(index);
					}
					// Print the table of contents (TOC) of an audiobook that
					// has been downloaded to the library. Get the desired book index
					// from the keyboard - the index is based on the list of books in the library
					else if (action.equalsIgnoreCase("BOOKTOC")) 
					{
						int index = 0;

						System.out.print("Audio Book Number: ");

						if (scanner.hasNextInt()) 
						{
							index = scanner.nextInt();
							scanner.nextLine();
						}


						mylibrary.printAudioBookTOC(index-1);

					// Print error message if the book doesn't exist in the library
					}
					// Similar to playsong above except for audio book
					// In addition to the book index, read the chapter 
					// number from the keyboard - see class Library
					else if (action.equalsIgnoreCase("PLAYBOOK")) 
					{
						int index = 0;
						int chapter = 0;

						System.out.print("Audio Book Number: ");

						if (scanner.hasNextInt()) 
						{
							index = scanner.nextInt();
							scanner.nextLine();
						}

						System.out.print("Chapter: ");

						if (scanner.hasNextInt()) 
						{
							chapter = scanner.nextInt();
							scanner.nextLine();
						}


						mylibrary.playAudioBook(index - 1, chapter);
					}
					// Print the episode titles for the given season of the given podcast
					// In addition to the podcast index from the list of podcasts, 
					// read the season number from the keyboard
					// see class Library for the method to call
					else if (action.equalsIgnoreCase("PODTOC")) 
					{
						int index = 0;
						int season = 0;

						System.out.print("Podcast Number: ");

						if (scanner.hasNextInt()) 
						{
							index = scanner.nextInt();
							scanner.nextLine();
						}

						System.out.print("Season: ");

						if (scanner.hasNextInt()) 
						{
							season = scanner.nextInt();
							scanner.nextLine();
						}


						mylibrary.printPodcastEpisodes(index-1, season-1);

					}
					// Similar to playsong above except for podcast
					// In addition to the podcast index from the list of podcasts, 
					// read the season number and the episode number from the keyboard
					// see class Library for the method to call
					else if (action.equalsIgnoreCase("PLAYPOD")) 
					{
						int index = 0;
						int seasonNumber = 0;
						int episodeNumber = 0;

						System.out.print("Podcast Number: ");

						if (scanner.hasNextInt()) 
						{
							index = scanner.nextInt();
							scanner.nextLine();
						}

						System.out.print("Season: ");

						if (scanner.hasNextInt()) 
						{
							seasonNumber = scanner.nextInt();
							scanner.nextLine();
						}
						System.out.print("Episode: ");

						if (scanner.hasNextInt()) 
						{
							episodeNumber = scanner.nextInt();
							scanner.nextLine();
						}

						mylibrary.playPodcast(index-1, seasonNumber-1, episodeNumber-1);

					}
					// Specify a playlist title (string) 
					// Play all the audio content (songs, audiobooks, podcasts) of the playlist 
					// see class Library for the method to call
					else if (action.equalsIgnoreCase("PLAYALLPL")) 
					{
						String title = "";

						System.out.print("Playlist Title: ");
						title = scanner.next();

						mylibrary.playPlaylist(title);

						

					}
					// Specify a playlist title (string) 
					// Read the index of a song/audiobook/podcast in the playist from the keyboard 
					// Play all the audio content 
					// see class Library for the method to call
					else if (action.equalsIgnoreCase("PLAYPL")) 
					{
						String title = "";
						int index = 0;

						System.out.print("Playlist Title: ");
						title = scanner.next();

						System.out.print("Content Number: ");

						if (scanner.hasNextInt()) 
						{
							index = scanner.nextInt();
							scanner.nextLine();
						}

						mylibrary.playPlaylist(title, index - 1);

						
					}
					// Delete a song from the list of songs in mylibrary and any play lists it belongs to
					// Read a song index from the keyboard
					// see class Library for the method to call
					else if (action.equalsIgnoreCase("DELSONG")) 
					{
						int songNumber = 0;

						System.out.print("Library Song #: ");

						if (scanner.hasNextInt()) 
						{
							songNumber = scanner.nextInt();
							scanner.nextLine();
						}

						mylibrary.deleteSong(songNumber-1);

							
					}
					// Read a title string from the keyboard and make a playlist
					// see class Library for the method to call
					else if (action.equalsIgnoreCase("MAKEPL")) 
					{
						String title = "";

						System.out.print("Playlist Title: ");
						title = scanner.next();

						mylibrary.makePlaylist(title);
					}
					// Print the content information (songs, audiobooks, podcasts) in the playlist
					// Read a playlist title string from the keyboard
				// see class Library for the method to call
					else if (action.equalsIgnoreCase("PRINTPL"))	// print playlist content
					{
						String title = "";

						System.out.print("Playlist Title: ");
						title = scanner.next();

						mylibrary.printPlaylist(title);
					}
					// Add content (song, audiobook, podcast) from mylibrary (via index) to a playlist
					// Read the playlist title, the type of content ("song" "audiobook" "podcast")
					// and the index of the content (based on song list, audiobook list etc) from the keyboard
				// see class Library for the method to call
					else if (action.equalsIgnoreCase("ADDTOPL")) 
					{
						String title = "";
						String type = "";
						int contentNumber = 0;

						System.out.print("Playlist Title: ");
						title = scanner.next();

						System.out.print("Content Type[SONG, PODCAST, AUDIOBOOK]: ");
						type = scanner.next();

						System.out.print("Library Content #: ");
						if (scanner.hasNextInt()) 
						{
							contentNumber = scanner.nextInt();
							scanner.nextLine();
						}

						mylibrary.addContentToPlaylist(type, contentNumber-1, title);

						
					}
					// Delete content from play list based on index from the playlist
					// Read the playlist title string and the playlist index
				// see class Library for the method to call
					else if (action.equalsIgnoreCase("DELFROMPL")) 
					{
						String title = "";
						int index = 0;

						System.out.print("Playlist Title: ");
						title = scanner.next();

						System.out.print("Playlist Content #: ");
						if (scanner.hasNextInt()) 
						{
							index = scanner.nextInt();
							scanner.nextLine();
						}

						mylibrary.delContentFromPlaylist(index, title);
					}
					
					else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
					{
						mylibrary.sortSongsByYear();
					}
					else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
					{
						mylibrary.sortSongsByName();
					}
					else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
					{
						mylibrary.sortSongsByLength();
					}
					else if (action.equalsIgnoreCase("SEARCH")) 
					{
						// Prompt the user to enter the audio content's details
						String title = "";

						System.out.print("Title: ");
						title = scanner.nextLine();
						
						store.search(title);
					}
					else if (action.equalsIgnoreCase("SEARCHA")) 
					{
						// Prompt the user to enter the audio content's details
						String artist = "";

						System.out.print("Artist: ");
						artist = scanner.nextLine();
						
						store.searchA(artist);
					}
					else if (action.equalsIgnoreCase("SEARCHG")) 
					{
						// Prompt the user to enter the audio content's details
						String genre = "";

						System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: ");
						genre = (scanner.nextLine()).toUpperCase();
						
						store.searchG(genre);
					}
					else if (action.equalsIgnoreCase("DOWNLOADA")) 
					{
						//  Prompt the user to enter an artist to download
						String artist = "";

						System.out.print("Artist Name: ");
						artist = scanner.nextLine();

						// Find all audio content by the specified artist and download it from `mylibrary`
						
							ArrayList<Integer> indexes = store.checkArtists(artist);

							for (Integer index: indexes) {
								try {
									mylibrary.download(store.getContent(index+1));
								}
								catch (AudioContentNotFoundException e) {
									System.out.println(e.getMessage());
							}
							}
						}
					else if (action.equalsIgnoreCase("DOWNLOADG")) 
					{
						String genre = "";

						// Prompt the user to enter a genre to download
						System.out.print("Genre: ");
						genre = scanner.nextLine().toUpperCase();

						// Find all audio content in the specified genre and download it from `mylibrary`
						
							ArrayList<Integer> indexes = store.checkGenre(genre);

							for (Integer index: indexes) {
								try {
									mylibrary.download(store.getContent(index+1));
								}
								catch (AudioContentNotFoundException e) {
									System.out.println(e.getMessage());
								}
								
							}
					}
				}
				catch (AudioContentNotFoundException e) {
					// This catch statement catches an AudioContentNotFoundException that might be thrown by
					// method call in the try block. If such an exception is caught, it prints the exception message to the console.

					System.out.println(e.getMessage());
				}
				
				System.out.print("\n>");

				
				
			}
		}
		
		}

