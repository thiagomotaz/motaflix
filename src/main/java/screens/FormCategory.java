/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens;

import classes.Category;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author thiag
 */
public class FormCategory extends javax.swing.JPanel {

    final JDialog dialog;
    JTable table;
    private int id = 0;
    public int selectedId = 0;
    public int row = 0;

    /**
     * Creates new form FormCategory
     */
    public FormCategory(JFrame frame, JTable table) {
        initComponents();
        dialog = new JDialog(frame, "", true);
        dialog.getContentPane().add(this);
        dialog.setUndecorated(true);
        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY));
        this.table = table;
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
        formTitle = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        fieldName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(600, 600));
        setPreferredSize(new java.awt.Dimension(600, 400));
        setLayout(null);

        jPanel1.setLayout(new java.awt.GridLayout(16, 1, 2, 2));

        formTitle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        formTitle.setText("Cadastro de usuário");
        jPanel1.add(formTitle);
        jPanel1.add(jLabel8);

        jLabel1.setText("Nome");
        jPanel1.add(jLabel1);
        jPanel1.add(fieldName);
        jPanel1.add(jLabel6);

        btnSave.setText("Salvar");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel1.add(btnSave);

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);

        add(jPanel1);
        jPanel1.setBounds(20, 22, 560, 360);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.setVisibility(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:

        if (this.fieldName.getText().isEmpty()) {
            showMessageDialog(this, "Por favor, preencha todos os campos!");
        } else if (this.selectedId == 0) { //create
            Category category = new Category(id++, this.fieldName.getText());
            addCategory(category);
            showMessageDialog(this, "Registro adicionado com sucesso!");
            this.clearFields();
            this.setVisibility(false);
        } else { //update
            Category category = new Category(this.selectedId, this.fieldName.getText());
            editCategory(category);
            this.clearFields();
            this.setVisibility(false);
            showMessageDialog(this, "Registro alterado com sucesso!");
            this.selectedId = 0;
        }

    }//GEN-LAST:event_btnSaveActionPerformed
    public void setVisibility(Boolean value) {
        this.dialog.setVisible(value);
    }

    public void clearFields() {
        fieldName.setText("");
    }

    public void addCategory(Category category) {
        DefaultTableModel model = (DefaultTableModel) this.table.getModel();
        model.addRow(new Object[]{category.getId(), category.getName()});
    }

    public void editCategory(Category category) {
        this.table.setValueAt(category.getName(), row, 1);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JTextField fieldName;
    private javax.swing.JLabel formTitle;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    void loadData(JTable tableCategories, int row) {
        this.row = row;
        this.formTitle.setText("Edição de categoria: " + tableCategories.getModel().getValueAt(row, 1).toString());
        fieldName.setText(tableCategories.getModel().getValueAt(row, 1).toString());
    }

    void setDefaultTitle() {
        this.formTitle.setText("Cadastro de Categoria");

    }
}