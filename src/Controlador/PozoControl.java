/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.PozosLuz;
import controlador.TDA.listas.DynamicList;

/**
 *
 * @author Alejandro
 */
public class PozoControl {
    private PozosLuz pozo = new PozosLuz();
    private DynamicList<PozosLuz> pozos;

    public PozoControl(PozosLuz pozo) {
        this.pozo = pozo;
    }

    public PozoControl() {
        this.pozos= new DynamicList<>();
        
    }

    public PozosLuz getPozo() {
         if (pozo == null) {
            pozo = new PozosLuz();
        }
        return pozo;
    }

    public void setPozo(PozosLuz pozo) {
        this.pozo = pozo;
    }

    public DynamicList<PozosLuz> getPozos() {
        return pozos;
    }

    public void setPozos(DynamicList<PozosLuz> pozos) {
        this.pozos = pozos;
    }

    
    

    //Metodo que permite guardar
    public Boolean guardar() {
        
        try {
            getPozo().setId(getPozos().getLength());
            getPozos().add(getPozo());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
