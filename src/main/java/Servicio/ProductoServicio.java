
package Servicio;

import Modelo.Producto;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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



public class ProductoServicio implements IProductoServicio
{
    private List<Producto> listProducto;
    private String folder;
    public ProductoServicio() throws IOException
    {
        folder = "C:/Equipo";
        listProducto= new ArrayList<>();
        listProducto = listar();
    }

    @Override
    public Producto crear(Producto producto) {
         try {
            if (exist(producto.getCodigo()) == false)
            {
                String path = folder + "/Partido.txt";
                ObjectOutputStream archivo = null;
                try {
                    archivo = new ObjectOutputStream(new FileOutputStream(path,true));
                    archivo.writeObject(producto);
                    archivo.close();
                } catch (IOException e) {
                    archivo.close();
                }
                this.listProducto = listar();
                return producto;
            }
            else
            {
                throw new RuntimeException("Ya existe este código.");
            }
        } catch (IOException ex) {
            Logger.getLogger(ClienteServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (RuntimeException e)
        {
            throw new RuntimeException("Ya existe este código.");
        }
        return producto;        
    }

    @Override
    public List<Producto> listar() throws IOException {
        String path = folder + "/Partido.txt";  
        if (new File(path).exists() == true)
        {
           var computadorList = new ArrayList<Producto>();        
        FileInputStream file = new FileInputStream(path);
        ObjectInputStream archivo = null;
        try{
            while(file.available()>0)
            {
                archivo = new ObjectInputStream(file);
                Producto computador = (Producto) archivo.readObject(); 
                computadorList.add(computador);
            }    
            if (archivo != null) archivo.close();            
            file.close();
        }catch(IOException e){
            archivo.close();
            file.close();
        } catch (ClassNotFoundException ex) {        
            Logger.getLogger(ProductoServicio.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return computadorList; 
        }
        else{
            return null;
        }
    }
    
      @Override
     public Producto modificar(int codigoNuevo, Producto productoNuevo) {
        var posicion = this.buscarPosicion(this.buscarPorCodigo(codigoNuevo));
        this.listar().get(posicion).setNombreProducto(productoNuevo.getNombreProducto());
        this.listar().get(posicion).setTipoProducto(productoNuevo.getTipoProducto());
        this.listar().get(posicion).setCatidadProducto(productoNuevo.getCatidadProducto());
        this.listar().get(posicion).setPrecioProducto(productoNuevo.getPrecioProducto());
        this.listar().get(posicion).setProductoStok(productoNuevo.getProductoStok());

        
        return productoNuevo;
    }
    
    public void replaceFile() throws IOException
    {
        String file_name = folder + "/Partido.txt";
        Path path = Paths.get(file_name);
        try {
            Files.delete(path);
            for (int i = 0; i < listProducto.size(); i++)
            {
            crear(listProducto.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        listProducto = listar();        
    }

    @Override
    public Producto eliminar(int codigoPartido) throws IOException {
         if (exist(codigoPartido) == true)
        {
        Producto producto=this.buscarPorCodigo(codigoProducto);
        var posicion=this.buscarPosicion(producto);        
        listPartido.remove(posicion);
            replaceFile();
        return producto;
        }
        else
        {
            throw new RuntimeException("No se ha encontrado un partido con ese código");
        }
    }

    @Override
      public Producto buscarPorCodigo(int codigoProducto) {
        Producto producto = null;
        for(var p:this.listar()){
            if(codigoProducto==p.getCodigoProducto()){
                producto = p;
                break;
            }
        }
        return producto;
    }

        @Override
    public int buscarPosicion(Producto producto) {
        
        int posicion = -1;
        for(var p:this.productoList){
            posicion++;
            if(p.getCodigoProducto()==producto.getCodigoProducto()){
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
        for (Producto e : a)
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
