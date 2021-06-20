package Negocio;

import Datos.*;
import Dominio.Publicacion;;
import java.io.IOException;
import java.util.List;

public class RegistroPublicacion {
    private IAccesoDatos datos;

    public RegistroPublicacion() {
        this.datos = new ListAccesoDatos();
    }

    public void addPublicacion(Publicacion p) throws IOException {
        this.datos.insertaPublicacion(p);
    }

    public List<Publicacion> consultaPublicaciones() throws IOException {
        return this.datos.leerPublicaciones();
    }

    public Publicacion buscarPublicacion(Publicacion p) throws IOException {
        return this.datos.buscarPublicacion(p);
    }

    public boolean eliminarPublicacion(Publicacion p, String isbn) throws IOException {
        return this.datos.eliminarPublicacion(p, isbn);
    }

}
