package lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ConsoleUI {

    private Scanner scanner;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    private Thread threadIn;
    private Thread threadOut;

    public ConsoleUI(Socket socket) {
        scanner = new Scanner(System.in);
        try {
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        threadIn = new Thread(() -> {
            try {
                while (true) {
                    String string = inputStream.readUTF();
                    System.out.println(string);
                    if (string.equalsIgnoreCase(ConsoleChatConstants.POISON_PILL)) {
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            close(socket);
            Thread.currentThread().interrupt();
        });
        threadIn.start();

        threadOut = new Thread(() -> {
            try {
                while (!socket.isClosed()) {
                    String messageFromUser = scanner.nextLine();
                    outputStream.writeUTF(messageFromUser);
                    if (messageFromUser.equalsIgnoreCase(ConsoleChatConstants.POISON_PILL)) {
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            threadIn.interrupt();
            try {
                threadIn.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            close(socket);
            Thread.currentThread().interrupt();
        });
        threadOut.start();
    }

    private void close(Socket socket) {
        scanner.close();
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}