
package Vista;

import Dominio.*;
import Excepciones.ExcepcionesAccesoDatos;
import Negocio.RegistroPublicacion;
import java.io.IOException;
import java.util.*;

public class VistaConsola {
   
    private String titulos[] = {
        "1. Registrar ",
        "2. Visualizar",
        "3. Buscar",
        "4. Eliminar ",
        "5. Finalizar"
    };
    private int opcion;
    private Scanner lector;
    private RegistroPublicacion logica;

    public VistaConsola() {
        this.lector = new Scanner(System.in);
        this.logica = new RegistroPublicacion();
    }

    public void ejecutarMenu() throws IOException {
        do {
            this.imprimirTitulos();
            this.leerOpcion();
            this.ejecutarOpcion();
        } while (this.opcion != 5);
    }

    public void imprimirTitulos() {
        System.out.println("\n  PUBLICACIONES\n");
        for (int i = 0; i < this.titulos.length; i++) {
            System.out.println(this.titulos[i]);
        }
    }

    public void leerOpcion() {
        boolean excepcion = true;
        do {
            try {
                System.out.print("\n- Seleccione una opcion: ");
                this.opcion = this.lector.nextInt();
                excepcion = false;
            } catch (java.util.InputMismatchException ime) {
                System.out.println("- Se requiere valor entero,digite nuevamente....\n");
                excepcion = true;
                this.lector.nextLine();
            }

        } while (excepcion);
    }

    public void ejecutarOpcion() throws IOException {
        switch (this.opcion) {
            case 1:
                System.out.print(
                          "\n--------------------------\n"
                        + "  REGISTRAR PUBLICACION"
                        + "\n--------------------------\n"
                        + "1. Libro  \n"
                        + "2. Audiolibro\n ");
                boolean excepcion = true;
                do {
                    try {
                        System.out.print("\n- Seleccione una opcion: ");
                        this.opcion = this.lector.nextInt();
                        excepcion = false;
                    } catch (java.util.InputMismatchException ime) {
                        System.out.println("- Se requiere valor entero,digite nuevamente....\n");
                        excepcion = true;
                        this.lector.nextLine();
                    }

                } while (excepcion);
                switch (this.opcion) {
                    case 1: 
                        vistaInsertaLibro();
                        break;
                    case 2: 
                        vistaInsertaAudiolibro();
                        break;
                    default:
                        System.out.println("Opcion no valida....");
                }

                break;
            case 2:
                vistaConsultarPublicacion();
                break;
            case 3:
                vistaBuscarPublicacion();
                break;
            case 4:
                vistaEliminarPublicacion();
                break;
            case 5:
                System.out.println("\n-Ha finalizado.....");
                break;
            default:
                System.out.println("Opcion no valida...");
        }

    }

    public void vistaInsertaLibro() throws IOException {
        try {
            System.out.println(
                          "\n--------------"
                        + "\n    LIBRO"
                        + "\n--------------");
            
            System.out.print("\n- Numero de paginas: ");
            String snPaginas = this.lector.next();
            int nPaginas = Integer.parseInt(snPaginas);
            
            System.out.print("- Año de edicion: ");
            String sEdicion = this.lector.next();
            int Edicion = Integer.parseInt(sEdicion);
            
            System.out.print("- ISBN: ");
            String isbn = this.lector.next();
            
            System.out.print("- Titulo: ");
            String Titulo = this.lector.next();
            
            System.out.print("- Nombre del autor: ");
            String Autor = this.lector.next();
            
            System.out.print("- Año de publicación: ");
            String sAnio = this.lector.next();
            int Anio = Integer.parseInt(sAnio);
            
            System.out.print("- Precio del libro: ");
            String sCosto = this.lector.next();
            int Costo = Integer.parseInt(sCosto);

            Publicacion Libro = new Libro(nPaginas, Edicion, isbn, Titulo, Autor, Anio, Costo);
            this.logica.addPublicacion(Libro);
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }

    }

    public void vistaInsertaAudiolibro() throws IOException {
        try {
            System.out.println(
                          "\n--------------"
                        + "\n  AUDIOLIBRO"
                        + "\n--------------");
            System.out.print("\n- Duración: ");
            String sDuracion = this.lector.next();
            double Duracion = Double.parseDouble(sDuracion);
            
            System.out.print("- Formato: ");
            String Formato = this.lector.next();
            
            System.out.print("- Peso: ");
            String sPeso = this.lector.next();
            double Peso = Double.parseDouble(sPeso);
            
            System.out.print("- ISBN: ");
            String isbn = this.lector.next();
            
            System.out.print("- Titulo: ");
            String Titulo = this.lector.next();
            
            System.out.print("- Nombre del autor: ");
            String Autor = this.lector.next();
            
            System.out.print("- Año de publicación: ");
            String sAnio = this.lector.next();
            int Anio = Integer.parseInt(sAnio);
            
            System.out.print("- Precio del libro: ");
            String sCosto = this.lector.next();
            int Costo = Integer.parseInt(sCosto);

            Publicacion AudioLibro = new AudioLibro(Duracion, Formato, Peso, isbn, Titulo, Autor, Anio, Costo);
            this.logica.addPublicacion(AudioLibro);
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }

    }

    public void vistaBuscarPublicacion() throws IOException {
        System.out.println(
                          "\n--------------"
                        + "\n   BUSQUEDA"
                        + "\n--------------");
        System.out.print("\n- ISBN a buscar: ");
        String isbn = this.lector.next();
        try {
            Publicacion pub = this.logica.buscarPublicacion(new Libro(isbn));
            if (pub == null) {
                Publicacion pub1 = this.logica.buscarPublicacion(new AudioLibro(isbn));
                if (pub1 == null) {
                    System.out.println("- La publicacion no está registrada!!....\n");
                } else {
                    System.out.println(pub1);
                }
            } else {
                System.out.println(pub);
            }
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void vistaConsultarPublicacion() throws IOException {
        try {
            System.out.println(
                      "\n---------------------------\n"
                    + "  LISTA DE PUBLICACIONES   "
                    + "\n---------------------------");
            List<Publicacion> lista = this.logica.consultaPublicaciones();
            for (Publicacion i : lista) {
                System.out.println(i);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void vistaEliminarPublicacion() throws IOException {
        System.out.print(
                 "\n---------------------------------\n"
               + "   ELIMINACIÓN DE PUBLICACIONES "
               + "\n---------------------------------");
            System.out.println("\n- ISBN a eliminar: ");
            String isbn = this.lector.next();
            try {
                List<Publicacion> lista = this.logica.consultaPublicaciones();
                Publicacion pub = this.logica.buscarPublicacion(new Libro(isbn));
                this.logica.eliminarPublicacion(pub, isbn);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }  
    }
}

