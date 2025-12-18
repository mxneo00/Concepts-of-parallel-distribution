import java.io.*;
import java.net.*;

public class FTPClient {
    public static void main(String[] args) throws Exception{
        if (args.length != 2) {
            System.out.println("Usage: java FTPClient <server> <username> <password>");
            System.exit(1);
        }

        String ipAddress = args[0];
        int portNumber = Integer.parseInt(args[1]);

        Socket clientSocket = new Socket(ipAddress, portNumber);
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        String line;
        while((line = inFromUser.readLine()) != null) {
            if (line.equals("Closing connection.")) {
                break;
            }
            System.out.println("Sent: " + line);
        }

        System.out.println("Connection Closed.");
        clientSocket.close();
    }
}
