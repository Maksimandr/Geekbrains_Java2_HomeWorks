package lesson6;

import java.io.IOException;
import java.net.Socket;

public class ChatClient {

    public ChatClient() {
        try {
            Socket socket = new Socket(ConsoleChatConstants.HOST, ConsoleChatConstants.PORT);
            new ConsoleUI(socket, "Client");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ChatClient();
    }
}