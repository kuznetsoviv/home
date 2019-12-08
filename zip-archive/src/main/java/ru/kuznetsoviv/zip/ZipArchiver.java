package ru.kuznetsoviv.zip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipArchiver {

    public static void main(String[] args) throws IOException {
        readZip("zip-archive/zipArchive.zip");
    }

    private static void readZip(String path) throws IOException {
        ZipFile zipFile = new ZipFile(path);
        for (Enumeration<? extends ZipEntry> entries = zipFile.entries(); entries.hasMoreElements(); ) {
            ZipEntry zipEntry = entries.nextElement();
            if (!zipEntry.isDirectory()) {
                System.out.println("Entry: " + zipEntry.getName());
                BufferedReader reader = new BufferedReader(new InputStreamReader(zipFile.getInputStream(zipEntry)));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                System.out.println("--------------------");
            }
        }
    }
}
