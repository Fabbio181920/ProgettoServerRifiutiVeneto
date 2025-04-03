import java.io.*;
import java.net.*;

public class Connection extends Thread{
    private Socket clientSocket;
    private BufferedReader in =null;
    private PrintWriter out=null;
    private GestoreDati dati;

    public Connection(Socket client, GestoreDati dati){
        this.dati= dati;
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
        int scelta = 0;
        String str = null;
        try {
            scelta = Integer.parseInt(in.readLine());
            switch(scelta){
                case 0:
                    chiudi();
                    System.exit(0);
                    break;
                case 1:
                    int nRiga = Integer.parseInt(in.readLine());
                    out.println(dati.getRiga(nRiga));
                    break;
                case 2:
                    int anno = Integer.parseInt(in.readLine());
                    out.println(dati.provinciaRifiutiAnno(anno));
                    break;
                case 3:
                    out.println(dati.rifiutiProdotti());
                    break;
                case 4:
                    anno = Integer.parseInt(in.readLine());
                    out.println(dati.rifiutiProdottiAnno(anno));
                    break;
                case 5:
                    String provincia = in.readLine();
                    if(provincia.equalsIgnoreCase("venezia")){
                        provincia = "citta' metropolitana di venezia";
                    }
                    out.println(dati.rifiutiProdottiProvincia(provincia));
                    break;
                case 6:
                    out.println(dati.annoRifiuti());
                    break;
                case 7:
                    out.println(dati.provinciaRifiuti());
                    break;
                default:
                    out.println("Scelta non valida");
                    break;
                }
                chiudi();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }catch (NumberFormatException e){
           out.println("input non valido");
           chiudi();
        }
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
