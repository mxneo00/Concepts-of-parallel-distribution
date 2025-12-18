import java.io.*;
import java.net.*;

public class FTPServer {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: java FTPServer <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);
        ServerSocket serverSocket = new ServerSocket(portNumber);
        System.out.println("Server Started.");

        Socket clientSocket = serverSocket.accept();
        System.out.println("Client Connected.");

        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter outToClient = new PrintWriter(clientSocket.getOutputStream());

        String line;
        while ((line = inFromClient.readLine()) != null) {
            outToClient.println("Recieved: " + line + '\n');
        }

        outToClient.println("Closing connection.");
        System.out.println("Connection Closed.");
        clientSocket.close();
        serverSocket.close();
    }  
}
