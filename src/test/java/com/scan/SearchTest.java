package com.scan;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class SearchTest {

    @Test
    public void searchFiles() throws IOException {
        Path folder = Files.createTempDirectory("folder");
        Path nextFolder = Files.createTempDirectory(folder, "nextFolder");
        File block = File.createTempFile("block", ".txt", nextFolder.toFile());
        File world1 = File.createTempFile("world1", ".docx", nextFolder.toFile());
        File world2 = File.createTempFile("world2", ".docx", folder.toFile());
        Search search = new Search();
        List<String> ex = Arrays.asList("docx");
        List<File> files = search.files(folder.toFile().getAbsolutePath(), ex);
        Assert.assertThat(files.size(), is(2));
        List<String> exTwo = Arrays.asList("docx");
        List<File> filesSec = search.files(folder.toFile().getAbsolutePath(), exTwo);
        Assert.assertThat(files.size(), is(2));

    }
}
