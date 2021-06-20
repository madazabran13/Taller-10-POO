package Datos;

import Dominio.Publicacion;
import java.io.IOException;
import java.util.List;

public interface IAccesoDatos {
    void insertaPublicacion(Publicacion p) throws IOException;
    List<Publicacion> leerPublicaciones() throws IOException;
    Publicacion buscarPublicacion(Publicacion p) throws IOException;
    boolean eliminarPublicacion(Publicacion p, String isbn) throws IOException;
}
