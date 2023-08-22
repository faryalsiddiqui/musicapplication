  // Name: Faryal Siddiqui
  // Student ID: 501157845

import java.util.ArrayList;

public class Podcast extends AudioContent {

    public static final String TYPENAME =	"PODCAST";
    
    // Arraylist seasons to store podcast seasons
    private ArrayList<Season> seasons;
    private String host;

    public Podcast(String title, int year, String id, String type, String audioFile, int length, String host, ArrayList<Season> seasons) {
        // initializing variables with parameters
        super(title, year, id, type, audioFile, length);
        this.host = host;
        this.seasons = seasons;
    }

    // accessing private method TYPENAME
    public String getType() {
        return TYPENAME;
    }

    // printing podcast information
    public void printInfo() {
        super.printInfo();
        System.out.println("Host: " + this.host);
        System.out.println("Seasons: " + seasons.size());
    }

    // playing a podcast season's epsiode
    public void play(int seasonNum, int episodeNum) {
        this.setAudioFile(seasons.get(seasonNum).getTitles().get(episodeNum) + "." + "\n"+seasons.get(seasonNum).getFiles().get(episodeNum));
        System.out.println(this.getSeasons().get(seasonNum).getTitles().get(episodeNum) + ".");
        super.play();
    }

    // printing the table of contents of the podcast's season
    public void printTOC(int seasonNum) {
        
        for (int i = 0 ; i < seasons.get(seasonNum).getTitles().size() ; i++) {
            super.printInfo();
            System.out.println("Host: " + this.host);
            seasons.get(seasonNum).printPodcastEpisode(i);
        }

    }

    // checking if current podcast is equal to podcast being passed
    public boolean equals(Object other)
	{
        // prevents non-podcast objects from being compared
		if (!(other instanceof Podcast)) {
			return false;
		}
		
		Podcast otherA = (Podcast) other;
		return (super.equals(other) && this.host.equals(otherA.host) && this.seasons.equals(otherA.seasons));
	}

    // accesses private variable host

    public String getHost() {
        return this.host;
    }

    // sets private variable host

    public void setHost(String host) {
        this.host = host; 
    }

    //accesses private arraylist seasons

    public ArrayList<Season> getSeasons() {
        return seasons;
    }
}
