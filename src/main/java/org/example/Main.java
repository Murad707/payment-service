package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    final static int PORT = 8088;
    final static int TREE_HEIGHT = 7;

    public static void main(String[] args) throws IOException {

        System.out.println("🎄 Server started on http://localhost:" + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("\n👉 New client connected\n");

                handleRequest(socket);
            }
        }
    }

    private static void handleRequest(Socket socket) {
        try (
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
        ) {

            // читаем HTTP
            String line;
            while ((line = input.readLine()) != null && !line.isEmpty()) {
                System.out.println(line);
            }

            // HTML-ответ
            String body = """
                    <html>
                        <head>
                            <title>New Year Server</title>
                            <style>
                                body { font-family: Arial; background: #222; color: #fff; text-align: center; }
                                pre { font-size: 22px; }
                            </style>
                        </head>
                        <body>
                            <h1>Say Hou hou hou 🎅 Happy New Year!</h1>
                            <pre>%s</pre>
                        </body>
                    </html>
                    """.formatted(buildEmojiTree(TREE_HEIGHT));

            String httpResponse =
                    "HTTP/1.1 200 OK\r\n" +
                            "Content-Type: text/html; charset=UTF-8\r\n" +
                            "Content-Length: " + body.getBytes().length + "\r\n" +
                            "Connection: close\r\n" +
                            "\r\n" +
                            body;

            output.write(httpResponse);
            output.flush();

        } catch (Exception e) {
            System.err.println("❌ Error handling client: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException ignored) {}
        }
    }

    public static String buildEmojiTree(int height) {
        StringBuilder sb = new StringBuilder();
        String E = "🌲 ";   // emoji + пробел → одинаковая ширина

        for (int i = 1; i <= height; i++) {
            sb.append(" ".repeat(height - i));
            sb.append(E.repeat(2 * i - 1));
            sb.append("\n");
        }

        for (int i = 0; i < 2; i++) {
            sb.append(" ".repeat(height - 1)).append("🪵").append("\n");
        }

        return sb.toString();
    }
}
