package com.access;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            String line;
            String sleep = "no";
            while ((line = reader.readLine()) != null) {
                String[] lineArray = line.split(" ");
                String type = lineArray[0];
                String time = lineArray[1];
                if ((type.equals("400") || type.equals("500")) && sleep.equals("no")) {
                    out.print(time);
                    sleep = "yes";
                } else if (!type.equals("400") && !type.equals("500") && sleep.equals("yes")) {
                    out.println("; " + time);
                    sleep = "no";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
 /*       try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        Analizy analizy = new Analizy();
        analizy.unavailable("unavailable.csv", "target.csv");
    }
}