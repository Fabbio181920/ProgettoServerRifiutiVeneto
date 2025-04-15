import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import javax.swing.*;

public class Main {
    final static String nomeServer = "localhost";
    final static int portaServer = 1050;

    public static JFrame menù;
    public static void main(String[] args) {
        System.out.println("Connessione al server in corso...");
        menù = new JFrame("Menù");
        menù.setSize(1200, 600);
        menù.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE);
        JPanel p = new JPanel();
        p.setBounds(70, 0, 800, 800);
        p.setLayout(new GridLayout(2, 4));
        int op = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                Bottone bottone = new Bottone(op);
                op++;
                p.add(bottone);
            }
        }
        menù.add(p);
        menù.setVisible(true);

    }

}