package me.raduserban11;

import me.raduserban11.commons.Playlist;
import me.raduserban11.commons.Song;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Downloader {

    protected List<Song> skipped = new ArrayList<>();

    protected List<Song> downloaded = new ArrayList<>();

    public void download(Playlist playlist) {
        WebDriver driver = new ChromeDriver();

        for (Song song : playlist.getSongs()) {
            String ytUrl = "https://www.youtube.com/results?search_query=" + song.getName().replace(" ", "+");
            driver.get(ytUrl);
            if (downloaded.size() == 0) {
                WebElement acceptButton = driver.findElement(By.xpath("/html/body/ytd-app/ytd-consent-bump-v2-lightbox/tp-yt-paper-dialog/div[4]/div[2]/div[6]/div[1]/ytd-button-renderer[2]/yt-button-shape/button"));
                acceptButton.click();
            }
            driver.navigate().refresh();
            WebElement thumbnail;
            try {
                thumbnail = new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(d -> d.findElement(By.xpath("/html/body/ytd-app/div[1]/ytd-page-manager/ytd-search/div[1]/ytd-two-column-search-results-renderer/div/ytd-section-list-renderer/div[2]/ytd-item-section-renderer/div[3]/ytd-video-renderer[1]/div[1]/ytd-thumbnail/a")));
            } catch (TimeoutException e){
                skipped.add(song);
                continue;
            }
            String songLink = thumbnail.getAttribute("href");
            driver.get("https://ytmp3.nu/E80bdA/");
            WebElement urlBar = driver.findElement(By.id("url"));
            urlBar.sendKeys(songLink);
            WebElement convertButton = driver.findElement(By.xpath("/html/body/form/div[2]/input[3]"));
            convertButton.submit();
            WebElement downloadButton;
            try {
                downloadButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(d -> d.findElement(By.xpath("/html/body/form/div[2]/a[1]")));
            } catch (TimeoutException e) {
                skipped.add(song);
                continue;
            }
            String downloadLink = downloadButton.getAttribute("href");
            driver.get(downloadLink);
            downloaded.add(song);
        }
    }

    public String message() {
        StringBuilder sb = new StringBuilder();
        int numberOfSkippedSongs = skipped.size();

        sb.append("Process completed successfully. Playlist Downloader downloaded " + numberOfSongsDownloaded() + " songs");

        if (numberOfSkippedSongs > 0) {
            sb.append(" and skipped the following " + skipped.size());
            if (skipped.size() == 1)
                sb.append(" song:\n");
            else sb.append(" songs:\n");

            for (Song skippedSong : skipped) {
                sb.append(skippedSong.getFullName() + '\n');
            }
        } else {
            sb.append(" and skipped 0 songs.");
        }

        return sb.toString();
    }

    public int getNumberOfSkippedSongs() {
        return skipped.size();
    }

    public int getNumberOfSongsDownloaded() {
        return downloaded.size();
    }
}
