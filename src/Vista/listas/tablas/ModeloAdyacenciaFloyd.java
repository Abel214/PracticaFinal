/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.listas.tablas;

import Controlador.TDA.grafos.GrafoFloydBellman;
import Modelo.PozosLuz;
import controlador.Utiles.UtilesFoto;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author Alejandro
 */
public class ModeloAdyacenciaFloyd extends AbstractTableModel {

    private GrafoFloydBellman<PozosLuz> grafoFloyd;

    @Override
    public int getRowCount() {
        return grafoFloyd.num_vertice();
    }

    @Override
    public int getColumnCount() {
        return grafoFloyd.num_vertice() + 1;
    }

    @Override
    public String getColumnName(int column) {
        if (column == 0) {
            return "Pozos Luz ";
        } else {
            try {
                return grafoFloyd.getLabelE(column).toString();
            } catch (Exception e) {
                return "";
            }
        }
    }

    public GrafoFloydBellman<PozosLuz> getGrafo1() {
        return grafoFloyd;
    }

    public void setGrafo1(GrafoFloydBellman<PozosLuz> grafo1) {
        this.grafoFloyd = grafo1;
    }
    
    

    @Override
    public Object getValueAt(int i, int i1) {
        try {
            if (i1 == 0) {
                return grafoFloyd.getLabelE(i + 1).toString();
            } else {
                PozosLuz o = grafoFloyd.getLabelE(i + 1);
                PozosLuz d = grafoFloyd.getLabelE(i1);
                if (grafoFloyd.isEdgeE(o, d)) {
                    return UtilesFoto.redondear(grafoFloyd.peso_arista(i + 1, i1)).toString();
                } else {
                    return "--";
                }
            }
        } catch (Exception e) {
            return "";
        }
    }
}
