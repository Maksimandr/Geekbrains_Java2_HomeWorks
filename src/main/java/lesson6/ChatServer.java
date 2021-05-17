package lesson6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

    public ChatServer() {
        Socket socket;
        try (ServerSocket serverSocket = new ServerSocket(ConsoleChatConstants.PORT)) {
            System.out.println("Server started. Wait for connection...");
            socket = serverSocket.accept();
            System.out.println("Client connected!");
            new ConsoleUI(socket, "Server");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ChatServer();
    }
}