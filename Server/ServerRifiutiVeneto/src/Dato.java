public class Dato implements Comparable{
    private int anno;
    private String provincia;
    private String tipoRifiuto;
    private float quantità;

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

    @Override
    public String toString() {
        return "Dato anno: " + anno +"provincia: " + provincia + "\n\r" +
                "Tipo rifiuto: " + tipoRifiuto + "quantità rifiuto:" + quantità;
    }

    @Override
    public int compareTo(Object o) {
        Dato dato = (Dato) o;
        return this.provincia.compareToIgnoreCase(dato.getProvincia());
    }
}
