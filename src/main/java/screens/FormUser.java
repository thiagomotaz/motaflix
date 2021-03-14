/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens;

import dao.UserDAO;
import models.DatabaseConnection;
import models.User;
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import models.Parental;

/**
 *
 * @author thiag
 */
public class FormUser extends javax.swing.JPanel {
    
    final JDialog dialog;
    JTable table;
    private int id = 0;
    public int selectedId = 0;
    public int row = 0;
    UserDAO userDAO;

    /**
     * Creates new form FormUser
     */
    public FormUser(JFrame frame, JTable table) throws SQLException {
        initComponents();
        loadParentalData();
        dialog = new JDialog(frame, "", true);
        dialog.getContentPane().add(this);
        dialog.setUndecorated(true);
        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY));
        this.table = table;
        userDAO = new UserDAO();
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
        jLabel2 = new javax.swing.JLabel();
        MaskFormatter maskCPF = null;
        try {
            maskCPF = new MaskFormatter("###.###.###-##");
        }
        catch(Exception e){

        }
        maskCPF.setPlaceholderCharacter('_');
        fieldCPF = new javax.swing.JFormattedTextField(maskCPF);
        jLabel3 = new javax.swing.JLabel();
        MaskFormatter maskDate = null;

        try {
            maskDate = new MaskFormatter("##/##/####");
        }
        catch(Exception e){

        }
        maskDate.setPlaceholderCharacter('_');
        fieldDate = new javax.swing.JFormattedTextField(maskDate);
        jLabel4 = new javax.swing.JLabel();
        fieldEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        fieldPassword = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jComboParentalList = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(600, 600));
        setPreferredSize(new java.awt.Dimension(600, 400));
        setLayout(null);

        jPanel1.setLayout(new java.awt.GridLayout(18, 1, 2, 2));

        formTitle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        formTitle.setText("Cadastro de usuário");
        jPanel1.add(formTitle);
        jPanel1.add(jLabel8);

        jLabel1.setText("Nome");
        jPanel1.add(jLabel1);
        jPanel1.add(fieldName);

        jLabel2.setText("CPF");
        jPanel1.add(jLabel2);
        jPanel1.add(fieldCPF);

        jLabel3.setText("Data de nascimento");
        jPanel1.add(jLabel3);
        jPanel1.add(fieldDate);

        jLabel4.setText("Email");
        jPanel1.add(jLabel4);
        jPanel1.add(fieldEmail);

        jLabel5.setText("Senha");
        jPanel1.add(jLabel5);
        jPanel1.add(fieldPassword);
        jPanel1.add(jLabel7);

        jLabel9.setText("Tipo de controle parental");
        jPanel1.add(jLabel9);

        jPanel1.add(jComboParentalList);
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
        Object item = this.jComboParentalList.getSelectedItem();
        Integer value = ((Parental) item).getValue();
        
        if (this.fieldName.getText().isEmpty() || this.fieldCPF.getText().isEmpty() || this.fieldDate.getText().isEmpty() || this.fieldEmail.getText().isEmpty() || this.fieldPassword.getText().isEmpty() || value == 0) {
            showMessageDialog(this, "Por favor, preencha todos os campos! \n Dev message: Lembre-se de ter registros na tabela\nParental para serem selecionados\nno ComboBox de controle parental");
        } else if (this.selectedId == 0) { //create
            User user = new User(id++, this.fieldName.getText(), this.fieldCPF.getText(), this.fieldDate.getText(), this.fieldEmail.getText(), this.fieldPassword.getText(), 0);
            try {
                addUser(user);
            } catch (SQLException ex) {
                Logger.getLogger(FormUser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(FormUser.class.getName()).log(Level.SEVERE, null, ex);
            }
            showMessageDialog(this, "Registro adicionado com sucesso!");
            this.clearFields();
            this.setVisibility(false);
        } else { //update
            User user = new User(this.selectedId, this.fieldName.getText(), this.fieldCPF.getText(), this.fieldDate.getText(), this.fieldEmail.getText(), this.fieldPassword.getText(), 0);
            try {
                editUser(user);
            } catch (SQLException ex) {
                Logger.getLogger(FormUser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(FormUser.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        fieldCPF.setText("");
        fieldEmail.setText("");
        fieldPassword.setText("");
        fieldName.setText("");
        fieldDate.setText("");
    }
    
    public void addUser(User user) throws SQLException, ParseException {
        DefaultTableModel model = (DefaultTableModel) this.table.getModel();
        user.setId(0);
        
        Object item = this.jComboParentalList.getSelectedItem();
        Integer value = ((Parental) item).getValue();
        user.setParental_id(value);
        int insertedId = userDAO.change(user);
        
        model.addRow(new Object[]{insertedId, user.getName(), user.getCPF(), user.getBirthday(), user.getEmail(), user.getPassword()});
    }
    
    public void editUser(User user) throws SQLException, ParseException {
        
        Object item = this.jComboParentalList.getSelectedItem();
        Integer value = ((Parental) item).getValue();
        user.setParental_id(value);
        
        this.table.setValueAt(user.getName(), row, 1);
        this.table.setValueAt(user.getCPF(), row, 2);
        this.table.setValueAt(user.getBirthday(), row, 3);
        this.table.setValueAt(user.getEmail(), row, 4);
        this.table.setValueAt(user.getPassword(), row, 5);
        user.setId((int) this.table.getModel().getValueAt(row, 0));
        userDAO.change(user);
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JFormattedTextField fieldCPF;
    private javax.swing.JTextField fieldDate;
    private javax.swing.JTextField fieldEmail;
    private javax.swing.JTextField fieldName;
    private javax.swing.JPasswordField fieldPassword;
    private javax.swing.JLabel formTitle;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<Object> jComboParentalList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    void loadData(JTable tableUsers, int row) {
        this.row = row;
        this.formTitle.setText("Edição de usuário: " + tableUsers.getModel().getValueAt(row, 1).toString());
        fieldName.setText(tableUsers.getModel().getValueAt(row, 1).toString());
        fieldCPF.setText(tableUsers.getModel().getValueAt(row, 2).toString());
        fieldDate.setText(tableUsers.getModel().getValueAt(row, 3).toString());
        fieldEmail.setText(tableUsers.getModel().getValueAt(row, 4).toString());
        fieldPassword.setText(tableUsers.getModel().getValueAt(row, 5).toString());
    }
    
    void setDefaultTitle() {
        this.formTitle.setText("Cadastro de usuário");
    }
    
    private ResultSet loadParentalData() throws SQLException {
        PreparedStatement ps = DatabaseConnection.connection().prepareStatement("SELECT * FROM parental");
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            this.jComboParentalList.addItem(new Parental(rs.getString("name"), rs.getInt("id")));
        }
        
        return rs;
    }
}
