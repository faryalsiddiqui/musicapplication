  // Name: Faryal Siddiqui
  // Student ID: 501157845

import java.util.ArrayList;

public class Season {

    // array list of all 3 componenets of episodes
    public ArrayList<String> episodeTitles;
    public ArrayList<String> episodeFiles;
    public ArrayList<Integer> episodeLengths;

    // season constructor which initializes array lists to empty array lists
    public Season() {
        episodeTitles = new ArrayList<String>();
        episodeFiles = new ArrayList<String>();
        episodeLengths = new ArrayList<Integer>();
    }
    
    // retrieves episode titles
    public ArrayList<String> getTitles() {
        return episodeTitles;
    }

    // retrieves episode files

    public ArrayList<String> getFiles() {
        return episodeFiles;
    }

    // retrieves episode lengths
    public ArrayList<Integer> getLengths() {
        return episodeLengths;
    }

    // prints podcast episode information
    public void printPodcastEpisode(int episodeIndex) {
        System.out.println("Episode " + (episodeIndex + 1) + ". " + episodeTitles.get(episodeIndex));

    }





        
    
}
