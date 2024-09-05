import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6001);
            System.out.println("Server started on port 6001");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");

                new ServerThread(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ServerThread extends Thread {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public ServerThread(Socket socket) {
        this.socket = socket;
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                String message = in.readUTF();
                System.out.println("Received message from client: " + message);

                // Broadcast message to all connected clients (implement later)

                out.writeUTF("Server received: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}