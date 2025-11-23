package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    final static int PORT = 8088;
    final static String DNS = "http://localhost:8088";
    final static Integer TREE_HEIGHT=7;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        Socket socket= serverSocket.accept();

        System.out.println(buildEmojiTree(TREE_HEIGHT));
        System.out.println(DNS);

        BufferedReader input =new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter output =new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        while (!input.ready());

        while (input.ready()){
            System.out.println(input.readLine());
        }

        String redNoseResponse ="<h1>Say Hou hou hou Happy new year</h1>";
        String ответ =  "HTTP/1.1 200 OK\r\n" +
                        "Content-Type: text/html; charset=UTF-8\r\n" +
                        "Content-Length: " + redNoseResponse.getBytes().length + "\r\n" +
                        "Connection: close\r\n" +
                        "\r\n" +
                        redNoseResponse;

        output.write(ответ);
        output.flush();
        socket.close();
    }
    public static String buildEmojiTree(int height) {
        StringBuilder sb = new StringBuilder();

        // крона ёлки
        for (int i = 1; i <= height; i++) {
            sb.append(" ".repeat(height - i));

            for (int k = 0; k < (2 * i - 1); k++) {
                sb.append("🌲");
            }
            sb.append("\n");
        }

        // ствол
        for (int i = 0; i < 2; i++) {
            sb.append(" ".repeat(height - 1)).append("🪵").append("\n");
        }

        return sb.toString();
    }

}
