/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.ActorController;
import dao.ActorDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import models.Actor;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thiag
 */
public class Actors extends javax.swing.JPanel {

    private JFrame frame;
    FormActor form;

    /**
     * Creates new form Usersx
     */
    public Actors(JFrame frame) throws SQLException {
        this.frame = frame;
        initComponents();
        initTableData();
        form = new FormActor(frame, this.tableActors);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnNew = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableActors = new javax.swing.JTable();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        btnNew.setText("Novo ator");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        add(btnNew);

        btnUpdate.setText("Editar");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        add(btnUpdate);

        btnDelete.setText("Excluir");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        add(btnDelete);

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel1.setText("Atores");
        add(jLabel1);

        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);

        tableActors.setAutoCreateRowSorter(true);
        tableActors.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "nome", "data de nascimento", "altura"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableActors.setMaximumSize(null);
        jScrollPane1.setViewportView(tableActors);

        add(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        form.clearFields();
        form.selectedId = 0;
        form.setDefaultTitle();
        form.setVisibility(true);
        form.setDefaultTitle();

    }//GEN-LAST:event_btnNewActionPerformed


    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        //verifica se tem linha selecionada
        int row = this.tableActors.getSelectedRow();
        if (row >= 0) {
            form.selectedId = 1;
            form.loadData(this.tableActors, row);
            form.setVisibility(true);
        } else {
            showMessageDialog(this, "Nenhum registro selecionado!");

        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int row = tableActors.getSelectedRow();
        if (row >= 0) {
            try {
                deleteContact(row);
            } catch (SQLException ex) {
                Logger.getLogger(Actors.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            showMessageDialog(this, "Nenhum registro selecionado");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed
    public void deleteContact(int row) throws SQLException {
        ActorController controller = new ActorController();

        Object[] options = {"Sim, remover", "Cancelar!"};
        int n = JOptionPane.showOptionDialog(this,
                "Tem certeza que deseja excluir o ator?",
                "",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (n == JOptionPane.YES_OPTION) {
            DefaultTableModel model = (DefaultTableModel) this.tableActors.getModel();
            controller.delete((int) model.getValueAt(row, 0));

            int[] rows = tableActors.getSelectedRows();
            for (int i = 0; i < rows.length; i++) {
                model.removeRow(rows[i] - i);
            }
        }
    }

    private void initTableData() throws SQLException {
        ActorController controller = new ActorController();
        ResultSet data = controller.index();

        DefaultTableModel model = (DefaultTableModel) this.tableActors.getModel();

        while (data.next()) {
            Date datax = data.getDate("birthday");
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String text = df.format(datax);

            model.addRow(new Object[]{data.getInt("id"), data.getString("name"), text, data.getString("height")});
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableActors;
    // End of variables declaration//GEN-END:variables
}
