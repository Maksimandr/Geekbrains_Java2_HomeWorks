package lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ConsoleUI {

    private String name;
    private Scanner scanner;
    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    private Thread threadIn;
    private Thread threadOut;

    public ConsoleUI(Socket sock, String string) {
        name = string;
        socket = sock;
        scanner = new Scanner(System.in);
        try {
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        threadIn = new Thread(() -> {
            try {
                String mesasgeFromSrv;
                while (true) {
                    mesasgeFromSrv = inputStream.readUTF();
                    System.out.println(mesasgeFromSrv);
                    if (mesasgeFromSrv.equalsIgnoreCase(ConsoleChatConstants.POISON_PILL)) {
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            threadOut.interrupt();
            close();
        });
        threadIn.start();

        threadOut = new Thread(() -> {
            try {
                String messageFromUser = "";
                while (true) {
                    try {
                        messageFromUser = scanner.nextLine();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                        break;
                    }
                    if (!socket.isClosed()) {
                        outputStream.writeUTF(messageFromUser);
                    }
                    if (messageFromUser.equalsIgnoreCase(ConsoleChatConstants.POISON_PILL)) {
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            threadIn.interrupt();
            close();
        });
        threadOut.start();
    }

    private void close() {
        System.out.println(name + " shutting down");
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