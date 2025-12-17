
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import static Signature.Message.changeMessage;

public class TelephoneGame {
    public static void main(String[] args) throws Exception {
        char role = args[0].charAt(0);
        int portListen = Integer.parseInt(args[1]);

        String nextIP = null;
        int nextPort = -1;

        if (role != 'E') {
            nextIP = args[2];
            nextPort = Integer.parseInt(args[3]);
        }

        String message;

        if (role == 'A') {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.print("Enter a message: ");
                message = reader.readLine();
                if (message.split(" ").length >= 8 && !message.isEmpty()) {
                    break;
                }
                System.out.println("Message cannot be empty and must be at least 8 words. Please try again.");
            }
            System.out.println("Starting the game with message: " + message);
        } else {
            ServerSocket serverSocket = new ServerSocket(portListen);
            Socket clientSocket = serverSocket.accept();
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            message = input.readLine();
            System.out.println("Received message from computer " + role + " : " + message);
            clientSocket.close();
            serverSocket.close();
        }

        String newMessage = changeMessage(message);

        if (role != 'E') {
            System.out.println("Sending modified message to computer " + (char)(role+1) + " : " + newMessage);
            Socket socket = new Socket(nextIP, nextPort);
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            output.writeBytes(newMessage + "\n");
            socket.close();
        } else {
            System.out.println("Final message at computer E: " + newMessage);
        }
    }
}