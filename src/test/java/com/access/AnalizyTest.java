package com.access;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class AnalizyTest {

    @Test
    public void checkSleeping() {
        Analizy analizy = new Analizy();
        analizy.unavailable("unavailable.csv", "test.csv");
        Assert.assertThat(checkTarget(), is(true));
    }

    public boolean checkTarget() {
        try (BufferedReader reader = new BufferedReader(new FileReader("target.csv"));
             BufferedReader readerTwo = new BufferedReader(new FileReader("test.csv"))) {
            String lineOne;
            String lineTwo;
            while ((lineOne = reader.readLine()) != null && (lineTwo = readerTwo.readLine()) != null) {
                if (!lineOne.equals(lineTwo))
                    return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }
}
