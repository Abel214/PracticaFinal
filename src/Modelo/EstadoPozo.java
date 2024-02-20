/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Alejandro
 */
public class EstadoPozo {
    private Integer Id;
    private String estado;
    private String descripcion;

    public EstadoPozo(Integer Id, String estado, String descripcion) {
        this.Id = Id;
        this.estado = estado;
        this.descripcion = descripcion;
    }
    public EstadoPozo() {
        this.Id = null;
        this.estado = null;
        this.descripcion = null;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return  estado;
    }
    
           
}
