/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vsta;

import Controlador.PozoControl;
import Controlador.PozoDao.PozoControl1;
import Modelo.Coordenada;
import Vista.util.vista.Utilvista;
import controlador.Utiles.UtilesFoto;
import java.io.File;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.edisoncor.gui.textField.TextFieldRound;
import vista.listas.tablas.ModeloTablaPozo;

/**
 *
 * @author Alejandro
 */
public class FrmPozos extends javax.swing.JFrame {

    private File fimagenPozo;
    private File fimagenAuxiliar;
    private ModeloTablaPozo mtp = new ModeloTablaPozo();
    PozoControl pozoControl = new PozoControl();
    PozoControl1 pozoDao = new PozoControl1();

    /**
     * Creates new form FrmPozos
     */
    public FrmPozos() {
        initComponents();
        this.setLocationRelativeTo(null);
        limpiar();
    }

    private Boolean verificar() {
        return (!txtDescripcion.getText().trim().isEmpty()
                && !txtFecha.getText().trim().isEmpty()
                && !txtPozo.getText().trim().isEmpty()
                && !txtProfundidad.getText().trim().isEmpty()
                && !txtLongitud.getText().trim().isEmpty()
                && !txtLatitud.getText().trim().isEmpty());
    }

    private String nombreArchivo(String file) {
        String aux = UUID.randomUUID().toString();
        aux += "." + UtilesFoto.extension(file);
        return aux;
    }

    private File CargarFoto() throws Exception {
        File obj = null;
        JFileChooser chooser = new JFileChooser();
        chooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter
                = new FileNameExtensionFilter("Imagenes", "jpg", "png", "jpeg");
        chooser.addChoosableFileFilter(filter);
        Integer resp = chooser.showOpenDialog(this);
        if (resp == JFileChooser.APPROVE_OPTION) {
            obj = chooser.getSelectedFile();
            //System.out.println("\nCargado: " + obj.getName());
        } else {
            //System.out.println("\nCargado: " + obj.getName());
        }
        return obj;
    }

    private void guardar() throws Exception {
        if (verificar()) {
            String pozo = nombreArchivo(fimagenPozo.getName());
//            String auxiliar = nombreArchivo(fimagenAuxiliar.getName());
            String auxiliar = (fimagenAuxiliar != null) ? nombreArchivo(fimagenAuxiliar.getName()) : "D:\\Desktop\\Simulacion 1\\Cat-pain.jpg";
            pozoControl.getPozo().setAuxiliar(auxiliar);

            pozoControl.getPozo().setDescripcion(txtDescripcion.getText());
            pozoControl.getPozo().setEstado(Utilvista.obtenerEstadoControl(cbxEstado));
            pozoControl.getPozo().setImagen(pozo);
            pozoControl.getPozo().setProfundidad(Double.parseDouble(txtProfundidad.getText()));
            pozoControl.getPozo().setFechaMantenimiento(txtFecha.getText());
            pozoControl.getPozo().setCoordenada(new Coordenada(Double.valueOf(txtLongitud.getText()), Double.valueOf(txtLatitud.getText())));
            pozoControl.getPozo().setAuxiliar(auxiliar);
            //fileEscuela.setGrafo(new GrafosEtiquetadosNoDirigidos<>());
            UtilesFoto.copiarArchivo(fimagenPozo, new File("Foto/" + pozo));
//            UtilesFoto.copiarArchivo(fimagenAuxiliar, new File("Foto/" + auxiliar));
            if (fimagenAuxiliar != null) {
                UtilesFoto.copiarArchivo(fimagenAuxiliar, new File("Foto/" + auxiliar));
            }
            if (pozoControl.guardar()) {
                pozoDao.persist(pozoControl.getPozo());
                JOptionPane.showMessageDialog(null, "Datos guardados");
                cargarTabla();
                limpiar();
                pozoControl.setPozo(null);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo guardar, hubo un error");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Falta llenar campos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarTabla() {
        mtp.setPozos(pozoDao.all());
        pozoControl.setPozos(pozoDao.all());
        tblMostrar.setModel(mtp);
        tblMostrar.updateUI();
    }

    private void cargarVista() {
        int fila = tblMostrar.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Escoja un registro de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                pozoControl.setPozo(pozoDao.getList().getInfo(fila));
                txtDescripcion.setText(pozoControl.getPozo().getDescripcion());
                txtFecha.setText(pozoControl.getPozo().getFechaMantenimiento());
                txtProfundidad.setText(String.valueOf(pozoControl.getPozo().getProfundidad()));
                txtPozo.setText(pozoControl.getPozo().getImagen());
                txtPozo1.setText(pozoControl.getPozo().getAuxiliar());
                txtLongitud.setText(pozoControl.getPozo().getCoordenada().getLongitud().toString());
                txtLatitud.setText(pozoControl.getPozo().getCoordenada().getLatitud().toString());
                fimagenPozo = new File("Foto/" + pozoControl.getPozo().getImagen());
                fimagenAuxiliar = new File("Foto/" + pozoControl.getPozo().getAuxiliar());

            } catch (Exception ex) {
                System.out.println("Error al cargar" + ex);
            }
        }
    }

