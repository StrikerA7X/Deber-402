
package Servicio;

import Modelo.Factura;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Cliente;
import modelo.Factura;


public class FacturaServicio implements IFacturaServicio {
    private List<Factura> listJugador;
    private String folder;
    
    public FacturaServicio() throws IOException
    {
        folder = "C:/Equipo";
        listJugador= new ArrayList<>();
        listJugador = listar();

    }

    @Override
    public Factura crear(Factura factura) 
    {
        try {
            if (exist(factura.getCodigo()) == false)
            {
                String path = folder + "/Jugador.txt";
                ObjectOutputStream archivo = null;
                try {
                    archivo = new ObjectOutputStream(new FileOutputStream(path,true));
                    archivo.writeObject(factura);
                    archivo.close();
                } catch (IOException e) {
                    archivo.close();
                }
                this.listJugador = listar();
                return factura;
            }
            else
            {
                throw new RuntimeException("Ya existe un jugador con este código.");
            }
        } catch (IOException ex) {
            Logger.getLogger(FacturaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (RuntimeException e)
        {
            throw new RuntimeException("Ya existe este código.");
        }
        return factura;        
    }

    @Override
    public List<Factura> listar() throws IOException{
        String path = folder + "/Jugador.txt";  
        if (new File(path).exists() == true)
        {
           var computadorList = new ArrayList<Factura>();        
        FileInputStream file = new FileInputStream(path);
        ObjectInputStream archivo = null;
        try{
            while(file.available()>0)
            {
                archivo = new ObjectInputStream(file);
                Factura computador = (Factura) archivo.readObject(); 
                computadorList.add(computador);
            }    
            if (archivo != null) archivo.close();            
            file.close();
        }catch(IOException e){
            archivo.close();
            file.close();
        } catch (ClassNotFoundException ex) {        
            Logger.getLogger(FacturaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return computadorList; 
        }
        else{
            return null;
        }
    }

  @Override
      public Factura modificar(int numeroNuevo, Factura facturaNuevo) {
        var posicion=this.buscarPosicion(this.buscarNFactura(numeroNuevo));
        this.listar().get(posicion).setUnCliente(facturaNuevo.getUnCliente());
        this.listar().get(posicion).setDireccion(facturaNuevo.getDireccion());
        this.listar().get(posicion).setUnProducto(facturaNuevo.getUnProducto());
        this.listar().get(posicion).setFecha(facturaNuevo.getFecha());
        this.listar().get(posicion).setTotal(facturaNuevo.getTotal());
        this.listar().get(posicion).setCantidad(facturaNuevo.getCantidad());
        return facturaNuevo;
    }     

   
    
       public void replaceFile() throws IOException
    {
        String file_name = folder + "/Jugador.txt";
        Path path = Paths.get(file_name);
        try {
            Files.delete(path);
            for (int i = 0; i < listJugador.size(); i++)
            {
            crear(listJugador.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        listJugador = listar();        
    }

     @Override
    public Factura eliminar(int nuevoFactura) {
        Factura factura = this.buscarNFactura(nuevoFactura);
        var posicion = this.buscarPosicion(factura);
        this.listar().remove(posicion);
        return factura;
    }

    @Override
   public Factura buscarNFactura(int nuevoFactura) {
        Factura factura = null;
        for(var p:this.listar()){
            if(nuevoFactura==p.getNumFactura()){
                factura = p;
                break;
            }
        }
        return factura;
    }

    @Override
     public int buscarPosicion(Factura factura) {
        
        int posicion =-1;
        for(var p:this.facturaList){
            posicion++;
            if(p.getNumFactura()==factura.getNumFactura()){
                break;
            }
        }
        return posicion;
    }
    private boolean exist(int codigo) throws IOException
    {
        var a = listar();
        if (a == null) return false;
        boolean result = false;
        for (Factura e : a)
        {
            if (e.getCodigo() == codigo)
            {
                result = true;
                break; 
            }            
        }
        return result;
    }
}
