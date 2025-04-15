import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

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
        frame.setTitle("Operazione "+operazione);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLayout(new BorderLayout(10, 10));
        frame.setLocationRelativeTo(null);
        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        label = new JLabel("");
        label.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(label);
        textField = createJTextField();
        panel.add(textField);
        frame.add(panel);
        frame.setVisible(true);
    }

    @Override
    public void run(){
        System.out.println(operazione);
        out.println(operazione);

        switch(operazione){
            case 0:
                System.exit(0);
            case 1:
                label.setText("Inserisci la riga");
                button = new JButton("invia");

                button.addActionListener(e -> {
                    String input = textField.getText();
                    System.out.println(input);

                    try {
                        int nRiga = Integer.parseInt(input);
                        out.println(nRiga);
                        String risposta = in.readLine();
                        label.setText(risposta);
                    } catch (NumberFormatException ex) {
                        label.setText("Inserisci un numero valido!");
                    } catch (IOException ex) {
                        label.setText("Errore di comunicazione col server.");
                        throw new RuntimeException(ex);
                    }
                });

                panel.add(button);
                break;
            case 2,4:
                textField = createJTextField();
                panel.add(textField);
                frame.add(panel);
                frame.setVisible(true);
                try {
                    label.setText(in.readLine());
                } catch (IOException e) {
                    label.setText("errore");
                }
                break;
            case 3, 7, 6:
                try {
                    label.setText(in.readLine());

                } catch (IOException e) {
                    label.setText("errore");
                }

                break;
            case 5:
                textField = createJTextField();
                panel.add(textField);
                try {
                    label.setText(in.readLine());
                } catch (IOException e) {
                    label.setText("errore");
                }
                break;
            default:
                label.setText("errore");
                break;
        }
        //chiudi();
    }


    public JTextField createJTextField() {
        JTextField textField = new JTextField(10);

        textField.setFont(new Font("Arial", Font.BOLD, 15));
        textField.setForeground(Color.BLACK);
        textField.setMargin(new Insets(5, 10, 5, 10));
        return textField;
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
