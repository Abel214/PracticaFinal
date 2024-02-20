/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista.listas.tablas;

import Controlador.TDA.grafos.GrafoBellman;

import Modelo.PozosLuz;

import controlador.Utiles.UtilesFoto;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author Alejandro
 */
public class ModeloAdyacenciaBellman extends AbstractTableModel {


    private GrafoBellman<PozosLuz> grafoBellman;

    @Override
    public int getRowCount() {
        return grafoBellman.num_vertice();
    }

    @Override
    public int getColumnCount() {
        return grafoBellman.num_vertice() + 1;
    }

    @Override
    public String getColumnName(int column) {
        if (column == 0) {
            return "Pozos Luz ";
        } else {
            try {
                return grafoBellman.getLabelE(column).toString();
            } catch (Exception e) {
                return "";
            }
        }
    }

    public GrafoBellman<PozosLuz> getGrafoBellman() {
        return grafoBellman;
    }

    public void setGrafoBellman(GrafoBellman<PozosLuz> grafoBellman) {
        this.grafoBellman = grafoBellman;
    }

    
    @Override
    public Object getValueAt(int i, int i1) {
        try {
            if (i1 == 0) {
                return grafoBellman.getLabelE(i + 1).toString();
            } else {
                PozosLuz o = grafoBellman.getLabelE(i + 1);
                PozosLuz d = grafoBellman.getLabelE(i1);
                if (grafoBellman.isEdgeE(o, d)) {
                    return UtilesFoto.redondear(grafoBellman.peso_arista(i + 1, i1)).toString();
                } else {
                    return "--";
                }
            }
        } catch (Exception e) {
            return "";
        }
    }
}
