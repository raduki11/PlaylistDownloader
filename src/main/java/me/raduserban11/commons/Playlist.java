package me.raduserban11.commons;

import java.util.List;
import java.util.Objects;

public class Playlist {

    private String name;
    private List<Song> songs;

    public Playlist(String name, List<Song> songs) {
        this.name = name;
        this.songs = songs;
    }

    public String getName() {
        return name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Playlist playlist = (Playlist) o;
        return Objects.equals(name, playlist.name) && Objects.equals(songs, playlist.songs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, songs);
    }
}
