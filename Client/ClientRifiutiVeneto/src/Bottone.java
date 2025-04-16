import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.*;

public class Bottone extends JButton {
    private int operazione;

    public Bottone(int operazione){
        super(Main.opzioni[operazione]);
        this.operazione = operazione;
        addActionListener(e -> {
            try {
                Socket sck = new Socket(Main.nomeServer, Main.portaServer);
                Connection connection = new Connection(sck, operazione);
                connection.start();
            } catch (UnknownHostException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public int getOperazione() {
        return operazione;
    }

    public void setOperazione(int operazione) {
        this.operazione = operazione;
    }
}
