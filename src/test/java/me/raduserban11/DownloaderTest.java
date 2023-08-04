package me.raduserban11;

import me.raduserban11.commons.Playlist;
import me.raduserban11.commons.Song;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DownloaderTest {
    @Test
    void testMessageEmpty() {
        Downloader downloader = new Downloader(new Playlist("abc", List.of()));
        assertEquals(downloader.message(), "Process completed successfully. Playlist Downloader downloaded 0 songs and skipped no songs.");
    }


    @Test
    void testMessageExactlyOneSong() {
        Downloader downloader = new Downloader(new Playlist("abc", List.of(
                new Song("name1", "artist1")
        )));
        downloader.downloaded = List.of(
                new Song("name1", "artist1")
        );
        assertEquals(downloader.message(), "Process completed successfully. Playlist Downloader downloaded 1 song and skipped no songs.");
    }

    @Test
    void testMessageMultipleSongs() {
        Downloader downloader = new Downloader(new Playlist("abc", List.of(
                new Song("name1", "artist1"),
                new Song("name2", "artist2")
        )));
        downloader.downloaded = downloader.getPlaylist().getSongs();
        assertEquals(downloader.message(), "Process completed successfully. Playlist Downloader downloaded 2 songs and skipped no songs.");
    }

    @Test
    void testMessageMultipleSongsAndSkippedOne() {
        List<Song> songsInPlaylist = List.of(
                new Song("name1", "artist1"),
                new Song("name2", "artist2"),
                new Song("skipped1", "artist3")
        );
        Playlist playlistToDownload = new Playlist("abc", songsInPlaylist);

        Downloader downloader =  new Downloader(playlistToDownload);
        downloader.downloaded = List.of(
                new Song("name1", "artist1"),
                new Song("name2", "artist2")
        );

        downloader.skipped = List.of(
                new Song("skipped1", "artist3")
        );

        assertEquals(downloader.message(), "Process completed successfully. Playlist Downloader downloaded 2 songs and skipped the following 1 song(s):" +
                "\n\nartist3 - skipped1\n");
    }

    @Test
    void testFailedProcess(){
        List<Song> songsInPlaylist = List.of(
                new Song("name1", "artist1"),
                new Song("name2", "artist2"),
                new Song("skipped1", "artist3"),
                new Song("unseen", "ahj")
        );
        Playlist playlistToDownload = new Playlist("abc", songsInPlaylist);

        Downloader downloader =  new Downloader(playlistToDownload);
        downloader.downloaded = List.of(
                new Song("name1", "artist1"),
                new Song("name2", "artist2")
        );

        downloader.skipped = List.of(
                new Song("skipped1", "artist3")
        );

        assertEquals("Process was either stopped or did not complete successfully. Playlist Downloader downloaded " +
                "2 songs and skipped the following 1 song(s):\n\nartist3 - skipped1\n" +
                "\n1 song(s) were neglected.", downloader.message());
    }
}