package lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

    private Scanner scanner;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    private Thread threadIn;
    private Thread threadOut;

    public ChatClient() {
        try {
            Socket socket = new Socket(ConsoleChatConstants.HOST, ConsoleChatConstants.PORT);
            new ConsoleUI(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ChatClient();
    }
}