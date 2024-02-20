/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Alejandro
 */
public class Coordenada {
    private Integer id;
    private Double longitud;
    private Double latitud;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Coordenada( Double longitud, Double latitud) {
        
        this.longitud = longitud;
        this.latitud = latitud;
    }
     public Coordenada() {
        this.id = null;
        this.longitud = null;
        this.latitud = null;
    }

    @Override
    public String toString() {
        return "longitud:" + longitud + "; latitud:" + latitud ;
    }
    
}
