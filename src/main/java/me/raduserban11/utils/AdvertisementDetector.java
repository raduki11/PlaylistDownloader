package me.raduserban11.utils;


import me.raduserban11.commons.Song;
import org.apache.commons.text.similarity.JaccardSimilarity;

public class AdvertisementDetector {

    public static boolean isNotAdvertisement(String videoTitle, Song song){
        if(videoTitle.toLowerCase().contains(song.getName().toLowerCase()) || videoTitle.toLowerCase().contains(song.getArtist().toLowerCase()))
            return true;
        else {
            JaccardSimilarity jaccardSimilarity = new JaccardSimilarity();
            double similarity = jaccardSimilarity.apply(videoTitle.toLowerCase(), song.getFullName().toLowerCase());
            if(similarity < 0.65)
                return false;
            return true;
        }
    }
}
