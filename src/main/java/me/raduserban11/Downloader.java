package me.raduserban11;

import me.raduserban11.commons.Playlist;
import me.raduserban11.commons.Song;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Downloader {

    public static void download(Playlist playlist){
        WebDriver driver = new ChromeDriver();

        for(Song song : playlist.getSongs()){
            String ytUrl = "https://www.youtube.com/results?search_query=" + song.getName().replace(" " ,"+" );
            driver.get(ytUrl);
            WebElement acceptButton = driver.findElement(By.xpath("/html/body/ytd-app/ytd-consent-bump-v2-lightbox/tp-yt-paper-dialog/div[4]/div[2]/div[6]/div[1]/ytd-button-renderer[2]/yt-button-shape/button"));
            acceptButton.click();
            driver.navigate().refresh();
            WebElement thumbnail = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(d -> d.findElement(By.xpath("/html/body/ytd-app/div[1]/ytd-page-manager/ytd-search/div[1]/ytd-two-column-search-results-renderer/div/ytd-section-list-renderer/div[2]/ytd-item-section-renderer/div[3]/ytd-video-renderer[1]/div[1]/ytd-thumbnail/a")));
            String songLink = thumbnail.getAttribute("href");
            driver.get("https://ytmp3.nu/E80bdA/");
            WebElement urlBar = driver.findElement(By.id("url"));
            urlBar.sendKeys(songLink);
            WebElement convertButton = driver.findElement(By.xpath("/html/body/form/div[2]/input[3]"));
            convertButton.submit();
            WebElement downloadButton = new WebDriverWait(driver, Duration.ofSeconds(1))
                    .until(d -> d.findElement(By.xpath("/html/body/form/div[2]/a[1]")));
            String downloadLink = downloadButton.getAttribute("href");
            driver.get(downloadLink);
        }
    }
}
