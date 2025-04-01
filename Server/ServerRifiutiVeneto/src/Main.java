import java.io.*;
import java.net.*;

public class Main {

    public static final int PORT = 1050; // porta al di fuori del range 1-1024 !

    /**
     * @param args the command line arguments
     * @throws IOException
     */

    public static void main(String[] args) throws IOException {
        try (  ServerSocket serverSocket = new ServerSocket(PORT)) {
            Socket clientSocket=null;
            while(true) {
                System.out.println("Server Socket: " + serverSocket);
                clientSocket = serverSocket.accept();
                Connection connessione = new Connection(clientSocket);
                connessione.start();
            }
        }
    }


}