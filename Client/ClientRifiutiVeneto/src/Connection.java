import java.io.*;
import java.net.*;

public class Connection extends Thread {
    private Socket clientSocket;
    private BufferedReader in = null;
    private PrintWriter out = null;


    public Connection(Socket client) {

        this.clientSocket = client;
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(clientSocket.getInputStream());
            OutputStreamWriter osw = new OutputStreamWriter(clientSocket.getOutputStream());
            BufferedWriter bw = new BufferedWriter(osw);
            out = new PrintWriter(bw, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        in = new BufferedReader(isr);

    }

    @Override
    public void run(){

    }


    public void chiudi(){
        try {
            clientSocket.close();
            in.close();
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
