
package Dominio;

public class Libro extends Publicacion {
    private int nPaginas;
    private int edicion;
    
    public Libro(String isbn){
        super(isbn);
    }

    public Libro(int nPaginas, int edicion, String isbn, String titulo, String autor, int anio, double costo) {
        super(isbn, titulo, autor, anio, costo);
        this.nPaginas = nPaginas;
        this.edicion = edicion;
    }

    public int getnPaginas() {
        return nPaginas;
    }

    public int getEdicion() {
        return edicion;
    }

    public void setnPaginas(int nPaginas) {
        this.nPaginas = nPaginas;
    }

    public void setEdicion(int edicion) {
        this.edicion = edicion;
    }
    
    @Override
    public String getInfo() {
        return "\nNumero de paginas: " +this.nPaginas + "\nAño de ediciom: " + this.edicion;
    }
}
