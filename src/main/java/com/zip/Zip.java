package com.zip;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private String Directory;

    public void pack(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            addFiles(zip, sources);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<File> seekBy(String ext) {
        List<File> result = new ArrayList<>();
        File directory = new File(Directory);
        Queue<File> files = new LinkedList<>(Arrays.asList(directory.listFiles()));
        while (!files.isEmpty()) {
            File file = files.poll();
            if (file.isDirectory()) {
                for (File myFile : file.listFiles()) {
                    files.offer(myFile);
                }
            } else if (file.getName().contains(ext.substring(1))) {
                result.add(file);
            }
        }
        return result;
    }

    public void addFiles(ZipOutputStream zipOutputStream, List<File> files) {
        for (File file : files) {
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                zipOutputStream.putNextEntry(new ZipEntry(file.getPath()));
                zipOutputStream.write(out.readAllBytes());
                zipOutputStream.closeEntry();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        Args arg = new Args(args);
        zip.Directory = arg.directory();
        List<File> files = zip.seekBy(arg.exclude());
        String target = arg.output();
        zip.pack(files, new File(target));
    }
}
