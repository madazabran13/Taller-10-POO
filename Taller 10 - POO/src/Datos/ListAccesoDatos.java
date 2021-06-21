package Datos;

import Dominio.Publicacion;
import Excepciones.ExcepcionesAccesoDatos;
import java.io.*;
import java.util.*;

public class ListAccesoDatos implements IAccesoDatos {
    private Publicacion arreglo[];
    private int n;
    private File archivo;
    private FileWriter modoEscritura; // abre el archivo para escritura
    private Scanner modoLectura; // abre el archivo en modo lectura

    public ListAccesoDatos() {
        this.archivo = new File("Publicaciones.txt");
    }

    public ListAccesoDatos(String nombreArchivo) {
        this.archivo = new File(nombreArchivo);
    }
    public ListAccesoDatos(int tam){
        this.arreglo = new Publicacion[tam];
    }
    
    @Override
    public void insertaPublicacion(Publicacion p) throws IOException {
        PrintWriter pw = null;
        try {
            this.modoEscritura = new FileWriter(this.archivo, true); // modo edicion
            pw = new PrintWriter(this.modoEscritura);
            pw.println(p.toString()); 
        } catch (IOException ioe) {
            throw new IOException("Error al abrir el archivo");
        } finally {
            if(pw!=null)
               pw.close();
               this.modoEscritura.close();
        }
    }

    @Override
    public List<Publicacion> leerPublicaciones() throws IOException {
        List<Publicacion> listado = new ArrayList();
        try {
            this.modoLectura = new Scanner(this.archivo);
            for (int i = 0; i < this.n; i++) {
                while (this.modoLectura.hasNext()) {
                    String datos[] = this.modoLectura.nextLine().split(";");
                    Publicacion p = this.arreglo[i];
                    listado.add(p);
                }
                this.modoLectura.close();
            }  
        } catch (IOException ioe) {
            System.out.println("Error al abrir archivo para lectura..");
        } finally {
            if(this.modoLectura!=null)
            this.modoLectura.close();
        }
        return listado;
    }
    

    @Override
    public Publicacion buscarPublicacion(Publicacion p) throws IOException {
        
        try {
            if (this.n == 0) {
                throw new IOException("No hay publicaciones");
            }
            if (p == null) {
                throw new IOException("El objeto no existe");
            }

            if (p.getIsbn() == null || p.getIsbn() == "" || p.getIsbn().equals("")) {
                throw new IOException("El ISBN no existe");
            }
            Publicacion encontrado = null;
            for (int i = 0; i < this.n; i++) {
                this.modoLectura = new Scanner(this.archivo);
                while (this.modoLectura.hasNextLine()) {
                    String linea = this.modoLectura.nextLine();
                    Publicacion pub = this.arreglo[i];
                    if (pub.getIsbn().equals(p.getIsbn())) {
                        encontrado = pub;
                        break;
                    }
                }
            }
            return encontrado;
        } catch (IOException ioe) {
            throw ioe;
        } finally {
            if(this.modoLectura!=null)
                this.modoLectura.close();
        }
    }

    
    @Override
    public boolean eliminarPublicacion(Publicacion p, String isbn) throws IOException {
        boolean eliminado = false;
        try {
            for (int i = 0; i < this.n; i++) {
                this.modoLectura = new Scanner(this.archivo);
                ListAccesoDatos archivoTemporal = new ListAccesoDatos("Temporal.dat");
                Publicacion pub = this.arreglo[i];
                while(this.modoLectura.hasNext()){
                    String linea = this.modoLectura.nextLine();
                    if (pub.getIsbn().equals(p.getIsbn())) {
                        this.arreglo[i] = null;
                        eliminado = true;
                        this.n--;
                        System.out.println("Publicacion No registrada");
                    } else {
                        System.out.println("Publicacion eliminada: ");
                        System.out.println(eliminado);
                        eliminado = false;
                    }
                }
                this.modoLectura.close();
            }
        } catch (IOException ioe) {
            throw ioe;
        }
        finally{
            this.modoLectura.close();
        }
        return eliminado;
    } 
}
