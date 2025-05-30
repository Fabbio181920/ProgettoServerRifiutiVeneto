import java.io.*;
import java.net.*;

/**
 * La classe Connection estende Thread, il server potrà gestire più connessioni
 */
public class Connection extends Thread{
    private Socket clientSocket;
    private BufferedReader in =null;
    private PrintWriter out=null;
    private GestoreDati dati;

    /**
     * Il costruttore istanzia il socket per permettere la comunicazione con il client
     * @param client
     * @param dati
     */
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

    /**
     * Viene eseguita l'operazione scelta dall'utente
     * (ogni scelta corrisponde ad un codice che a sua volta corrisponde ad un metodo della classe GestoreDati)
     */
    @Override
    public void run(){
        int scelta = 0;
        String str = null;
        try {
            scelta = Integer.parseInt(in.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(scelta);
        try {

            switch(scelta){
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    String rigaStr = in.readLine();
                    int nRiga = Integer.parseInt(rigaStr);
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
        } catch (IOException e) {
            System.out.println("Errore di comunicazione con il client: " + e.getMessage());
        } catch (NumberFormatException e) {
            out.println("Errore: Inserisci un numero valido per la riga!");
        } finally {
            chiudi();
        }
    }

    /**
     * Chiusura del socket una colta terminata la comunicazione
     */
    public void chiudi(){
        System.out.println("Connesione chiusa");
        try {
            clientSocket.close();
            in.close();
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
