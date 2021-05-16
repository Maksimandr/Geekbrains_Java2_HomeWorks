package lesson6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

    public ChatServer(){

        try (ServerSocket serverSocket = new ServerSocket(ConsoleChatConstants.PORT)) {
            System.out.println("Сервер запущен, ожидаем подключения...");
            Socket socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            new ConsoleUI(socket);
        } catch (IOException e) {
            System.out.println("Ошибка инициализации сервера");
        }
    }

    public static void main(String[] args) {
        new ChatServer();
    }
}