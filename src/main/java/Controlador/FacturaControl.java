/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Cliente;
import Modelo.Factura;
import Modelo.Producto;
import Servicio.ClienteServicio;
import Servicio.FacturaServicio;
import Servicio.ProductoServicio;
import java.util.List;

/**
 *
 * @author Gus-Gus
 */
public class FacturaControl {
    
    private FacturaServicio facturaServicio = new FacturaServicio();
    private ClienteServicio clienteServicio = new ClienteServicio();
    private ProductoServicio productoServicio = new ProductoServicio();
    //private FacturaServicio facturaServicio = new FacturaServicio();
    
    
    public Factura crearFactura(String []args)throws RuntimeException{
        Cliente cliente = this.clienteServicio.buscarPorCedula(convertirEntero(args[1]));
        Producto producto = this.productoServicio.buscarPorCodigo(convertirEntero(args[3]));
        Factura factura = new Factura (convertirEntero(args[0]), cliente, args[2], producto ,args[4],convertirEntero(args[5]), convertirEntero(args[6]));
        this.facturaServicio.crear(factura);
        return factura;
    }
    public Factura buscarNFactura(String arg){
        return this.facturaServicio.buscarNFactura(convertirEntero(arg));
        
    }
    public Factura elimiar (String arg){
        return this.facturaServicio.eliminar(convertirEntero(arg));
    }
    public Factura modificar(String []args){
        Cliente cliente = this.clienteServicio.buscarPorCedula(convertirEntero(args[1]));
        Producto producto = this.productoServicio.buscarPorCodigo(convertirEntero(args[3]));
        Factura facturaNuevo = new Factura (convertirEntero(args[0]), cliente, args[2], producto ,args[4],convertirEntero(args[5]), convertirEntero(args[6]));
        this.facturaServicio.modificar(convertirEntero(args[0]),facturaNuevo);
        return facturaNuevo;
    }
    public List<Factura> listar(){
        return this.facturaServicio.listar();
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
