import java.util.ArrayList;
import java.io.*;
import java.util.Collections;
import java.util.Scanner;

/**
 * La classe gestore dati legge il file csv e converte i dati lettio in Arraylist.
 * Vengono sviluppate tutte le funzioni avanzate di ricerca tramite filtri
 */

public class GestoreDati {
    private ArrayList<Dato> dati;

    /**
     * Nel metodo costruttore viene effettuata la lettura del file
     */
    public GestoreDati() {
        dati = new ArrayList<>();
        File dati = new File("Regione-Veneto---Quantita-di-rifiuti-prodotti-per-tipo-e-provincia.csv");
        try {
            Scanner reader = new Scanner(dati);
            String oggetto = "";
            int count=0;
            while(reader.hasNextLine()) {
                oggetto = reader.nextLine();
                if(count >0) {
                    String arrDati[] = oggetto.split(";");
                    this.dati.add(new Dato(Integer.parseInt(arrDati[0]),arrDati[1],arrDati[2], Float.parseFloat(arrDati[3])));
                }
                count++;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }catch (NumberFormatException e){
            System.out.println("Errore nella lettura del file");
        }
    }


    /**
     * CODICE: 1
     * Prende una determinata riga del file
     * @param riga
     * @return riga del file (String)
     */
    public String getRiga(int riga){
        if(riga <= dati.size() && riga >= 0){
            return dati.get(riga-1).toString();
        }
        return "ERROR: riga non trovata";
    }

    /**
     * CODICE: 2
     *Trova la provincia con più rifiuti prodotti in un anno
     * @param anno
     * @return Provincia che ha prodotto più rifiuti in un determinato anno (String)
     */
    public String provinciaRifiutiAnno(int anno){
        float quantità = 0;
        String provincia = "";
        for(Dato dato:dati){
            if(dato.getAnno() == anno) {
                if(dato.getQuantità() > quantità){
                    quantità = dato.getQuantità();
                    provincia = dato.getProvincia();
                }
            }
        }
        if(quantità== 0){
            return "Non sono presenti dati per l'anno inserito";
        }
        return "La provincia con piu rifiuti prodotti nell'anno: "+anno+" e: "+provincia;
    }

    /**
     * CODICE: 3
     * Scorre tutti i dati e somma tutti i rifiuti per tipo
     * @return quantità di rifiuti prodotti (String)
     */
    public String rifiutiProdotti(){
        String pericolosi = "pericolosi";
        String specialiNp = "speciali np";
        float iPericolosi = 0;
        float iSpeciali = 0;
        for(Dato dato: dati){
            if(dato.getTipoRifiuto().equalsIgnoreCase(pericolosi)){
                iPericolosi += dato.getQuantità();
            }else{
               iSpeciali += dato.getQuantità();
            }
        }

        return "<html><div style='text-align: center;'>La regione veneto in 10 anni ha prodotto "+iPericolosi+" tonnellate di rifiuti pericolosi<br>" +
                "e " + iSpeciali+" tonnellate di rifiuti speciali</div></html>";
    }

    /**
     * CODICE: 4
     *Scorre fino a trovare l'anno giusto e somma tutti i rifiuti prodotti per tipo
     * @param anno
     * @return Quantità di rifiuti prodotti per tipo in un anno (String)
     */
    public String rifiutiProdottiAnno(int anno){
        if(anno < 2000 || anno > 2010){
            return "Non sono presenti dati nell'anno inserito";
        }
        String pericolosi = "pericolosi";
        String specialiNp = "speciali np";
        float iPericolosi = 0;
        float iSpeciali = 0;
        for(Dato dato: dati){
            if(dato.getAnno() == anno){
                if(dato.getTipoRifiuto().equalsIgnoreCase(pericolosi)){

                    iPericolosi += dato.getQuantità();
                }else{
                    iSpeciali += dato.getQuantità();

                }
            }
        }
        return "<html><div style='text-align: center;'>Nell'anno: "+anno+" sono state prodotte "+iPericolosi+" tonnellate di rifiuti pericolosi<br>" +
                "e "+iSpeciali+" tonnellate di rifiuti speciali</div></html>";
    }

    /**
     * CODICE: 5
     *Scorre fino a trovare la provincia giusta e somma tutti i rifiuti prodotti per tipo
     * @param provincia
     * @return Quantità di rifiuti prodotti per tipo da una provincia (String)
     */
    public String rifiutiProdottiProvincia (String provincia){
        String pericolosi = "pericolosi";
        String specialiNp = "speciali np";
        float iPericolosi = 0;
        float iSpeciali = 0;
        for(Dato dato: dati){
            if(dato.getProvincia().equalsIgnoreCase(provincia)){
                if(dato.getTipoRifiuto().equalsIgnoreCase(pericolosi)){
                    iPericolosi += dato.getQuantità();
                }else{
                    iSpeciali += dato.getQuantità();
                }
            }
        }
        if(iSpeciali + iPericolosi == 0){
            return "Provincia non trovata";
        }
        return "<html><div style='text-align: center;'>La provincia: "+provincia+" ha prodotto "+iPericolosi+" tonnellate di rifiuti pericolosi <br>" +
                "e "+iSpeciali+" tonnellate di rifiuti speciali</div></html>";
    }

    /**
     * Scorre tutti i dati, somma i rifiuti prodotti in un anno e individua l'anno iin cui sono stati prodotti più rifiuti
     * @return Anno in cui la regione Veneto ha prodotto più rifiuti (String)
     */
    public String annoRifiuti(){
        float nRifiuti = 0;
        float nRifiutiMax = 0;
        int annoMagg = 0;
        int anno = 0;
        for(Dato dato: dati){
            if(dato.getAnno() != anno){
                nRifiuti = 0;
                anno = dato.getAnno();

            }
            nRifiuti += dato.getQuantità();
            if(nRifiuti > nRifiutiMax){
                nRifiutiMax = nRifiuti;
                annoMagg = anno;
            }
        }
        return "La regione Veneto ha prodotto piu rifiuti nel: "+annoMagg;
    }

    /**
     * Ordina i dati per provincia, somma i rifiuti prodotti per provincia e restituisce la provincia che ha prodotto più rifiuti
     * @return La provincia che ha prodotto più rifiuti
     */
    public String provinciaRifiuti (){
        //Creo la copia dell'arrayList di dati
        ArrayList <Dato> tmp = dati;
        //Ordino la copia per provincia rendendo così adiacenti i dati riferiti alla stessa provincia
        Collections.sort(tmp);
        float nRifiuti = 0;
        float nRifiutiMax = 0;
        String provincia = "";
        String provinciaMax = "";
        for(Dato dato: tmp){
            if(!dato.getProvincia().equalsIgnoreCase(provincia)){
                nRifiuti = 0;
                provincia = dato.getProvincia();

            }
            nRifiuti += dato.getQuantità();
            if(nRifiuti > nRifiutiMax){
                nRifiutiMax = nRifiuti;
                provinciaMax = provincia;
            }
        }
        return "La provincia che ha prodotto piu rifiuti e : "+provinciaMax;
    }

    /**
     * Metodo toString
     * @return
     */
    @Override
    public String toString() {
        String s="";
        for(Dato dato:dati){
            s+= dato.toString()+"\n\r";
        }
        return s;
    }


}
