package me.raduserban11.commons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlaylistTest {

    Playlist playlist1;
    List<Song> songs1;

    @BeforeEach
    void setUp(){
        songs1 = List.of(
                new Song("heartless", "the weeknd"),
                new Song("heartless", "kanye west"),
                new Song("radioactive", "imagine dragons")
        );
        playlist1 = new Playlist("abc", songs1);
    }

    @Test
    void testGetName() {
        assertEquals(playlist1.getName(), "abc");
    }

    @Test
    void testGetSongs() {
        assertEquals(playlist1.getSongs(), List.of(
                new Song("heartless", "the weeknd"),
                new Song("heartless", "kanye west"),
                new Song("radioactive", "imagine dragons")
        ));
    }

    @Test
    void testEquals() {
        Playlist playlist2 = new Playlist("abc", List.of(
                new Song("heartless", "the weeknd"),
                new Song("heartless", "kanye west"),
                new Song("radioactive", "imagine dragons")
        ));
        assertEquals(playlist1, playlist2);
    }

    @Test
    void testHashCode() {
        Playlist playlist2 = new Playlist("abc", List.of(
                new Song("heartless", "the weeknd"),
                new Song("heartless", "kanye west"),
                new Song("radioactive", "imagine dragons")
        ));
        assertEquals(playlist1.hashCode(), playlist2.hashCode());
    }
}