
package Servicio;

import Modelo.Producto;
import java.util.List;


public interface IProductoServicio {
    
    public Producto crear(Producto producto);
    public Producto modificar(int codigoNuevo, Producto producto);
    public Producto eliminar(int codigoProducto);
    public Producto buscarPorCodigo(int codigoProducto);
    public int buscarPosicion(Producto producto);
    public List<Producto> listar();
    
}
