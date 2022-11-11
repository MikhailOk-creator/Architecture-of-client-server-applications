import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String name = "";
        String reply = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ваше имя: ");
        name = scanner.nextLine();
        try (Socket socket = new Socket("localhost", 8080)) {
            System.out.println("Вы подключены, можете начинать вводить сообщения");
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            ClientThread clientThread = new ClientThread(socket);
            new Thread(clientThread).start();
            do {
                String message = (name + ": ");
                reply = scanner.nextLine();
                out.println(message + reply);
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}