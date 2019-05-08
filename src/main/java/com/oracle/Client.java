package com.oracle;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Client {

    private Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
        start();
    }

    public void start() {
        try (PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner console = new Scanner(System.in)) {
            String serverAnswer;
            String clientAnswer = "Hello oracle";
            do {
                clientAnswer = console.nextLine();
                out.println(clientAnswer);
                while (!(serverAnswer = in.readLine()).isEmpty()) {
                    System.out.println(serverAnswer);
                }
            } while (!clientAnswer.equals("exit"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (Socket socket = new Socket(InetAddress.getByName(null), 2000);) {
            new Client(socket);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}
