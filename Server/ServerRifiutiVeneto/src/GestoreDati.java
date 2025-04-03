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
                    this.dati.add(new Dato(Integer.parseInt(arrDati[0]),arrDati[1],arrDati[2], Float.parseFloat(arrDati[3])));
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

    //
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
            return "Anno non valido";
        }
        return "La provincia con piu rifiuti prodotti nell'anno: "+anno+" e: "+provincia;
    }
//
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

        return "La regione veneto in 10 anni ha prodotto "+iPericolosi+" tonnellate di rifiuti pericolosi\n" +
                "e " + iSpeciali+" tonnellate di rifiuti speciali";
    }
//
    public String rifiutiProdottiAnno(int anno){
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
        return "Nell'anno: "+anno+" sono state prodotte "+iPericolosi+" tonnellate di rifiuti pericolosi\n" +
                "e "+iSpeciali+" tonnellate di rifiuti speciali";
    }

    //
    public String rifiutiProdottiProvincia (String provincia){
        String pericolosi = "pericolosi";
        String specialiNp = "speciali np";
        float iPericolosi = 0;
        float iSpeciali = 0;
        for(Dato dato: dati){
            if(dato.getProvincia().equalsIgnoreCase(provincia)){
                if(dato.getTipoRifiuto().equalsIgnoreCase(pericolosi)){
                    System.out.println(iPericolosi);
                    iPericolosi += dato.getQuantità();
                }else{
                    iSpeciali += dato.getQuantità();
                }
            }
        }
        if(iSpeciali + iPericolosi == 0){
            return "Provincia non trovata";
        }
        return "La provincia: "+provincia+" ha prodotto "+iPericolosi+" tonnellate di rifiuti pericolosi\n" +
                "e "+iSpeciali+" tonnellate di rifiuti speciali";
    }

    //
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

    //
    public String provinciaRifiuti (){
        ArrayList <Dato> tmp = dati;
        Collections.sort(tmp);
        float nRifiuti = 0;
        float nRifiutiMax = 0;
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
        return "La provincia che ha prodotto piu rifiuti e : "+provinciaMax;
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
