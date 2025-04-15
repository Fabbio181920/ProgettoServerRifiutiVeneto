import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import javax.swing.*;

public class Main {
    final static String nomeServer = "localhost";
    final static int portaServer = 1050;
    public static void main(String[] args) {
        System.out.println("Connessione al server in corso...");
        JFrame menù = new JFrame("Menù");
        menù.setSize(1200, 600);
        menù.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE);
        JPanel p = new JPanel();
        p.setBounds(70, 0, 800, 800);
        p.setLayout(new GridLayout(2, 4));
        String[][] opzioni = {
                {"Spegni server", "Riga del file", "Provincia che ha prodotto \npiù rifiuti in un determinato anno", "Quantità di rifiuti prodotti \nper tipo dalla regione"},
                {"Quantità di rifiuti prodotti \nper tipo in un anno", "Quantità di rifiuti prodotti \nper tipo da una provincia", "Anno in cui sono stati \nprodotti più rifiuti", "Provincia che ha \nprodotto più rifiuti"}
        };

        for (int k = 0; k < 2; k++) {
            for (int j = 0; j < 4; j++) {
                Bottone bottone = new Bottone(k+j);
                p.add(bottone);
            }
        }
        menù.add(p);

//        jT = new JTextField();
//        jT.setBounds(100, alt - 130, 200, 40);
//        f.add(jT);
//
//        f.setVisible(true);
        menù.setVisible(true);
//        try (Socket sck = new Socket(nomeServer, portaServer)) {
//            String rem = sck.getRemoteSocketAddress().toString();
//            String loc = sck.getLocalSocketAddress().toString();
//            System.out.format("Server (remoto): %s%n", rem);
//            System.out.format("Client (client): %s%n", loc);
//
//        } catch (UnknownHostException e) {
//            System.err.format("Nome di server non valido: %s%n", e.getMessage());
//        } catch (IOException e) {
//            System.err.format("Errore durante la comunicazione con il server: %s%n",
//                    e.getMessage());
//
//        }
    }

}