package me.raduserban11;

import me.raduserban11.commons.Playlist;
import me.raduserban11.commons.Song;
import me.raduserban11.utils.AdvertisementDetector;
import me.raduserban11.utils.MyChromeDriver;
import me.raduserban11.utils.exceptions.ElementNotFoundInTimeException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Downloader {

    Playlist playlist;
    protected List<Song> skipped;
    protected List<Song> downloaded;

    public Downloader(Playlist playlist) {
        this.playlist = playlist;
        this.skipped = new ArrayList<>();
        this.downloaded = new ArrayList<>();
    }

    public void download() {
        System.setProperty("selenium.manager.enabled", "false");
        MyChromeDriver driver = new MyChromeDriver();

        for (Song song : playlist.getSongs()) {
            String ytUrl = "https://www.youtube.com/results?search_query=" + song.getName().replace(" ", "+") + ' ' + song.getArtist().replace(" ", "+");
            driver.get(ytUrl);

            if (downloaded.isEmpty() && skipped.isEmpty()) {
                WebElement acceptButton;
                try {
                    acceptButton = driver.findElement(By.xpath("/html/body/ytd-app/ytd-consent-bump-v2-lightbox/tp-yt-paper-dialog/div[4]/div[2]/div[6]/div[1]/ytd-button-renderer[2]/yt-button-shape/button"));
                } catch (ElementNotFoundInTimeException e) {
                    skip(song);
                    continue;
                }
                acceptButton.click();
            }

            driver.navigateRefresh();

            WebElement thumbnail;
            try {
                thumbnail = driver.findElement(By.xpath("/html/body/ytd-app/div[1]/ytd-page-manager/ytd-search/div[1]/ytd-two-column-search-results-renderer/div/ytd-section-list-renderer/div[2]/ytd-item-section-renderer/div[3]/ytd-video-renderer[1]/div[1]/ytd-thumbnail/a"));
            } catch (ElementNotFoundInTimeException e) {
                skip(song);
                continue;
            }
            WebElement songTitle;
            try{
                songTitle = driver.findElement(By.xpath("/html/body/ytd-app/div[1]/ytd-page-manager/ytd-search/div[1]/ytd-two-column-search-results-renderer/div/ytd-section-list-renderer/div[2]/ytd-item-section-renderer/div[3]/ytd-video-renderer[1]/div[1]/div/div[1]/div/h3/a/yt-formatted-string"));
                if(!AdvertisementDetector.isNotAdvertisement(songTitle.getText(), song)){
                    System.out.println("reclama gasita: " + songTitle.getText());
                    skip(song);
                    continue;
                }
            } catch (ElementNotFoundInTimeException e){
                skip(song);
                continue;
            }
            String songLink = thumbnail.getAttribute("href");

            driver.get("https://ytmp3.nu/E80bdA/");

            WebElement urlBar;
            try{
                urlBar = driver.findElement(By.id("v"));
            } catch (ElementNotFoundInTimeException e){
                skip(song);
                continue;
            }
            urlBar.sendKeys(songLink);

            WebElement convertButton;
            try{
                convertButton = driver.findElement(By.xpath("/html/body/form/div[2]/button[2]"));
            } catch (ElementNotFoundInTimeException e){
                skip(song);
                continue;
            }
            convertButton.submit();

            WebElement downloadButton;
            try {
                downloadButton = driver.findElement(By.xpath("/html/body/form/div[2]/button[1]"));
            } catch (ElementNotFoundInTimeException e) {
                skip(song);
                continue;
            }

            // ... inside your download() loop
            downloadButton.click();
            downloaded.add(song);

            // Close the ad tab immediately
            driver.closeOtherTabs();

            System.out.println(downloaded.size() + "/" + playlist.getSongs().size() + " : Downloaded song " + song.getFullName() );
        }
    }

    private void skip(Song song) {
        skipped.add(song);
        System.out.println("Skipped song " + song.getFullName() + "...\n");
    }

    public String message() {
        StringBuilder sb = new StringBuilder();

        int numberOfDownloadedSongs = getNumberOfDownloadedSongs();
        int numberOfSkippedSongs = getNumberOfSkippedSongs();

        if (numberOfDownloadedSongs + numberOfSkippedSongs == playlist.getSongs().size()) {
            sb.append("Process completed successfully. Playlist Downloader downloaded ").append(numberOfDownloadedSongs);
            messageHelper(sb, numberOfDownloadedSongs, numberOfSkippedSongs);
        } else {
            sb.append("Process was either stopped or did not complete successfully. Playlist Downloader downloaded ").append(numberOfDownloadedSongs);
            messageHelper(sb, numberOfDownloadedSongs, numberOfSkippedSongs);
            int numberOfNeglectedSongs = playlist.getSongs().size() - numberOfDownloadedSongs - numberOfSkippedSongs;
            sb.append("\n").append(numberOfNeglectedSongs).append(" song(s) were neglected.");
        }

        return sb.toString();
    }

    private void messageHelper(StringBuilder sb, int numberOfDownloadedSongs, int numberOfSkippedSongs) {
        if (numberOfDownloadedSongs == 1)
            sb.append(" song");
        else sb.append(" songs");

        if (numberOfSkippedSongs == 0)
            sb.append(" and skipped no songs.");

        else {
            sb.append(" and skipped the following " + numberOfSkippedSongs + " song(s):\n\n");

            for (Song skippedSong : skipped) {
                sb.append(skippedSong.getFullName()).append('\n');
            }
        }
    }

    //just for testing purposes
    protected Playlist getPlaylist() {
        return this.playlist;
    }

    public int getNumberOfSkippedSongs() {
        return skipped.size();
    }

    public int getNumberOfDownloadedSongs() {
        return downloaded.size();
    }
}
