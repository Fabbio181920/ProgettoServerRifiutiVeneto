import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

public class Connection extends Thread {

    private Socket clientSocket;

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
            out = new PrintWriter(bw, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        in = new BufferedReader(isr);

    }

    @Override
    public void run(){
        JFrame frame = new JFrame();
        frame.setTitle("Operazione "+operazione);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLayout(new BorderLayout(10, 10));
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel label = new JLabel("");
        label.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(label);
        JTextField textField;
        switch(operazione){
            case 0:
                System.exit(0);
            case 1:
                textField = createJTextField("Inserisci riga");
                panel.add(textField);
                try {
                    label.setText(in.readLine());
                } catch (IOException e) {
                    label.setText("errore");
                }
                break;
            case 2,4:
                textField = createJTextField("Inserisci anno");
                panel.add(textField);
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
                textField = createJTextField("Inserisci la provincia");
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
        chiudi();
    }


    public JTextField createJTextField(String tipoInserimento) {
        JTextField textField = new JTextField(10);

        textField.setFont(new Font("Arial", Font.BOLD, 15));
        textField.setForeground(Color.BLACK);
        textField.setToolTipText(tipoInserimento);
        textField.setMargin(new Insets(5, 10, 5, 10));
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = textField.getText();
                if(s.equalsIgnoreCase("venezia")){
                    out.println("citta' metropolitana di venezia");
                }else{
                    out.println(textField.getText());
                }
            }
        });
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
