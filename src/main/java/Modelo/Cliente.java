/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Gus-Gus
 */
public class Cliente {
    
    private int numCedula;
    private String nombreCliente;
    private String apellidoCliente;
    private String direccion;
    private int telefono;
    private String correo;

    public Cliente(int numCedula, String nombreCliente, String apellidoCliente, String direccion, int telefono, String correo) {
        this.numCedula = numCedula;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
    }

    public int getNumCedula() {
        return numCedula;
    }

    public void setNumCedula(int numCedula) {
        this.numCedula = numCedula;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Cliente{" + "numCedula=" + numCedula + ", nombreCliente=" + nombreCliente + ", apellidoCliente=" + apellidoCliente + ", direccion=" + direccion + ", telefono=" + telefono + ", correo=" + correo + '}';
    }

    public int getCodigo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object getNombre() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
