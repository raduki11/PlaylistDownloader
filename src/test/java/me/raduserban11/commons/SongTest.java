package me.raduserban11.commons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
    void testEqualsSameObject() {
        assertEquals(song1, song1);
    }

    @Test
    void testEqualsNullObject() {
        assertNotEquals(song1, null);
    }

    @Test
    void testEqualsDifferentClasses(){
        assertNotEquals(song1, "ddd");
    }

    @Test
    void testEqualsNotSameName(){
        assertNotEquals(song1, new Song("glue", "bicep"));
    }

    @Test
    void testEqualsNotSameArtist(){
        assertNotEquals(song1, new Song("apricots", "drake"));
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