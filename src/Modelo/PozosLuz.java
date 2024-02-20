/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Alejandro
 */
public class PozosLuz {
    private Integer id;
    private EstadoPozo estado;
    private String imagen;
    private String descripcion;
    private Double profundidad;
    private String fechaMantenimiento;
    private Coordenada coordenada;
    private String auxiliar;

    public PozosLuz(Integer id, EstadoPozo estado, String imagen, String auxiliar,String descripcion, Double profundidad, String fechaMantenimiento, Coordenada coordenada) {
        this.id = id;
        this.estado = estado;
        this.imagen = imagen;
        this.auxiliar=auxiliar;
        this.descripcion = descripcion;
        this.profundidad = profundidad;
        this.fechaMantenimiento = fechaMantenimiento;
        this.coordenada = coordenada;
    }
    public PozosLuz() {
        this.id = null;
        this.estado = null;
        this.imagen = null;
        this.descripcion = null;
        this.profundidad = null;
        this.fechaMantenimiento = null;
        this.coordenada = null;
        this.auxiliar=null;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EstadoPozo getEstado() {
        return estado;
    }

    public void setEstado(EstadoPozo estado) {
        this.estado = estado;
    }


    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(Double profundidad) {
        this.profundidad = profundidad;
    }

    public String getFechaMantenimiento() {
        return fechaMantenimiento;
    }

    public void setFechaMantenimiento(String fechaMantenimiento) {
        this.fechaMantenimiento = fechaMantenimiento;
    }

    public Coordenada getCoordenada() {
        if(coordenada==null){
            coordenada=new Coordenada();
        }
        return coordenada;
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }

    public String getAuxiliar() {
        return auxiliar;
    }

    public void setAuxiliar(String auxiliar) {
        this.auxiliar = auxiliar;
    }
    

    @Override
    public String toString() {
        return descripcion ;
    }
    
    
}
