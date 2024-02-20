
/**
 * this class is usesd to find the youtube URLs from a webpage:
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class WordFind {
    public void wordfind() {
        URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        int indexOfYoutube = 0;
        int firstOccur;
        int lastOccur;
        String LCw;// lowercase of the URL
        String URL = "";
        for (String w : ur.words()) {
            LCw = w.toLowerCase();
            indexOfYoutube = LCw.indexOf("youtube.com");
            if (indexOfYoutube != -1) {
                lastOccur = w.indexOf("\"", indexOfYoutube + 1);
                firstOccur = w.lastIndexOf("\"", indexOfYoutube);
                URL = w.substring(firstOccur + 1, lastOccur);
                System.out.println(URL);

            }
        }
    }

}