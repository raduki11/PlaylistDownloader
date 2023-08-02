package me.raduserban11.utils;

import me.raduserban11.commons.Playlist;
import me.raduserban11.commons.Song;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TextToPlaylistConverterTest {

    @Test
    void testConvertNormal() {
        File file = new File("src/test/java/me/raduserban11/utils/testFile.txt");
        FileWriter writer;
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            writer = new FileWriter(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            writer.write("artist1 - song1\n");
            writer.write("artist2 - song2\n");
            writer.write("artist3 - song3");
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Song song1 = new Song("song1", "artist1");
        Song song2 = new Song("song2", "artist2");
        Song song3 = new Song("song3", "artist3");
        List list = List.of(song1, song2, song3);

        assertEquals(TextToPlaylistConverter.convert(file), new Playlist("testFile", list));
    }

    @Test
    void testConvertException(){
        assertThrows(RuntimeException.class, () -> {
           TextToPlaylistConverter.convert(new File("non-existent.txt"));
        });
    }
}