  // Name: Faryal Siddiqui
  // Student ID: 501157845
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/*
 * This class manages, stores, and plays audio content such as songs, podcasts and audiobooks. 
 */
public class Library
{
	private ArrayList<Song> 			songs; 
	private ArrayList<AudioBook> 	audiobooks;
	private ArrayList<Playlist> 	playlists; 
	
  	private ArrayList<Podcast> 	podcasts;
	
	// Public methods in this class set errorMesg string 
	// Error Messages can be retrieved from main in class MyAudioUI by calling  getErrorMessage()
	// In assignment 2 we will replace this with Java Exceptions
	String errorMsg = "";


	public Library()
	{

		
		songs 			= new ArrayList<Song>(); 
		audiobooks 	= new ArrayList<AudioBook>(); ;
		playlists   = new ArrayList<Playlist>();
	  	podcasts		= new ArrayList<Podcast>(); ;
	}
	/*
	 * Download audio content from the store. Since we have decided (design decision) to keep 3 separate lists in our library
	 * to store our songs, podcasts and audiobooks (we could have used one list) then we need to look at the type of
	 * audio content (hint: use the getType() method and compare to Song.TYPENAME or AudioBook.TYPENAME etc)
	 * to determine which list it belongs to above
	 * 
	 * Make sure you do not add song/podcast/audiobook to a list if it is already there. Hint: use the equals() method
	 * If it is already in a list, set the errorMsg string and return false. Otherwise add it to the list and return true
	 * See the video
	 */
	public void download(AudioContent content) throws AudioContentNotFoundException
	{
		
		switch (content.getType())
		{
			case Song.TYPENAME:
				for (Song song: songs) 
				{
					// Checking if song is already present in songs library
					if (song.equals(content)) 
					{
						errorMsg = "SONG " + song.getTitle() + " already downloaded.";
						throw new AudioContentNotFoundException(errorMsg);
					}
				}

				Song tempSong = (Song) content;
				songs.add(tempSong);
				System.out.println("SONG " + tempSong.getTitle() +  " Added to Library.");
				break;

			case AudioBook.TYPENAME:
				for (AudioBook audioBook: audiobooks) 
				{
					// Checking if book is already present in audiobook library
					if (audioBook.equals(content)) 
					{
						errorMsg = "AUDIOBOOK " + audioBook.getTitle() + " already downloaded.";
						throw new AudioContentNotFoundException(errorMsg);
					}
				}

				AudioBook tempBook = (AudioBook) content;
				audiobooks.add(tempBook);
				System.out.println("AUDIOBOOK " + tempBook.getTitle() +  " Added to Library.");
				break;


		}

	}
	
	// Print Information (printInfo()) about all songs in the array list
	public void listAllSongs()
	{
		for (int i = 0; i < songs.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");
			songs.get(i).printInfo();
			System.out.println();	
		}
	}
	
	// Print Information (printInfo()) about all audiobooks in the array list
	public void listAllAudioBooks()
	{
		for (int i = 0; i < audiobooks.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");
			audiobooks.get(i).printInfo();
			System.out.println();	
		}
	}
	
  // Print Information (printInfo()) about all podcasts in the array list
	public void listAllPodcasts()
	{
		for (int i = 0; i < podcasts.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");
			podcasts.get(i).printInfo();
			System.out.println();	
		}
		
	}
	
  // Print the name of all playlists in the playlists array list
	// First print the index number as in listAllSongs() above
	public void listAllPlaylists()
	{
		for (int i = 0; i < playlists.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");
			System.out.println(playlists.get(i).getTitle());
		}
	}
	
  // Print the name of all artists. 
	public void listAllArtists()
	{
		// First create a new (empty) array list of string 
		// Go through the songs array list and add the artist name to the new arraylist only if it is
		// not already there. Once the artist arrayl ist is complete, print the artists names

		ArrayList<String> artists = new ArrayList<String>();

		for (Song song: songs) 
		{
			if (!artists.contains(song.getArtist())) 
			{
				artists.add(song.getArtist());
			}
		}


		for (String artist: artists) 
		{
			System.out.println(artists.indexOf(artist) + 1 + ". " + artist);
		}
		
	}

	// Delete a song from the library (i.e. the songs list) - 
	// also go through all playlists and remove it from any playlist as well if it is part of the playlist
	public void deleteSong(int index) throws AudioContentNotFoundException
	{

		// Raise an error if index is not valid

		if (index <= 0 || index > songs.size()) 
		{
			errorMsg = "Song not downloaded.";
			throw new AudioContentNotFoundException(errorMsg);
		}

		// Delete song from any playlist it is in first
		for (int i = 0; i < playlists.size(); i++) 
		{
			if (playlists.get(i).getContent().contains(songs.get(index))) 
			{
				int songIndex = playlists.get(i).getContent().indexOf(songs.get(index));
				playlists.get(i).deleteContent(songIndex+1);
			}
		}
		
		// Remove song from song library
		songs.remove(songs.get(index));

	}
	
