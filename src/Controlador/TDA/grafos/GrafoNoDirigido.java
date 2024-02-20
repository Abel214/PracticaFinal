/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.TDA.grafos;

import Controlador.grafos.exception.VerticeException;
import controlador.TDA.listas.DynamicList;
import controlador.utiles.Utilidades;

/**
 *
 * @author Alejandro
 */
public class GrafoNoDirigido extends Grafo {
     private Integer num_vertice;
    private Integer num_aristas;
    private DynamicList<Adyacencia> listaAdyacencias[];

    public GrafoNoDirigido(Integer num_vertice) {
        this.num_vertice = num_vertice;
        this.num_aristas = 0;
        this.listaAdyacencias = new DynamicList[num_vertice + 1];
        for (int i = 0; i <= this.num_vertice; i++) {
            listaAdyacencias[i] = new DynamicList<>();
        }
    }
    @Override
    public Integer num_vertice() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            return num_vertice;
    }

    @Override
    public Integer num_aristas() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return num_vertice;
    }

    @Override
    public Boolean existe_arista(Integer v1, Integer v2) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
         Boolean band = false;
        if (v1.intValue() <= num_vertice && v2.intValue() <= num_vertice) {
            DynamicList<Adyacencia> listaA = listaAdyacencias[v1];
            for (int i = 0; i < listaA.getLength(); i++) {
                Adyacencia a = listaA.getInfo(i);
                if ((a.getDestino().intValue() == v2.intValue()) || (a.getDestino().intValue() == v1.intValue())) {
                    band = true;
                    break;
                }
            }
        } else {
            throw new VerticeException();
        }
        return band;
    }

    @Override
    public Double peso_arista(Integer v1, Integer v2) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Double peso = Double.NaN;
        if (existe_arista(v1, v2)) {
            DynamicList<Adyacencia> listaA = listaAdyacencias[v1];
            for (int i = 0; i < listaA.getLength(); i++) {
                Adyacencia a = listaA.getInfo(i);
                if ((a.getDestino().intValue() == v2.intValue()) || (a.getDestino().intValue() == v1.intValue())) {
                    peso = a.getPeso();
                    break;
                }
            }
        }
        return peso;
    }

    @Override
    public void insertar_arista(Integer v1, Integer v2, Double peso) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
             if (v1.intValue() <= num_vertice && v2.intValue() <= num_vertice) {
            if (!existe_arista(v1, v2)) {
                num_aristas++;
                
                listaAdyacencias[v1].add(new Adyacencia(v2, peso));
                listaAdyacencias[v2].add(new Adyacencia(v1, peso)); // Agregar arista en la dirección opuesta
            }
        } else {
            throw new VerticeException();
        }
    }

    @Override
    public void insertar_arista(Integer v1, Integer v2) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            insertar_arista(v1, v2, Double.NaN);
    }

    @Override
    public void adyacencia(Integer v1, Integer v2) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
    }

    @Override
    public DynamicList<Adyacencia> adyacentes(Integer v1) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
             return listaAdyacencias[v1];
    }
    public static void main(String[] args) throws VerticeException {
        Grafo f = new GrafoNoDirigido(6);
        System.out.println(f);
        try {
            f.insertar_arista(1, 3, 10.0);
            f.insertar_arista(4, 5, 10.0);
            f.insertar_arista(2, 2, 20.0);
            System.out.println(f);
            PaintGraph p=new PaintGraph();
            p.updateFile(f);
            Utilidades.abrirNavegadorPredeterminadorWindows("d3/grafo.html");
        } catch (Exception e) {
            throw new VerticeException();
        }
    }
}
