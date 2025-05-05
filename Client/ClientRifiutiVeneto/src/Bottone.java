import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

/**
 * La classe Bottone esetnde JButton permettendomi così di aggiungere funzionalità in più
 */
public class Bottone extends JButton {
    private int operazione;

    /**
     * Il metodo costruttore inizializza il bottone e imposta l'operazione corretta nel caso in cui venga cliccato
     * @param operazione
     */
    public Bottone(int operazione){
        super(Main.opzioni[operazione]);
        this.operazione = operazione;
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);
        //quando il pulsante viene cliccato viene creata una nuova connessione al server
        addActionListener(e -> {
            try {
                Socket sck = new Socket(Main.nomeServer, Main.portaServer);
                Connection connection = new Connection(sck, operazione);
                connection.start();
            } catch (UnknownHostException ex) {
                throw new RuntimeException(ex);
            } catch(ConnectException ec){
                System.out.println("Server spento");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    /**
     * Rende arrotondati gli angoli dei pulsanti
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(),30,30);

        super.paintComponent(g2);
        g2.dispose();
    }

    /**
     * Imposta il colore del bordo
     * @param g the <code>Graphics</code> context in which to paint
     *
     */
    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
        g2.dispose();
    }


    public int getOperazione() {
        return operazione;
    }

    public void setOperazione(int operazione) {
        this.operazione = operazione;
    }
}
