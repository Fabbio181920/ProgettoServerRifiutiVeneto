import java.io.*;
import java.net.*;

public class Connection extends Thread{
    private Socket clientSocket;
    private BufferedReader in =null;
    private PrintWriter out=null;
    public Connection(Socket client){
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
        while (true) {
            String str = null;
            try {
                str = in.readLine();
                if(str.equals("STOP")){
                    out.close();
                    try {
                        in.close();
                        clientSocket.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.exit(0);
                }
                System.out.println("Echoing: " + str.toUpperCase());
                out.println(str.toUpperCase());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (str.equals("END")) break;

        }
        System.out.println("EchoServer: closing...");
        out.close();
        try {
            in.close();
            clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
