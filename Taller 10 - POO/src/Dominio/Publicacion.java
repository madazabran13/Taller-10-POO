
package Dominio;

public abstract class Publicacion {
    public String isbn, titulo, autor;
    public int anio;
    public Double costo;
    
    public Publicacion() {
    }

    public Publicacion(String isbn) {
        this.isbn = isbn;
    }

    public Publicacion(String isbn, String titulo, String autor, int anio, double costo) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
        this.costo = costo;
    }    
    
    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAnio() {
        return anio;
    }

    public String getAutor() {
        return autor;
    }

    public Double getCosto() {
        return costo;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }
    
    public abstract String getInfo();
    
    @Override
    public String toString() {
        return "\nISBN: "+isbn + 
                "\nTitulo: " + titulo + 
                "\nAño de publicacion: " + anio
                + "\nAutor: " + autor + 
                "\nPrecio: " + costo + 
                this.getInfo();
    }
}