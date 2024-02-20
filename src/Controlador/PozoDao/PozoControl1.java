/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.PozoDao;

import Controlador.TDA.grafos.GrafoBellman;
import Controlador.TDA.grafos.GrafoFloydBellman;
import Controlador.listas.Exception.EmptyException;
import Modelo.PozosLuz;
import controlador.DAO.DaoImplement;
import controlador.TDA.grafos.Adyacencia;
import controlador.TDA.grafos.GrafoEtiquetadoNoDirigido;
import controlador.TDA.grafos.GrafosEtiquetadosDirigidos;
import controlador.TDA.listas.DynamicList;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;

/**
 *
 * @author Alejandro
 */
public class PozoControl1 extends DaoImplement<PozosLuz> {
     private DynamicList<PozosLuz>list=new DynamicList<>();
    private GrafoFloydBellman<PozosLuz> grafo;
    private PozosLuz pozo;
    public PozoControl1(Class<PozosLuz> clazz) {
        super(PozosLuz.class);
    }

    public PozoControl1() {
        super(PozosLuz.class);
    }


    public GrafoFloydBellman<PozosLuz> getGrafo () throws EmptyException {
        if (grafo == null) {
            DynamicList<PozosLuz> list = getList();
            System.out.println(list);
            if (!list.isEmpty()) {
                grafo = new GrafoFloydBellman<>(list.getLength(), PozosLuz.class);
                for (int i = 0; i < list.getLength(); i++) {
                    grafo.labelVertice((i + 1), list.getInfo(i));
                }
            }
        }
        
        return grafo;
    }

    public DynamicList<PozosLuz> getList() {
         if (list.isEmpty()) {
            list = all();
        }
        return list;
    }

    public void setList(DynamicList<PozosLuz> list) {
        this.list = list;
    }

    public PozosLuz getPozo() {
         if (pozo == null) {
            pozo = new PozosLuz();
        }
        return pozo;
    }

    public void setPozo(PozosLuz escuela) {
        this.pozo = escuela;
    }
    public Boolean persist() {
        pozo.setId(all().getLength()+ 1);
        return persist(pozo);
    }
    
    
    public void guardarGrafoFloydBellman() throws Exception{
        getConection().toXML(grafo, new FileWriter("files/grafo.json"));
    }
    
    public void loadGrapgFloydBellman() throws Exception{
        
        grafo = (GrafoFloydBellman<PozosLuz>)getConection().fromXML(new FileReader("files/grafo.json"));
        list.reset();
        for(int i = 1; i <= grafo.num_vertice(); i++){
            list.add(grafo.getLabelE(i));
        }
    }


    
    
    @Override
    public String toString() {
        return pozo.getDescripcion();
    }
    
}
