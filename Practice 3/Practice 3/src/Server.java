import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Server {
    public static final int SERVER_PORT = 50001;
    public static void main(String[] args) {
        ArrayList<Socket> clientList = new ArrayList<>();
        HashMap<Socket, String> clientNameList = new HashMap<>();
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server started!");
            while (true) {
                Socket socket = serverSocket.accept();
                clientList.add(socket);
                ServerThread serverThread = new ServerThread(socket, clientList, clientNameList);
                new Thread(serverThread).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}