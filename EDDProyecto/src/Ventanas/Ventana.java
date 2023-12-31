/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Ventanas;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import Estructuras.Funciones_TXT;
import Estructuras.Grafo;
import Estructuras.User;

public class Ventana extends javax.swing.JFrame {
    Grafo grafito;
    String direccion_archivo;
    Funciones_TXT f_txt ;
    /**
     * Creates new form Ventana
     */
    public Ventana() {
        initComponents();
        this.setVisible(true);
        f_txt = new Funciones_TXT();
        this.grafito = f_txt.leerTXT("registro_usuarios.txt");
        direccion_archivo = "registro_usuarios.txt";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        buscar = new javax.swing.JButton();
        direccion = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        leer_arch = new javax.swing.JButton();
        iniciar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(40, 115, 145));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buscar.setText("Buscar");
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });
        jPanel1.add(buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, -1, -1));

        direccion.setEditable(false);
        direccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                direccionActionPerformed(evt);
            }
        });
        jPanel1.add(direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 290, -1));

        jLabel2.setText("Si quieres utilizar el archivo por defecto, solo presiona \"Iniciar\"");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, -1, 20));

        leer_arch.setText("Leer archivo");
        leer_arch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leer_archActionPerformed(evt);
            }
        });
        jPanel1.add(leer_arch, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, -1, -1));

        iniciar.setText("Iniciar");
        iniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarActionPerformed(evt);
            }
        });
        jPanel1.add(iniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, -1, -1));

        jLabel3.setText("Presiona \"Buscar\" para escoger un archivo .TXT");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, -1, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 330));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        // TODO add your handling code here:
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de Texto", "txt");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);
        File ruta = new File("e:/carpeta/");
        fileChooser.setCurrentDirectory(ruta);
        int result = fileChooser.showOpenDialog(null);
        if(result == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            String dir = String.valueOf(file).replace("\\", "//");
            direccion.setText(dir);
            direccion_archivo = dir;}
    }//GEN-LAST:event_buscarActionPerformed

    private void leer_archActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leer_archActionPerformed
        
// TODO add your handling code here:

grafito = f_txt.leerTXT(direccion.getText());
    }//GEN-LAST:event_leer_archActionPerformed

    private void iniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniciarActionPerformed
        // TODO add your handling code here:
//        usuarios.setText(this.grafito.imprimir_usuarios());
Ventana1 v1 = new Ventana1(grafito, direccion_archivo);
    }//GEN-LAST:event_iniciarActionPerformed

    private void direccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_direccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_direccionActionPerformed

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
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscar;
    private javax.swing.JTextField direccion;
    private javax.swing.JButton iniciar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton leer_arch;
    // End of variables declaration//GEN-END:variables
}
