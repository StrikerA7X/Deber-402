/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicio;

import Modelo.Cliente;
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
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adrian
 */
public class ClienteServicio implements IClienteServicio {
    private List<Cliente> listCliente;
    private String folder;
    
    public ClienteServicio() throws IOException
    {
        folder = "C:/Equipo";
        listCliente= new ArrayList<>();
        listCliente = listar();        
    }
    @Override
    public Cliente crear(Cliente equipo) {
        try {
            if (exist(cliente.getCodigo(), cliente.getNombre()) == false)
            {
                String path = folder + "/Equipo.txt";
                ObjectOutputStream archivo = null;
                try {
                    archivo = new ObjectOutputStream(new FileOutputStream(path,true));
                    archivo.writeObject(cliente);
                    archivo.close();
                } catch (IOException e) {
                    archivo.close();
                }
                this.listcliente = listar();
                return cliente;
            }
            else
            {
                throw new RuntimeException("Ya existe un equipo con este código.");
            }
        } catch (IOException ex) {
            Logger.getLogger(ClienteServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (RuntimeException e)
        {
            throw new RuntimeException("Ya existe un equipo con este código.");
        }
        return cliente;
    }

    @Override
    public List<Cliente> listar() throws IOException{
        String path = folder + "/Equipo.txt";  
        if (new File(path).exists() == true)
        {
           var computadorList = new ArrayList<Cliente>();        
        FileInputStream file = new FileInputStream(path);
        ObjectInputStream archivo = null;
        try{
            while(file.available()>0)
            {
                archivo = new ObjectInputStream(file);
                Cliente computador = (Cliente) archivo.readObject(); 
                computadorList.add(computador);
            }    
            if (archivo != null) archivo.close();            
            file.close();
        }catch(IOException e){
            archivo.close();
            file.close();
        } catch (ClassNotFoundException ex) {        
            Logger.getLogger(ClienteServicio.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return computadorList; 
        }
        else{
            return null;
        }
        
    }

   @Override
    public Cliente modificar(long numCedulaNuevo, Cliente clienteNuevo) throws IOException {
       var posicion = this.buscarPosicion(this.buscarPorCedula(numCedulaNuevo));
       this.listar().get(posicion).setNombreCliente(clienteNuevo.getNombreCliente());
       this.listar().get(posicion).setApellidoCliente(clienteNuevo.getApellidoCliente());
       this.listar().get(posicion).setDireccion(clienteNuevo.getDireccion());
       this.listar().get(posicion).setTelefono(clienteNuevo.getTelefono());
       this.listar().get(posicion).setCorreo(clienteNuevo.getCorreo());
       return clienteNuevo;
       
    }
      
    
    public void replaceFile() throws IOException
    {
        String file_name = folder + "/Equipo.txt";
        Path path = Paths.get(file_name);
        try {
            Files.delete(path);
            for (int i = 0; i < listCliente.size(); i++)
            {
            crear(listCliente.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        listCliente = listar();        
    }

    @Override
    public Cliente eliminar(int codigoEquipo) throws IOException{
        if (exist(codigoEquipo) == true)
        {
        Cliente cliente=this.buscarPorCodigo(codigoCliente);
        var posicion=this.buscarPosicion(cliente);        
        listCliente.remove(posicion);
            replaceFile();
        return cliente;
        }
        else
        {
            throw new RuntimeException("No se ha encontrado un equipo con ese código");
        }
    }

    @Override
  public Cliente buscarPorCedula(long numCedula) {
      Cliente cliente = null;
      for(var p:this.listar()){
          if(numCedula==p.getNumCedula()){
              cliente=p;
              break;
          }
      }
      return cliente;
    }


  @Override
    public int buscarPosicion(Cliente cliente) {
        int posicion =-1;
        for(var p:this.clienteList){
            posicion++;
            if(p.getNumCedula()==cliente.getNumCedula()){
                break;
            }
        }
        return posicion;
    }
    
    @Override
    public int count() {
        if (listCliente == null)
        {
            return 0;
        }
        else{
        return listCliente.size();
        }
    }
    
    public boolean exist(int codigo) throws IOException
    {
        var a = listar();
        if (a == null) return false;
        boolean result = false;
        for (Cliente e : a)
        {
            if (e.getCodigo() == codigo)
            {
                result = true;
                break; 
            }            
        }
        return result;
    }
    public boolean exist(int codigo, String nombre) throws IOException
    {
        var a = listar();
        if (a == null) return false;
        boolean result = false;
        for (Cliente e : a)
        {
            if (e.getCodigo() == codigo)
            {
                result = true;
                break; 
            }
            else if (e.getNombre().equals(nombre))
            {
                result  = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Cliente eliminar(long numCedula) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    

 
    
}
   
