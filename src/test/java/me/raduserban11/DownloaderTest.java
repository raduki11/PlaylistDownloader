package me.raduserban11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DownloaderTest {

    Downloader downloader;

    @BeforeEach
    void setUp(){
        downloader = new Downloader();
    }

    @Test
    void testMessageEmpty() {
        Downloader downloader =  new Downloader();
        assertEquals(downloader.message(), "Process completed successfully. Playlist Downloader downloaded 0 songs and skipped 0 songs.");
    }
}