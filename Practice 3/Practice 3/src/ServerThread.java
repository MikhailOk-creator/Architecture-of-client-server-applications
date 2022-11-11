import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ServerThread extends Thread {
    private final Socket socket;
    private final ArrayList<Socket> clientList;
    private final HashMap<Socket, String> clientNameList;
    private final ConcurrentHashMap<Socket, ArrayList<String>> messageList = new ConcurrentHashMap<>();

    public ServerThread(Socket socket, ArrayList<Socket> clientList, HashMap<Socket, String> clientNameList) {
        this.socket = socket;
        this.clientList = clientList;
        this.clientNameList = clientNameList;
    }

    @Override
    public void run() {
        class MessageRunnable implements Runnable {
            private final ConcurrentHashMap<Socket, ArrayList<String>> messageList;
            public MessageRunnable(ConcurrentHashMap<Socket, ArrayList<String>> messageList) {
                this.messageList = messageList;
            }

            @Override
            public void run() {
                try {
                    for (Map.Entry<Socket, ArrayList<String>> entry : messageList.entrySet()) {
                        for (String str : entry.getValue()) {
                            Socket socket1 = entry.getKey();
                            showMessageToAllClients(socket1, str);
                        }
                        messageList.remove(socket);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            private void showMessageToAllClients(Socket socket, String message) {
                Socket socketBuffer;
                PrintWriter printWriter;
                int i = 0;
                while (i < clientList.size()) {
                    socketBuffer = clientList.get(i++);
                    try {
                        if (socketBuffer != socket) {
                            printWriter = new PrintWriter(socketBuffer.getOutputStream(), true);
                            printWriter.println(message);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        Runnable sendMessage = new MessageRunnable(messageList);
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(sendMessage, 0, 10, TimeUnit.SECONDS);

        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                String message = input.readLine();
                if (!clientNameList.containsKey(socket)) {
                    String[] str = message.split(": " , 2);
                    clientNameList.put(socket, str[0]);
                }
                if (!messageList.containsKey(socket)) {
                    messageList.put(socket, new ArrayList<>());
                }
                messageList.get(socket).add(message);
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
