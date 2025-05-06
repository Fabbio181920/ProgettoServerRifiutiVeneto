
import java.awt.*;
import javax.swing.*;

/**
 * Creazione della schermata iniziale con il menù
 */
public class Main {
    public static String nomeServer = "localhost";
    final static int portaServer = 1050;
    public static String [] titoli = {"Spegni client e server", "Riga del file", "Provincia che ha prodotto più rifiuti in un anno", "Quantità di rifiuti prodotti per tipo dalla regione", "Quantità di rifiuti prodotti per tipo in un anno", "Quantità di rifiuti prodotti per tipo da una provincia", "Anno in cui sono stati prodotti più rifiuti", "Provincia che ha prodotto più rifiuti" };
    public static String[] opzioni = {"Spegni client e server", "Riga del file",
            "<html><div style='text-align: center;'>Provincia che ha prodotto <br>più rifiuti in un anno</div></html>",
            "<html><div style='text-align: center;'>Quantità di rifiuti prodotti <br>per tipo dalla regione</div></html>",
            "<html><div style='text-align: center;'>Quantità di rifiuti prodotti <br>per tipo in un anno</div></html>",
            "<html><div style='text-align: center;'>Quantità di rifiuti prodotti <br>per tipo da una provincia</div></html>",
            "<html><div style='text-align: center;'>Anno in cui sono stati <br>prodotti più rifiuti</div></html>",
            "<html><div style='text-align: center;'>Provincia che ha <br>prodotto più rifiuti</div></html>"};

    public static JFrame frame;
    public static JPanel content = new JPanel();
    public static JPanel menù = new JPanel();
    public static void main(String[] args) {
        System.out.println("Connessione al server in corso...");
        frame = new JFrame("Menù");
        frame.setSize(800, 300);
        frame.setIconImage(new ImageIcon("Regione_Veneto.png").getImage());
        frame.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.add(menù);
        menù.setLayout(new BorderLayout());
        JLabel descrizione = new JLabel("Selezionare l'opzione", SwingConstants.CENTER);
        descrizione.setFont(new Font("Arial", Font.BOLD, 18));
        descrizione.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        menù.add(descrizione, BorderLayout.NORTH);
        JLabel modificaIp =new JLabel("Inserisci un nuovo indirizzo IP (opzionale): ");
        modificaIp.setFont(new Font("Arial", Font.BOLD, 18));
        JTextField insIp = new JTextField(10);
        insIp.setFont(new Font("Arial", Font.BOLD, 14));
        insIp.setForeground(Color.BLACK);
        insIp.setMargin(new Insets(5, 10, 8, 100));
        JButton button = new JButton("Invia");
        button.addActionListener(e -> {
            String ip = insIp.getText();
            if(verIp(ip)){
                nomeServer = ip;
            }
            System.out.println(nomeServer
            );
        });
        modificaIp.setVisible(true);
        insIp.setVisible(true);
        button.setVisible(true);
        content.add(modificaIp, BorderLayout.SOUTH);
        content.add(insIp,BorderLayout.SOUTH);
        content.add(button);
        frame.add(content);


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
        frame.setVisible(true);

    }

    public static boolean verIp(String ip){
        int count = 0;
        for(int i = 0; i < ip.length(); i++){
            if(ip.substring(i, i+1).equalsIgnoreCase(".")){
                count ++;
            }
        }
        if(count == 3){
            return true;
        }
        return false;
    }

}