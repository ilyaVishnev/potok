package com.oracle;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.stream.Collectors;

public class Server {

    private Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
        start();
    }

    public void start() {
        try (PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
            String clientQuestion = null;
            do {
                System.out.println("wait command ...");
                clientQuestion = in.readLine();
                if ("Hello oracle".equals(clientQuestion)) {
                    out.println("Hello, dear friend, I'm a oracle.");
                    out.println();
                } else {
                    out.println(getAnswer(clientQuestion));
                    out.println();
                }
            } while (!"exit".equals(clientQuestion));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getAnswer(String question) {
        try (BufferedReader fromDoc = new BufferedReader(new FileReader("myAnswers.txt"))) {
            StringJoiner joiner = new StringJoiner(System.lineSeparator());
            fromDoc.lines().forEach(joiner::add);
            String[] answers = joiner.toString().split("#");
            for (int index = 1; index < answers.length - 1; index++) {
                if (answers[index].equals(question)) {
                    String answer = answers[++index];
                    return answer.substring(2, answer.length() - 4).replace("\r\n", "\n");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "exit".equals(question) ? question : "sorry, no answer";
    }

    public static void main(String[] args) throws IOException {
        try (Socket socket = new ServerSocket(2000).accept()) {
            new Server(socket);
        }
    }
}
