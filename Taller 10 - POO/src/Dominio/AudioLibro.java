
package Dominio;

public class AudioLibro extends Publicacion {
    private double duracion;
    private String formato;
    private double peso;

    public AudioLibro(String isbn) {
        super(isbn);
    }
    
    public AudioLibro(double duracion, String formato, double peso, String isbn, String titulo, String autor, int anio, double costo) {
        super(isbn, titulo, autor, anio, costo);
        this.duracion = duracion;
        this.formato = formato;
        this.peso = peso;
    }

    public double getDuracion() {
        return duracion;
    }

    public String getFormato() {
        return formato;
    }

    public double getPeso() {
        return peso;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public String getInfo() {
        return "\nDuracion: "+ this.duracion + "\nFormato: " + this.formato + "\nPeso: "+this.peso;
    }
}
