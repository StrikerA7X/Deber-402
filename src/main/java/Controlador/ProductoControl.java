/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Factura;
import Modelo.Producto;
import Servicio.FacturaServicio;
import Servicio.ProductoServicio;
import java.util.List;

/**
 *
 * @author Gus-Gus
 */
public class ProductoControl {
    
    private ProductoServicio productoServicio = new ProductoServicio();
    
    
    public Producto crearProducto(String[]args)throws RuntimeException{
        
        Producto producto = new Producto(args[0], args[1],convertirEntero(args[2]), convertirEntero(args[3]), convertirEntero(args[4]), convertirEntero(args[5]));
        this.productoServicio.crear(producto);
        return producto;
    }
    public Producto buscarProducto(String arg){
        return this.productoServicio.buscarPorCodigo(convertirEntero(arg));
        
    }
    public Producto eliminar(String arg){
        return this.productoServicio.eliminar(convertirEntero(arg));
    }
    public Producto modificarProducto(String[]args){
        
        Producto productoNuevo = new Producto(args[0], args[1], convertirEntero(args[2]), convertirEntero(args[3]), convertirEntero(args[4]), convertirEntero(args[5]));
        this.productoServicio.crear(productoNuevo);
        return productoNuevo;
    }
    public List<Producto> listar(){
        return this.productoServicio.listar();
    }
    private int convertirEntero(String numero){
        try{
            return Integer.valueOf(numero);
        }catch(NumberFormatException e){
            throw new RuntimeException("el campo ingresado solamente recibe "+"numeros");
        }catch(Exception e){
            throw new RuntimeException("error inesperado");
        }
    }
}