  //Sort songs in library by year
	public void sortSongsByYear()
	{
		// Use Collections.sort() 

		Collections.sort(songs, new SongYearComparator());

	}
  // Write a class SongYearComparator that implements
	// the Comparator interface and compare two songs based on year
	private class SongYearComparator implements Comparator<Song>
	{
		public int compare(Song s1, Song s2) 
		{

			// If song1 has a greater year than song2, return 1
			if (s1.getYear() > s2.getYear()) 
			{
				return 1;
			}
			// If song1 has a lower year than song2, return -1
			else if (s1.getYear() < s2.getYear()) 
			{
				return -1;
			}
			// If song1 and song2 have the same year, return 0
			return 0;
		}
	}

	// Sort songs by length
	public void sortSongsByLength()
	{
	 // Use Collections.sort() 
	 Collections.sort(songs, new SongLengthComparator());
	}
  // Write a class SongLengthComparator that implements
	// the Comparator interface and compare two songs based on length
	private class SongLengthComparator implements Comparator<Song>
	{
		public int compare(Song s1, Song s2) 
		{
			// If song1 has a greater length than song2, return 1
			if (s1.getLength() > s2.getLength()) 
			{
				return 1;
			}
			// If song1 has a lower length than song2, return -1
			else if (s1.getLength() < s2.getLength()) 
			{
				return -1;
			}
			// If song1 and song2 have the same length, return 0
			return 0;
		}
	}

	// Sort songs by title 
	public void sortSongsByName()
	{
	  // Use Collections.sort()
		// class Song should implement the Comparable interface
		// see class Song code

		Collections.sort(songs);
	}

	
	/*
	 * Play Content
	 */
	
	// Play song from songs list
	public void playSong(int index) throws AudioContentNotFoundException
	{
		// Checks if index is valid
		if (index < 1 || index > songs.size())
		{
			errorMsg = "Song Not Found";
			throw new AudioContentNotFoundException(errorMsg);
		}
		songs.get(index-1).play();
	}
	
	// Play podcast from list (specify season and episode)
	// Bonus
	public void playPodcast(int index, int season, int episode) throws AudioContentNotFoundException
	{
		// Checks if podcast index is valid
		if (index < 0 || index >= podcasts.size())
		{
			errorMsg = "Podcast Not Found";
			throw new AudioContentNotFoundException(errorMsg);
		}

		// Checks if season index is valid
		if (season < 0 || season >= podcasts.get(index).getSeasons().size()) 
		{
			errorMsg = "Season in podcast not found.";
			throw new AudioContentNotFoundException(errorMsg);
		}

		// Checks if episode index is valid
		if (episode < 0 || episode >= podcasts.get(index).getSeasons().get(season).getTitles().size()) 
		{
			errorMsg = "Episode in season in podcast not found.";
			throw new AudioContentNotFoundException(errorMsg);
		}

		podcasts.get(index).play(season, episode);
	}
	
	// Print the episode titles of a specified season
	// Bonus 
	public void printPodcastEpisodes(int index, int season) throws AudioContentNotFoundException
	{
		// Checks if podcast index is valid
		if (index < 0 || index >= podcasts.size()) 
		{
			errorMsg = "Podcast not found.";
			throw new AudioContentNotFoundException(errorMsg);
		}

		// Checks if season index is valid

		if (season < 0 || season >= podcasts.get(index).getSeasons().size()) 
		{
			errorMsg = "Season in podcast not found.";
			throw new AudioContentNotFoundException(errorMsg);
		}

		Season tempSeason = podcasts.get(index).getSeasons().get(season);

		for (int i = 0; i < tempSeason.getTitles().size(); i++) 
		{
			tempSeason.printPodcastEpisode(i);
			System.out.println();
		}
	}
	
	// Play a chapter of an audio book from list of audiobooks
	public void playAudioBook(int index, int chapter) throws AudioContentNotFoundException
	{
		// Checks if the audiobook index is valid
		if (index < 0 || index >= audiobooks.size()) 
		{
			errorMsg = "AudioBook not found.";
			throw new AudioContentNotFoundException(errorMsg);
		}

		// Checks if the chapter index is valid
		if (chapter < 0 || chapter > audiobooks.get(index).getNumberOfChapters()) 
		{
			errorMsg = "Chapter not found.";
			throw new AudioContentNotFoundException(errorMsg);
		}

		audiobooks.get(index).selectChapter(chapter);
		audiobooks.get(index).play();

	}
	
	// Print the chapter titles (Table Of Contents) of an audiobook
	// see class AudioBook
	public void printAudioBookTOC(int index) throws AudioContentNotFoundException
	{
		if (index < 0 || index >= audiobooks.size()) 
		{
			errorMsg = "AudioBook not found.";
			throw new AudioContentNotFoundException(errorMsg);
		}

		audiobooks.get(index).printTOC();
	}
	
  /*
   * Playlist Related Methods
   */
	
	// Make a new playlist and add to playlists array list
	// Make sure a playlist with the same title doesn't already exist
	public void makePlaylist(String title) throws AudioContentNotFoundException
	{
		Playlist p = new Playlist(title);
		if (playlists.contains(p)) 
		{
			errorMsg = "Playlist " + title + " Already Exists.";
			throw new AudioContentNotFoundException(errorMsg);
		}
		playlists.add(p);
	}
	
