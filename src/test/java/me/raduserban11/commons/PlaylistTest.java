package me.raduserban11.commons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
    void testEqualsSameObject() {
        assertEquals(playlist1, playlist1);
    }

    @Test
    void testEqualsNullObject() {
        assertNotEquals(playlist1, null);
    }

    @Test
    void testEqualsDifferentClasses(){
        assertNotEquals(playlist1, songs1);
    }

    @Test
    void testEqualsNotSameName() {
        Playlist playlist2 = new Playlist("abca", List.of(
                new Song("heartless", "the weeknd"),
                new Song("heartless", "kanye west"),
                new Song("radioactive", "imagine dragons")
        ));
        assertNotEquals(playlist1, playlist2);
    }

    @Test
    void testEqualsNotSameSongs() {
        Playlist playlist2 = new Playlist("abc", List.of(
                new Song("heartless", "the weeknd"),
                new Song("heartless", "kanye west")
        ));
        assertNotEquals(playlist1, playlist2);
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