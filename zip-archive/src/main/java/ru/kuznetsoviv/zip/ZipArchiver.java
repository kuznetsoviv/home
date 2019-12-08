package ru.kuznetsoviv.zip;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class ZipArchiver {

    private static final String FILE = "writeZipArchive.zip";

    public static void main(String[] args) throws IOException {
        readZip("zip-archive/readZipArchive.zip");
        try (ZipOutputStream zos = new ZipOutputStream(Files.newOutputStream(Paths.get("zip-archive/" + FILE)))) {
            writeZip(zos, new File(".").listFiles(), "");
        }
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

    private static void writeZip(ZipOutputStream zos, File[] files, String path) throws IOException {
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    writeZip(zos, file.listFiles(), path + file.getName() + "/");
                } else if (!file.getName().equals(FILE)) {
                    ZipEntry zipEntry = new ZipEntry(path + file.getName());
                    zos.putNextEntry(zipEntry);
                    try (InputStream fis = new BufferedInputStream(Files.newInputStream(Paths.get(file.getAbsolutePath())))) {
                        byte[] buffer = new byte[1024];
                        int size;
                        while ((size = fis.read(buffer)) != -1) {
                            zos.write(buffer, 0, size);
                        }
                    }
                }
            }
        }
    }
}
