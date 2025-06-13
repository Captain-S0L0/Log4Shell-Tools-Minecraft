package xyz.terriblefriends.log4shelltools;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HTTPServer {
    private static final int MAX_THREADS = 10;
    private static final int DEFAULT_LISTEN_PORT = 8000;

    public static void main(String[] args) {
        int port = DEFAULT_LISTEN_PORT;

        if (args.length >= 1) {
            try {
                port = Integer.parseInt(args[0]);
            }
            catch (NumberFormatException e) {
                System.err.println("Failed to parse port! Using default of " + port);
            }
        }

        ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREADS);
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("HTTP server listening on 0.0.0.0:" + port);
            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    executorService.submit(() -> handleConnection(clientSocket));
                }
                catch (IOException e) {
                    System.err.println("Failed to open connection!");
                    e.printStackTrace();
                }
            }

        }
        catch (IOException e) {
            System.err.println("Failed to open server socket!");
            e.printStackTrace();
        }
    }

    private static void handleConnection(Socket socket) {
        try (Socket clientSocket = socket){
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            String request = in.readLine();
            System.out.println("Request was: " + request);

            HashMap<String, String> propertyMap = new HashMap<>();
            String line;
            while ((line = in.readLine()) != null && !line.isEmpty()) {
                int separator = line.indexOf(':');
                if (separator == -1) {
                    continue;
                }
                propertyMap.put(line.substring(0, separator), line.substring(separator + 2));
            }

            System.out.println("Headers: " + propertyMap);

            String requestedName = request.split(" ")[1].substring(1);

            System.out.println("Fetching " + requestedName);

            File requestedFile = new File(".", requestedName);
            if (!requestedFile.getParentFile().equals(new File("."))) {
                System.out.println("Illegal file");
                String content = "You are not allowed to access this page.";
                out.write("HTTP/1.0 403 FORBIDDEN\r\n");
                out.write("Content-Type: text/html\r\n");
                out.write("Content-Length: " + content.length() + "\r\n");
                out.write("\r\n");
                out.write(content);
            }
            else if (!requestedFile.exists() || !requestedFile.isFile()) {
                System.out.println("File does not exist");
                String content = "File not found.";
                out.write("HTTP/1.0 404 NOT FOUND\r\n");
                out.write("Content-Type: text/html\r\n");
                out.write("Content-Length: " + content.length() + "\r\n");
                out.write("\r\n");
                out.write(content);
            }
            else {
                System.out.println("Sending file");
                byte[] response = Files.readAllBytes(requestedFile.toPath());

                out.write("HTTP/1.0 200 OK\r\n");
                out.write("Content-Type: application/octet-stream\r\n");
                out.write("Content-Length:" + response.length + "\r\n");
                out.write("\r\n");
                out.flush();
                clientSocket.getOutputStream().write(response, 0, response.length);
            }
            out.flush();
        }
        catch (IOException e) {
            System.err.println("Failed to handle connection!");
            e.printStackTrace();
        }
    }
}
