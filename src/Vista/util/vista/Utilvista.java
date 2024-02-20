/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista.util.vista;



import Controlador.EstadoControl;
import Controlador.listas.Exception.EmptyException;
import Modelo.EstadoPozo;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;


/**
 *
 * @author Alejandro
 */
public class Utilvista {
    
    public static void cargarcomboRolesL(JComboBox cbx) throws EmptyException{
        EstadoControl rc = new EstadoControl();
        cbx.removeAllItems();
        if (rc.getPozos().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Lista vacia");
        }else{
            for (int i = 0; i < rc.getPozos().getLength(); i++) {
                cbx.addItem(rc.getPozos().getInfo(i));
            }
        }
    }
    
    
    
    public static EstadoPozo obtenerEstadoControl(JComboBox cbx){
        return (EstadoPozo) cbx.getSelectedItem();
    }
}
