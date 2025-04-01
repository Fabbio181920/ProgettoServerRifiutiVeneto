import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class GestoreDati {
    private ArrayList<Dato> dati;


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
                    this.dati.add(new Dato(Integer.parseInt(arrDati[0]),arrDati[1],arrDati[2], Double.parseDouble(arrDati[3])));
                }
                count++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    public String getRiga(int riga){
        if(riga <= (dati.size()-1) && riga > 0){
            return dati.get(riga -1).toString();
        }
        return "ERROR: riga non trovata";
    }

    public String rifiutiProdotti(){
        String pericolosi = "pericolosi";
        String specialiNp = "speciali np";
        double iPericolosi = 0;
        int iSpeciali = 0;
        for(Dato dato: dati){
            if(dato.getTipoRifiuto().equalsIgnoreCase(pericolosi)){
                iPericolosi += dato.getQuantità();
            }else{
               iSpeciali += dato.getQuantità();
            }
        }
        if(iSpeciali > iPericolosi){
            return "La regione veneto produce più rifiuti " +specialiNp;
        }
        return "La regione veneto produce puù rifiuti "+pericolosi;
    }

    public String provinciaRifiutiAnno(int anno){
        double quantità = 0;
        String provincia = "";
        for(Dato dato:dati){
            if(dato.getAnno() == anno) {
                if(dato.getQuantità() > quantità){
                    quantità = dato.getQuantità();
                    provincia = dato.getProvincia();
                }
            }
        }
        return "La provincia con più rifiuti prodotti nell'anno: "+anno+" è: "+provincia;
    }


    @Override
    public String toString() {
        String s="";
        for(Dato dato:dati){
            s+= dato.toString()+"\n";
        }
        return s;
    }


}