	// Print list of content information (songs, audiobooks etc) in playlist named title from list of playlists
	public void printPlaylist(String title) throws AudioContentNotFoundException
	{
		int playlistIndex = -1;

		for (int i = 0; i < playlists.size(); i++) 
		{
			if (playlists.get(i).getTitle().equals(title)) 
			{
				playlistIndex = i;
			}
		}

		if (playlistIndex == -1) 
		{
			errorMsg = "Playlist not found.";
			throw new AudioContentNotFoundException(errorMsg);
		}

		playlists.get(playlistIndex).printContents();
	}
	
	// Play all content in a playlist
	public void playPlaylist(String playlistTitle) throws AudioContentNotFoundException
	{
		boolean found = false;
		Playlist p = new Playlist(playlistTitle);

		for (Playlist playlist: playlists) 
		{
			if (p.getTitle().equals(playlistTitle)) 
			{
				playlist.playAll();
				found = true;
			}
		}
		if (!found) {
			errorMsg = "Playlist not found.";
			throw new AudioContentNotFoundException(errorMsg);
		}
	}
	
	// Play a specific song/audiobook in a playlist
	public void playPlaylist(String playlistTitle, int indexInPL) throws AudioContentNotFoundException
	{
		int playlistIndex = -1;

		for (int i = 0; i < playlists.size(); i++) 
		{
			if (playlists.get(i).getTitle().equals(playlistTitle)) 
			{
				playlistIndex = i;
			}
		}

		if (playlistIndex == -1) 
		{
			errorMsg = "Playlist not found.";
			throw new AudioContentNotFoundException(errorMsg);
		}

		Playlist p = playlists.get(playlistIndex);

		if (indexInPL >= p.getContent().size() || indexInPL < 0) 
		{
			errorMsg = "Content in playlist not found.";
			throw new AudioContentNotFoundException(errorMsg);
		}

		
		playlists.get(playlistIndex).play(indexInPL);
	
	}
	
	// Add a song/audiobook/podcast from library lists at top to a playlist
	// Use the type parameter and compare to Song.TYPENAME etc
	// to determine which array list it comes from then use the given index
	// for that list
	public void addContentToPlaylist(String type, int index, String playlistTitle) throws AudioContentNotFoundException
	{
		int playlistIndex = -1;

				// Find the index of the playlist with the given title
				for (int i = 0; i < playlists.size(); i++) 
				{
					if (playlists.get(i).getTitle().equals(playlistTitle)) 
					{
						playlistIndex = i;
					}
				}

				// If the playlist wasn't found, set an error message and return false
				if (playlistIndex == -1) 
				{
					errorMsg = "Playlist not found.";
					throw new AudioContentNotFoundException(errorMsg);
				}

		type = type.toUpperCase();	// Convert type to uppercase to simplify comparisons
		
		// Switch on the type of content being added to the playlist

		switch (type) 
		{
			case "SONG":	

				// If the song at the given index doesn't exist, set an error message and return false
				if (index >= songs.size() || index < 0) 
				{
					errorMsg = "Song not found in library";
					throw new AudioContentNotFoundException(errorMsg);
				}

				// Add the song to the playlist and break out of the switch statement
				playlists.get(playlistIndex).addContent(songs.get(index));
				break;
	
			case "AUDIOBOOK":

				// If the audiobook at the given index doesn't exist, set an error message and return false
				if (index >= audiobooks.size() || index < 0) 
				{
					errorMsg = "AudioBook not found in library";
					throw new AudioContentNotFoundException(errorMsg);
				}

				// Add the audiobook to the playlist and break out of the switch statement
				playlists.get(playlistIndex).addContent(audiobooks.get(index));
				break;

			case "PODCAST":

				// If the podcast at the given index doesn't exist, set an error message and return false
				if (index >= podcasts.size() || index < 0) 
				{
					errorMsg = "Audio Book not found in library";
					throw new AudioContentNotFoundException(errorMsg);
				}

				// Add the podcast to the playlist and break out of the switch statement
				playlists.get(playlistIndex).addContent(podcasts.get(index));
				break;
				
			default:
				// if content is invalid
				errorMsg = "Invalid content type";
				throw new AudioContentNotFoundException(errorMsg);
		}
	}

  // Delete a song/audiobook/podcast from a playlist with the given title
	// Make sure the given index of the song/audiobook/podcast in the playlist is valid 
	public void delContentFromPlaylist(int index, String title) throws AudioContentNotFoundException
	{
		Playlist p = new Playlist(title);

		boolean found = false;
		for (Playlist playlist: playlists) 
		{
			if (p.equals(playlist)) 
			{
				if (playlist.contains(index)) 
				{
					playlist.deleteContent(index);
					found = true;
				}
			}
		}
		if (!found) {
			errorMsg = "Content not found";
			throw new AudioContentNotFoundException(errorMsg);
		}
	}


	
	}

