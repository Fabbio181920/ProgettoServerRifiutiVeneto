import java.io.*;
import java.net.*;

public class Main {

    public static final int PORT = 1050; // porta al di fuori del range 1-1024 !

    /**
     * Il main inizializza la classe per gestire i dati letti dal file e
     * attende che avvenga una connessione tra client e server in modo da poter creare il Socket
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        GestoreDati dati = new GestoreDati();
        try (  ServerSocket serverSocket = new ServerSocket(PORT)) {
            Socket clientSocket=null;
            while(true) {
                System.out.println("Server Socket: " + serverSocket);
                clientSocket = serverSocket.accept();
                Connection connessione = new Connection(clientSocket, dati);
                connessione.start();
            }
        }catch (IOException e){
            System.out.println("Errore");
        }
    }


}