package lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Класс для работы чата c консольным интерфейсом
 */
public class ConsoleUI {

    private String name;
    private Scanner scanner;
    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    private Thread threadIn;
    private Thread threadOut;

    public ConsoleUI(Socket sock, String string) {
        name = string; // клиент или сервер
        socket = sock;
        scanner = new Scanner(System.in);
        try {
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

//        поток для считывания сообщений от сервера
        threadIn = new Thread(() -> {
            try {
                String messageFromSrv;
                while (true) {
                    messageFromSrv = inputStream.readUTF();
                    System.out.println(messageFromSrv);
                    if (messageFromSrv.equalsIgnoreCase(ConsoleChatConstants.POISON_PILL)) {
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            threadOut.interrupt(); // эта штука не работает :(
            closeConnections();
        });
        threadIn.start();

//        поток для считывания сообщений от пользователя из консоли
        threadOut = new Thread(() -> {
            try {
                String messageFromUser;
                while (true) {
                    // на случай если сканер уже закрыт другим потоком
                    try {
                        messageFromUser = scanner.nextLine();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                        break;
                    }
                    outputStream.writeUTF(messageFromUser);
                    if (messageFromUser.equalsIgnoreCase(ConsoleChatConstants.POISON_PILL)) {
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            threadIn.interrupt(); // эта штука не работает :(
            closeConnections();
        });
        threadOut.start();
    }

    private void closeConnections() {
        if (!socket.isClosed()) {
            System.out.println(name + " shutting down");
        }
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