import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 6001);
            System.out.println("Connected to server");

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.print("Enter message: ");
                String message = reader.readLine();
                out.writeUTF(message);

                String serverResponse = in.readUTF();
                System.out.println("Server response: " + serverResponse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}