package Datos;

import Dominio.Publicacion;
import Excepciones.ExcepcionesAccesoDatos;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class ListAccesoDatos implements IAccesoDatos {
    private int contador;
    private File archivo;
    private FileWriter modoEscritura; // abre el archivo para escritura
    private Scanner modoLectura; // abre el archivo en modo lectura

    public ListAccesoDatos() {
        this.archivo = new File("Publicaciones.txt");
    }

    public ListAccesoDatos(String nombreArchivo) {
        this.archivo = new File(nombreArchivo);
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
        try {
            List<Publicacion> listado = new ArrayList();
            this.modoLectura = new Scanner(this.archivo);
            while (this.modoLectura.hasNext()) {
                String datos[] = this.modoLectura.nextLine().split(";");
                   Publicacion p = new Publicacion() {
                    @Override
                    public String getInfo() {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                };
                p.setIsbn(datos[0]);
                p.setTitulo(datos[1]);
                p.setAnio(Integer.parseInt(datos[2]));
                p.setAutor(datos[3]);
                p.setCosto(Double.parseDouble(datos[4]));
                listado.add(p);
            }
            this.modoLectura.close();
            return listado;
        } catch (FileNotFoundException ioe) {
            System.out.println("Error al abrir archivo para lectura..");
        }
        return null;
    }
    

    @Override
    public Publicacion buscarPublicacion(Publicacion p) throws IOException {
        
        if (p == null) {
            throw new ExcepcionesAccesoDatos("El objeto no existe");
        }

        if (p.getIsbn() == null || p.getIsbn() == "" || p.getIsbn().equals("")) {
            throw new ExcepcionesAccesoDatos("El ISBN no existe");
        }
        Publicacion encontrado = null;
        try {
            this.modoLectura = new Scanner(this.archivo);
            while (this.modoLectura.hasNextLine()) {
                String linea = this.modoLectura.nextLine();
                if (p.getIsbn().equalsIgnoreCase(p.isbn)) {
                    encontrado = p;
                    break;
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
            this.modoLectura = new Scanner(this.archivo);
            ListAccesoDatos archivoTemporal = new ListAccesoDatos("Temporal.dat");
            while(this.modoLectura.hasNext()){
                String linea = this.modoLectura.nextLine();
                
                if (p.getIsbn().equals(p.getIsbn())){ // eliminar
                    eliminado = true;
                    contador--;   
                    System.out.println("Publicacion No registrada");
                }
                else{
                    System.out.println("Publicacion eliminada: ");
                    System.out.println(eliminado);
                    eliminado = false;
                }
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
