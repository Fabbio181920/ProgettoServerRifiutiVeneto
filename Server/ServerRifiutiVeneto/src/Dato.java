/**
 * La classe dato permette di memorizzare in maniera strutturata una riga del file csv
 * Implemento l'interfaccia Comparable in modo da poter comparare due dati (e quindi poterli ordinare)
 */
public class Dato implements Comparable{
    private int anno;
    private String provincia;
    private String tipoRifiuto;
    private float quantità;

    /**
     *Ogni parametro del metodo costruttore corrisponde ad una colonna del file csv
     * @param anno
     * @param provincia
     * @param tipoRifiuto
     * @param quantità
     */
    public Dato(int anno, String provincia, String tipoRifiuto, float quantità) {
        this.anno = anno;
        this.provincia = provincia;
        this.tipoRifiuto = tipoRifiuto;
        this.quantità = quantità;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getTipoRifiuto() {
        return tipoRifiuto;
    }

    public void setTipoRifiuto(String tipoRifiuto) {
        this.tipoRifiuto = tipoRifiuto;
    }

    public float getQuantità() {
        return quantità;
    }

    public void setQuantità(float quantità) {
        this.quantità = quantità;
    }

    /**
     * Restituisce tutte le informazioni relative al dato in una stringa che corrispondono ad una riga del file csv
     * @return
     */
    @Override
    public String toString() {
        return "<html><div style='text-align: center;'>Dato anno: " + anno +" provincia: " + provincia + "<br>" +
                "Tipo rifiuto: " + tipoRifiuto + " quantità rifiuto: " + quantità+"</div></html>";
    }

    /**
     * Compara due dati in base alla provincia
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Object o) {
        Dato dato = (Dato) o;
        return this.provincia.compareToIgnoreCase(dato.getProvincia());
    }
}
