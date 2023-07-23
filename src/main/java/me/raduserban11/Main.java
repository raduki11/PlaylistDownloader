package me.raduserban11;

import me.raduserban11.commons.Playlist;
import me.raduserban11.utils.TextToPlaylistConverter;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please input playlist text file path:");
        String path = scanner.nextLine();

        if(path.startsWith("\"") && path.endsWith("\""))
            path = path.replaceFirst("\"", "").substring(0, path.length() - 2);

        Playlist playlist = TextToPlaylistConverter.convert(new File(path));

        Downloader downloader =  new Downloader();
        downloader.download(playlist);
    }
}