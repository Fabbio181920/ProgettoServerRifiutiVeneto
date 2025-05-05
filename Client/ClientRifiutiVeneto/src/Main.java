import java.awt.*;
import javax.swing.*;

/**
 * Creazione della schermata iniziale con il menù
 */
public class Main {
    final static String nomeServer = "localhost";
    final static int portaServer = 1050;
    public static String [] titoli = {"Spegni client e server", "Riga del file", "Provincia che ha prodotto più rifiuti in un anno", "Quantità di rifiuti prodotti per tipo dalla regione", "Quantità di rifiuti prodotti per tipo in un anno", "Quantità di rifiuti prodotti per tipo da una provincia", "Anno in cui sono stati prodotti più rifiuti", "Provincia che ha prodotto più rifiuti" };
    public static String[] opzioni = {"Spegni client e server", "Riga del file",
            "<html><div style='text-align: center;'>Provincia che ha prodotto <br>più rifiuti in un anno</div></html>",
            "<html><div style='text-align: center;'>Quantità di rifiuti prodotti <br>per tipo dalla regione</div></html>",
            "<html><div style='text-align: center;'>Quantità di rifiuti prodotti <br>per tipo in un anno</div></html>",
            "<html><div style='text-align: center;'>Quantità di rifiuti prodotti <br>per tipo da una provincia</div></html>",
            "<html><div style='text-align: center;'>Anno in cui sono stati <br>prodotti più rifiuti</div></html>",
            "<html><div style='text-align: center;'>Provincia che ha <br>prodotto più rifiuti</div></html>"};

    public static JFrame menù;
    public static void main(String[] args) {
        System.out.println("Connessione al server in corso...");
        menù = new JFrame("Menù");
        menù.setSize(1000, 400);
        menù.setIconImage(new ImageIcon("Regione_Veneto.png").getImage());
        menù.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE);
        menù.setLayout(new BorderLayout());
        JLabel descrizione = new JLabel("Selezionare l'opzione", SwingConstants.CENTER);
        descrizione.setFont(new Font("Arial", Font.BOLD, 18));
        descrizione.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        menù.add(descrizione, BorderLayout.NORTH);


        JPanel p = new JPanel();
        p.setBounds(70, 0, 800, 800);
        p.setLayout(new GridLayout(2, 4));
        int op = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                Bottone bottone = new Bottone(op);
                bottone.setBackground(Color.WHITE);
                op++;
                p.add(bottone);
            }
        }
        menù.add(p, BorderLayout.CENTER);
        menù.setVisible(true);

    }

}