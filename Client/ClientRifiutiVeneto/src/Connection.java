import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

/**
 * La classe Connection estende Thread permettendo così all'utente di chiedere più informazioni al server contemporaneamente
 */
public class Connection extends Thread {

    private Socket clientSocket;
    private JFrame frame;
    private JPanel panel;
    private JTextField textField;
    private JButton button;
    private JLabel label;
    private int operazione;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private boolean cliccato = false;


    /**
     * Il metodo cotruttore crea la finestra con cui l'utente potrà interagire per fare le richieste al server
     * @param client
     * @param operazione
     */
    public Connection(Socket client, int operazione) {
        this.operazione = operazione;
        this.clientSocket = client;
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(clientSocket.getInputStream());
            OutputStreamWriter osw = new OutputStreamWriter(clientSocket.getOutputStream());
            BufferedWriter bw = new BufferedWriter(osw);
            in = new BufferedReader(isr);
            out = new PrintWriter(bw, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        frame = new JFrame();
        frame.setTitle(Main.titoli[operazione]);
        frame.setIconImage(new ImageIcon("Regione_Veneto.png").getImage());
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(1000, 200);
        frame.setLayout(new BorderLayout(10, 10));
        frame.setLocationRelativeTo(null);
        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        label = new JLabel("");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(label);
        frame.add(panel);
        button = new JButton("Invia");

    }

    /**
     * Nel metodo run viene inviata l'opzione scelta dall'utente e vengono mostrati i dati
     */
    @Override
    public void run(){
        System.out.println(operazione);
        out.println(operazione);
        try{
            switch(operazione){
                case 0:
                    System.exit(0);
                case 1:
                    label.setText("Inserisci la riga (1-154):");
                    textField = createJTextField();
                    panel.add(textField);
                    panel.add(button);
                    button.addActionListener(e -> {
                        if (!cliccato) {
                            inserimento();
                            cliccato = true;
                            textField.setVisible(false);
                            button.setVisible(false);
                            chiudi();
                        }
                    });
                    frame.setVisible(true);
                    break;
                case 2, 4:
                    label.setText("Seleziona l'anno:");

                    Integer[] anni = new Integer[11];
                    for (int i = 0; i <= 10; i++) {
                        anni[i] = 2000 + i;
                    }
                    JComboBox<Integer> comboBox = new JComboBox<>(anni);
                    comboBox.setFont(new Font("Arial", Font.BOLD, 14));
                    comboBox.setBackground(Color.WHITE);
                    comboBox.setForeground(Color.DARK_GRAY);
                    comboBox.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
                    comboBox.setFocusable(false);
                    comboBox.setVisible(true);
                    panel.add(comboBox);
                    panel.add(button);

                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (!cliccato) {
                                int annoSelezionato = (int) comboBox.getSelectedItem();
                                out.println(annoSelezionato);
                                try {
                                    label.setText(in.readLine());
                                } catch (IOException ex) {
                                    label.setText("Errore di comunicazione col server.");
                                }
                                cliccato = true;
                                comboBox.setVisible(false);
                                button.setVisible(false);
                                chiudi();
                            }
                        }
                    });
                    frame.setVisible(true);
                    break;
                case 3, 6, 7:
                    try {
                        label.setText(in.readLine());
                    } catch (IOException e) {
                        label.setText("Errore");
                    }
                    frame.setVisible(true);
                    break;
                case 5:
                    label.setText("Seleziona la provincia:");
                    String [] sProvincie = {"Belluno","Padova","Rovigo","Treviso","Venezia","Verona","Vicenza"};
                    JComboBox<String> provincie= new JComboBox<>(sProvincie);
                    provincie.setFont(new Font("Arial", Font.BOLD, 14));
                    provincie.setBackground(Color.WHITE);
                    provincie.setForeground(Color.DARK_GRAY);
                    provincie.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
                    provincie.setFocusable(false);
                    provincie.setVisible(true);
                    panel.add(provincie);
                    panel.add(button);

                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (!cliccato) {
                                String provinciaSel = (String) provincie.getSelectedItem();
                                if(provinciaSel.equalsIgnoreCase("Venezia")){
                                    out.println("citta' metropolitana di venezia");
                                }else{
                                    out.println(provinciaSel);
                                }
                                try {
                                    label.setText(in.readLine());
                                } catch (IOException ex) {
                                    label.setText("Errore di comunicazione col server.");
                                }
                                cliccato = true;
                                provincie.setVisible(false);
                                button.setVisible(false);
                                chiudi();
                            }
                        }
                    });
                    frame.setVisible(true);
                    break;
                default:
                    label.setText("errore");
                    break;
            }
        }catch (NumberFormatException ex) {
            label.setText("Inserisci un numero valido!");
        }

    }

    /**
     * Questo metodo permette di inviare i dati letti tramite interfaccia grafica e mostrare a schermo i dati ricevuti dal server
     */
    public void inserimento (){
        try {
            out.println(textField.getText());
            JLabel aLabel = label;
            aLabel.setText(in.readLine());
            panel.add(aLabel);
        } catch (IOException ex) {
            label.setText("Errore di comunicazione col server.");
        }
        chiudi();
    }

    /**
     * Tramite questo metodo creo una JTextField con font corretto
     * @return textField
     */
    public JTextField createJTextField() {
        JTextField textField = new JTextField(10);

        textField.setFont(new Font("Arial", Font.BOLD, 15));
        textField.setForeground(Color.BLACK);
        textField.setMargin(new Insets(5, 10, 5, 10));
        return textField;
    }

    /**
     * Chiusura del socket una volta terminata la comunicazione
     */
    public void chiudi(){
        try {
            clientSocket.close();
            in.close();
            out.close();
        } catch (IOException e) {
            System.out.println("Errore di comunicazione");
        }
        System.out.println("Connessione chiusa");
    }

}
