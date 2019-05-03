package com.chat;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.LoggerFactory;

import java.io.*;

import java.util.logging.*;

import static java.util.stream.Collectors.joining;

public class Chatroom {

    private static final Logger logger = Logger.getLogger(Chatroom.class.getName());

    public void circle() throws IOException {
        BufferedReader answer = new BufferedReader(new InputStreamReader(new FileInputStream("myDoc.txt")));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] answers = answer.lines().collect(joining()).split(" ");
        boolean speak = true;
        String talk = null;
        System.out.println("Начинаем разговор: ");
        while (!(talk = reader.readLine()).equals("закончить")) {
            logger.log(Level.INFO, talk);
            if (talk.equals("стоп"))
                speak = false;
            if (talk.equals("продолжить"))
                speak = true;
            if (speak) {
                String random = answers[(int) (Math.random() * (answers.length - 1))];
                logger.log(Level.INFO, random);
                System.out.println(random);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        FileHandler fh = new FileHandler("mylog.txt");
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
        logger.setUseParentHandlers(false);
        new Chatroom().circle();
    }
}
