/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.EstadoPozo;
import controlador.TDA.listas.DynamicList;



/**
 *
 * @author Alejandro
 */
public class EstadoControl {
    private DynamicList<EstadoPozo> pozos;

    public EstadoControl() {
        pozos = new DynamicList<>();
        pozos.add(new EstadoPozo(1, "Bueno", "Pozo en buen estado"));
        pozos.add(new EstadoPozo(2, "Regular", "Pozo en un estado regular"));
        pozos.add(new EstadoPozo(3, "Malo", "Pozo en un mal estado"));
    }

    public DynamicList<EstadoPozo> getPozos() {
        return pozos;
    }

    public void setPozos(DynamicList<EstadoPozo> pozos) {
        this.pozos = pozos;
    }

    

    
}
