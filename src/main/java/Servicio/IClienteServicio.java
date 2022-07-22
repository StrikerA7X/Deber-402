/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Servicio;

import Modelo.Cliente;
import java.util.List;

/**
 *
 * @author Gus-Gus
 */
public interface IClienteServicio {
    
    public Cliente crear(Cliente cliente);
    public Cliente modificar(long numCedulaNuevo, Cliente cliente);
    public Cliente eliminar (long numCedula);
    public Cliente buscarPorCedula(long numCedula);
    public int buscarPosicion(Cliente cliente);
    public List<Cliente> listar();
}
