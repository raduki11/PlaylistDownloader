package me.raduserban11.utils;

import me.raduserban11.commons.Playlist;
import me.raduserban11.commons.Song;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TextToPlaylistConverter {

    public static Playlist convert(File file){
        FileReader fileReader;
        BufferedReader bufferedReader;
        try {
            fileReader =  new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<Song> songs = new ArrayList<>();
        bufferedReader.lines().forEach(line -> {
            String[] parts;
            parts = line.split(" - ");
            String artist = parts[0];
            String songName = parts[1];
            Song song = new Song(songName, artist);
            songs.add(song);
        });

        return new Playlist(file.getName(), songs);
    }
}
