package me.raduserban11.commons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SongTest {

    Song song1;

    @BeforeEach
    void setUp(){
        song1 =  new Song("apricots", "bicep");
    }

    @Test
    void getName() {
        assertEquals(song1.getName(), "apricots");
    }

    @Test
    void getArtist() {
        assertEquals(song1.getArtist(), "bicep");
    }

    @Test
    void testEquals() {
        Song song2 = new Song("apricots", "bicep");
        assertEquals(song1, song2);
    }

    @Test
    void testHashCode() {
        Song song2 = new Song("apricots", "bicep");
        assertEquals(song1.hashCode(), song2.hashCode());
    }

    @Test
    void testGetFullName(){
        assertEquals(song1.getFullName(), "bicep - apricots");
    }
}