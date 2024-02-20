/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.PozoDao;

import Modelo.EstadoPozo;
import controlador.DAO.DaoImplement;
import controlador.TDA.listas.DynamicList;

/**
 *
 * @author Alejandro
 */
public class EstadoControl1 extends DaoImplement<EstadoPozo>{
    private DynamicList<EstadoPozo> pozos;
    private EstadoPozo pozo;

    public EstadoControl1() {
        super(EstadoPozo.class);
    }

    public DynamicList<EstadoPozo> getPozos() {
        return pozos = all();
    }

    public void setPozos(DynamicList<EstadoPozo> roles) {
        this.pozos = roles;
    }

    public EstadoPozo getPozo() {
        if (pozo == null) {
            pozo = new EstadoPozo();
        }
        return pozo;
    }

    public void setPozo(EstadoPozo rol) {
        this.pozo = rol;
    }
    
    public Boolean persist(){
        pozo.setId(all().getLength() + 1);
        return persist(pozo);
    }
}
