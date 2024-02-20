/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.listas.tablas;


import Modelo.PozosLuz;
import controlador.TDA.listas.DynamicList;
import java.io.File;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author Alejandro
 */
public class ModeloTablaPozo extends AbstractTableModel {
     private DynamicList<PozosLuz> pozos = new DynamicList<>();

    public DynamicList<PozosLuz> getPozos() {
        return pozos;
    }

    public void setPozos(DynamicList<PozosLuz> pozos) {
        this.pozos = pozos;
    }
    @Override
    public int getRowCount() {
        return pozos.getLength();
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getColumnCount() {
        return 5;
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object getValueAt(int i, int i1) {
        PozosLuz e = null;
        try {
            e = pozos.getInfo(i);
        } catch (Exception ex) {
        }
        switch (i1) {
            case 0: return (e != null) ? e.getDescripcion(): "";
            case 1: return (e != null) ? e.getEstado(): "";
            case 2: return (e != null) ? e.getProfundidad(): ""; 
            case 3: return (e != null) ? e.getFechaMantenimiento(): ""; 
            case 4: return (e != null) ? e.getCoordenada() : "";     
            default:
                return null;
        }
    }
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "POZO LUZ";
            case 1: return "ESTADO DEL POZO";
            case 2: return "PROFUNDIDAD POZO";
            case 3: return "FECHA MANTENIMIENTO";
            case 4: return "COORDENADAS";
            default:
                return null;
        }
    }
    
}
