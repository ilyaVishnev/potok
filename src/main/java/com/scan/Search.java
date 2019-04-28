package com.scan;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Search {


    List<File> files(String parent, List<String> exts) {
        List<File> result = new ArrayList<>();
        File parentFile = new File(parent);
        Queue<File> files = new LinkedList<>(Arrays.asList(parentFile.listFiles()));
        while (!files.isEmpty()) {
            File file = files.poll();
            if (file.isDirectory()) {
                for (File myFile : file.listFiles()) {
                    files.offer(myFile);
                }
            } else if (hasExtention(file.getName(), exts)) {
                result.add(file);
            }
        }
        return result;
    }

    public boolean hasExtention(String extention, List<String> exts) {
        for (String ex : exts) {
            if (extention.contains(ex))
                return true;
        }
        return false;
    }
}
