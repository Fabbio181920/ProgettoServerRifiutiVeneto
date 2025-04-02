import java.util.ArrayList;
import java.io.*;
import java.util.Collections;
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

    public String tipoRifiutiProdotti(){
        String pericolosi = "pericolosi";
        String specialiNp = "speciali np";
        double iPericolosi = 0;
        double iSpeciali = 0;
        for(Dato dato: dati){
            if(dato.getTipoRifiuto().equalsIgnoreCase(pericolosi)){
                iPericolosi += dato.getQuantità();
            }else{
               iSpeciali += dato.getQuantità();
            }
        }
        if(iSpeciali > iPericolosi){
            return "La regione veneto produce più rifiuti di tipo: " +specialiNp;
        }
        return "La regione veneto produce più rifiuti di tipo: "+pericolosi;
    }

    public String tipoRifiutiProdottiAnno(int anno){
        String pericolosi = "pericolosi";
        String specialiNp = "speciali np";
        double iPericolosi = 0;
        double iSpeciali = 0;
        for(Dato dato: dati){
            if(dato.getAnno() == anno){
                if(dato.getTipoRifiuto().equalsIgnoreCase(pericolosi)){
                    iPericolosi += dato.getQuantità();
                }else{
                    iSpeciali += dato.getQuantità();
                }
            }
        }
        if(iSpeciali > iPericolosi){
            return "Nell'anno: "+anno+" sono stati prodotti più rifiuti di tipo: " +specialiNp;
        }
        return "Nell'anno: "+anno+" sono stati prodotti più rifiuti di tipo: "+pericolosi;
    }

    public String tipoRifiutiProdottiProvincia (String provincia){
        String pericolosi = "pericolosi";
        String specialiNp = "speciali np";
        double iPericolosi = 0;
        double iSpeciali = 0;
        for(Dato dato: dati){
            if(dato.getProvincia().equalsIgnoreCase(provincia)){
                if(dato.getTipoRifiuto().equalsIgnoreCase(pericolosi)){
                    iPericolosi += dato.getQuantità();
                }else{
                    iSpeciali += dato.getQuantità();
                }
            }
        }
        if(iSpeciali > iPericolosi){
            return "La provincia: "+provincia+" ha prodotto più rifiuti di tipo: " +specialiNp;
        }else if(iPericolosi == iSpeciali){
            return "Provincia non trovata";

        }
        return "La provincia: "+provincia+" ha prodotto più rifiuti di tipo: "+pericolosi;
    }

    public String annoRifiuti(){
        double nRifiuti = 0;
        double nRifiutiMax = 0;
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
        return "La regione Veneto ha prodotto più rifiuti nel: "+annoMagg;
    }

    public String provinciaRifiuti (){
        ArrayList <Dato> tmp = dati;
        Collections.sort(tmp);
        double nRifiuti = 0;
        double nRifiutiMax = 0;
        String provincia = "";
        String provinciaMax = "";
        for(Dato dato: dati){
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
        return "La provincia che ha prodotto più rifiuti è : "+provinciaMax;
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