    private void limpiar() {
        try {
            Utilvista.cargarcomboRolesL(cbxEstado);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        txtDescripcion.setText("");
        txtFecha.setText("");
        txtPozo.setText("");
        txtPozo1.setText("");
        txtLongitud.setText("");
        txtLatitud.setText("");
        txtProfundidad.setText(" ");
        fimagenPozo = null;
        fimagenAuxiliar = null;
        cargarTabla();
        pozoControl.setPozo(null);
        txtDescripcion.setEnabled(true);
        txtLongitud.setEnabled(true);
        txtLatitud.setEnabled(true);

    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new org.edisoncor.gui.panel.Panel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cbxEstado = new javax.swing.JComboBox<>();
        btnCargar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMostrar = new javax.swing.JTable();
        btnCargar1 = new javax.swing.JButton();
        btnSeleccionar = new org.edisoncor.gui.button.ButtonAction();
        btnGuardar = new org.edisoncor.gui.button.ButtonAction();
        txtLongitud = new org.edisoncor.gui.textField.TextFieldRound();
        txtDescripcion = new org.edisoncor.gui.textField.TextFieldRound();
        txtProfundidad = new org.edisoncor.gui.textField.TextFieldRound();
        txtFecha = new org.edisoncor.gui.textField.TextFieldRound();
        txtPozo = new org.edisoncor.gui.textField.TextFieldRound();
        txtPozo1 = new org.edisoncor.gui.textField.TextFieldRound();
        textFieldRound7 = new org.edisoncor.gui.textField.TextFieldRound();
        txtLatitud = new org.edisoncor.gui.textField.TextFieldRound();
        textFieldRound9 = new org.edisoncor.gui.textField.TextFieldRound();
        btnModificar = new org.edisoncor.gui.button.ButtonAction();
        btnAdyacencias = new org.edisoncor.gui.button.ButtonAction();
        btnAtras = new org.edisoncor.gui.button.ButtonAction();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel1.setColorPrimario(new java.awt.Color(255, 204, 204));
        panel1.setColorSecundario(new java.awt.Color(204, 255, 204));
        panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("POZOS DE LUZ");
        panel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 350, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 0, 102));
        jLabel2.setText("DESCRIPCIÓN DEL POZO:");
        panel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 60, 190, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 0, 102));
        jLabel3.setText("IMAGEN:");
        panel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 120, 67, -1));

        jLabel4.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 0, 102));
        jLabel4.setText("FECHA DE MANTENIMIENTO:");
        panel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 0, 102));
        jLabel5.setText("PROFUNDIDAD DEL POZO:");
        panel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 166, 190, -1));

        jLabel6.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("COORDENADAS DEL POZO");
        panel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 300, -1, -1));

        jLabel7.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 0, 102));
        jLabel7.setText("LATITUD:");
        panel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 190, -1));

        jLabel8.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 0, 102));
        jLabel8.setText("LONGITUD:");
        panel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 330, 90, -1));

        jLabel9.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 0, 102));
        jLabel9.setText("ESTADO DEL POZO:");
        panel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 117, 190, -1));

        jLabel10.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 0, 102));
        jLabel10.setText("IMAGEN2:");
        panel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, 80, -1));

        jLabel11.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 0, 0));
        jLabel11.setText("¡SI ES NECESARIO CARGE OTRA FOTO!");
        panel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 250, -1, -1));

        cbxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        panel1.add(cbxEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 114, 174, -1));

        btnCargar.setText("CARGAR");
        btnCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarActionPerformed(evt);
            }
        });
        panel1.add(btnCargar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 120, -1, -1));

        tblMostrar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblMostrar);

        panel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 430, -1, 170));

        btnCargar1.setText("CARGAR");
        btnCargar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargar1ActionPerformed(evt);
            }
        });
        panel1.add(btnCargar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 270, -1, -1));

        btnSeleccionar.setText("SELECCIONAR");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });
        panel1.add(btnSeleccionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 370, -1, -1));

        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        panel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 130, -1));
        panel1.add(txtLongitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 330, 210, 30));
        panel1.add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 410, 40));
        panel1.add(txtProfundidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, 410, 30));
        panel1.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, 410, 30));

        txtPozo.setEnabled(false);
        txtPozo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPozoMouseClicked(evt);
            }
        });
        panel1.add(txtPozo, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, 110, 30));

        txtPozo1.setEnabled(false);
        txtPozo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPozo1MouseClicked(evt);
            }
        });
        panel1.add(txtPozo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 270, 220, 30));
        panel1.add(textFieldRound7, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 270, 220, 30));
        panel1.add(txtLatitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 330, 220, 30));
        panel1.add(textFieldRound9, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 330, 220, 30));

        btnModificar.setText("MODIFICAR");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        panel1.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 370, -1, -1));

        btnAdyacencias.setText("VER ADYACENCIAS Y EL GRAFO GENERADO");
        btnAdyacencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdyacenciasActionPerformed(evt);
            }
        });
        panel1.add(btnAdyacencias, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 610, 330, -1));

        btnAtras.setText("Regresar al Menú");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });
        panel1.add(btnAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 610, 170, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarActionPerformed
        try {
            fimagenPozo = CargarFoto();
            if (fimagenPozo != null) {
                txtPozo.setText(fimagenPozo.getAbsolutePath());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnCargarActionPerformed

    private void btnCargar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargar1ActionPerformed
        try {
            fimagenAuxiliar = CargarFoto();
            if (fimagenAuxiliar != null) {
                txtPozo1.setText(fimagenAuxiliar.getAbsolutePath());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }


    }//GEN-LAST:event_btnCargar1ActionPerformed

    private void txtPozoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPozoMouseClicked
        if (evt.getClickCount() >= 2) {
            new FrmFoto(null, true, fimagenPozo).setVisible(true);
        }
    }//GEN-LAST:event_txtPozoMouseClicked

    private void txtPozo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPozo1MouseClicked
        if (evt.getClickCount() >= 2) {
            new FrmFoto(null, true, fimagenAuxiliar).setVisible(true);
        }
    }//GEN-LAST:event_txtPozo1MouseClicked

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            guardar();
        } catch (Exception ex) {
            Logger.getLogger(FrmPozos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        cargarVista();
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed

        if (verificar()) {
            String pozo = nombreArchivo(fimagenPozo.getName());
//            String auxiliar = nombreArchivo(fimagenAuxiliar.getName());
            String auxiliar = (fimagenAuxiliar != null) ? nombreArchivo(fimagenAuxiliar.getName()) : "D:\\Desktop\\Simulacion 1\\Cat-pain.jpg";
            pozoControl.getPozo().setAuxiliar(auxiliar);

            pozoControl.getPozo().setDescripcion(txtDescripcion.getText());
            pozoControl.getPozo().setEstado(Utilvista.obtenerEstadoControl(cbxEstado));
            pozoControl.getPozo().setImagen(pozo);
            pozoControl.getPozo().setProfundidad(Double.parseDouble(txtProfundidad.getText()));
            pozoControl.getPozo().setFechaMantenimiento(txtFecha.getText());
            pozoControl.getPozo().setCoordenada(new Coordenada(Double.valueOf(txtLongitud.getText()), Double.valueOf(txtLatitud.getText())));
            pozoControl.getPozo().setAuxiliar(auxiliar);
            try {
                //fileEscuela.setGrafo(new GrafosEtiquetadosNoDirigidos<>());
                UtilesFoto.copiarArchivo(fimagenPozo, new File("Foto/" + pozo));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
//            UtilesFoto.copiarArchivo(fimagenAuxiliar, new File("Foto/" + auxiliar));
            if (fimagenAuxiliar != null) {
                try {
                    UtilesFoto.copiarArchivo(fimagenAuxiliar, new File("Foto/" + auxiliar));
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
            if (pozoDao.merge(pozoControl.getPozo(), tblMostrar.getSelectedRow())) {
                JOptionPane.showMessageDialog(null, "Datos guardados");
                cargarTabla();
                limpiar();
                pozoControl.setPozo(null);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo guardar, hubo un error");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Falta llenar campos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnAdyacenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdyacenciasActionPerformed
       FrmGrafoPozo fgp=new FrmGrafoPozo(this, rootPaneCheckingEnabled);
       this.dispose();
       fgp.setVisible(true);
       
    }//GEN-LAST:event_btnAdyacenciasActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        Menu menu=new Menu(this, rootPaneCheckingEnabled);
        this.dispose();
        menu.setVisible(true);
    }//GEN-LAST:event_btnAtrasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPozos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPozos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPozos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPozos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPozos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonAction btnAdyacencias;
    private org.edisoncor.gui.button.ButtonAction btnAtras;
    private javax.swing.JButton btnCargar;
    private javax.swing.JButton btnCargar1;
    private org.edisoncor.gui.button.ButtonAction btnGuardar;
    private org.edisoncor.gui.button.ButtonAction btnModificar;
    private org.edisoncor.gui.button.ButtonAction btnSeleccionar;
    private javax.swing.JComboBox<String> cbxEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private org.edisoncor.gui.panel.Panel panel1;
    private javax.swing.JTable tblMostrar;
    private org.edisoncor.gui.textField.TextFieldRound textFieldRound7;
    private org.edisoncor.gui.textField.TextFieldRound textFieldRound9;
    private org.edisoncor.gui.textField.TextFieldRound txtDescripcion;
    private org.edisoncor.gui.textField.TextFieldRound txtFecha;
    private org.edisoncor.gui.textField.TextFieldRound txtLatitud;
    private org.edisoncor.gui.textField.TextFieldRound txtLongitud;
    private org.edisoncor.gui.textField.TextFieldRound txtPozo;
    private org.edisoncor.gui.textField.TextFieldRound txtPozo1;
    private org.edisoncor.gui.textField.TextFieldRound txtProfundidad;
    // End of variables declaration//GEN-END:variables
}
