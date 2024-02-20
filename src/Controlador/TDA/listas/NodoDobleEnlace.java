/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.TDA.listas;

/**
 *
 * @author Alejandro
 */
public class NodoDobleEnlace<E> {
    
    private NodoDobleEnlace<E> preview;
    private E info;
    private NodoDobleEnlace<E> next;

    public NodoDobleEnlace(E info) {
        this.info = info;
        next = null;
        preview = null;
    }

    public NodoDobleEnlace(E info, NodoDobleEnlace<E> next, NodoDobleEnlace<E> preview) {
        this.info = info;
        this.next = next;
        this.preview = preview;
    }

    public NodoDobleEnlace() {
        next = null;
        info = null;
        preview = null;
    }

    public E getInfo() {
        return info;
    }

    public void setInfo(E info) {
        this.info = info;
    }

    public NodoDobleEnlace<E> getNext() {
        return next;
    }

    public void setNext(NodoDobleEnlace<E> next) {
        this.next = next;
    }

    public NodoDobleEnlace<E> getPreview() {
        return preview;
    }

    public void setPreview(NodoDobleEnlace<E> preview) {
        this.preview = preview;
    }
}
