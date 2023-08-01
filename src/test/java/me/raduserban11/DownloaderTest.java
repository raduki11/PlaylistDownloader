package me.raduserban11;

import me.raduserban11.commons.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DownloaderTest extends Downloader{

    Downloader downloader;

    @BeforeEach
    void setUp(){
        downloader = new Downloader();
    }
    @Override
    public void setSkipped(List<Song> skipped) {
        this.skipped = skipped;
    }

    @Override
    public void setDownloaded(List<Song> downloaded) {
        this.downloaded = downloaded;
    }

    @Test
    void testMessageEmpty() {
        Downloader downloader =  new Downloader();
        assertEquals(downloader.message(), "Process completed successfully. Playlist Downloader downloaded 0 songs and skipped 0 songs.");
    }
}