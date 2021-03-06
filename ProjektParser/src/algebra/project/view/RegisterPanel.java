/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algebra.project.view;

import algebra.project.UserForm;
import algebra.project.dal.Repository;
import algebra.project.dal.RepositoryFactory;
import algebra.project.model.User;
import algebra.project.utils.MessageUtils;
import algebra.project.utils.StringUtils;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Kiki
 */
public class RegisterPanel extends javax.swing.JPanel {

    private List<JTextComponent> validationFields;
    private User user;
    private List<JLabel> errorLabels;
    private Repository repository;
    /**
     * Creates new form RegisterForm
     */
    public RegisterPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tfUsername = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lblConfirmPassword = new javax.swing.JLabel();
        btnRegister = new javax.swing.JButton();
        lblErrorPasswordMatch = new javax.swing.JLabel();
        lblErrorPassword = new javax.swing.JLabel();
        lblErrorUsername = new javax.swing.JLabel();
        tfPassword = new javax.swing.JPasswordField();
        tfConfirmPassword = new javax.swing.JPasswordField();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jLabel1.setText("Username:");

        tfUsername.setName("Username"); // NOI18N

        jLabel2.setText("Password:");

        lblConfirmPassword.setText("Confirm password:");

        btnRegister.setText("Register");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        lblErrorPasswordMatch.setForeground(java.awt.Color.red);

        lblErrorPassword.setForeground(java.awt.Color.red);

        lblErrorUsername.setForeground(java.awt.Color.red);

        tfConfirmPassword.setName("ConfirmPassword"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRegister, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                    .addComponent(tfUsername)
                    .addComponent(tfPassword)
                    .addComponent(tfConfirmPassword)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblErrorUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblErrorPasswordMatch, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblErrorPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblErrorUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblErrorPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(tfPassword))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblErrorPasswordMatch, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(tfConfirmPassword))
                .addGap(18, 18, 18)
                .addComponent(btnRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        if (!formValid()) {
            return;
        }
        try {
            repository.createUser(new User(
                tfUsername.getText().trim(),
                StringUtils.charToString(tfPassword.getPassword()).trim(),
                User.UserRoleType.USER.toString()
            ));
            JFrame userForm = new UserForm();
            userForm.setVisible(true);
            this.setVisible(false);
            SwingUtilities.windowForComponent(this).dispose();
        } catch (Exception ex) {
            Logger.getLogger(RegisterPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        init();
    }//GEN-LAST:event_formComponentShown

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegister;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblConfirmPassword;
    private javax.swing.JLabel lblErrorPassword;
    private javax.swing.JLabel lblErrorPasswordMatch;
    private javax.swing.JLabel lblErrorUsername;
    private javax.swing.JPasswordField tfConfirmPassword;
    private javax.swing.JPasswordField tfPassword;
    private javax.swing.JTextField tfUsername;
    // End of variables declaration//GEN-END:variables

     private void init() {
        try {
            initValidation();
            initRepository();
        } catch (Exception ex) {
            Logger.getLogger(EditMoviePanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Unrecoverable", "Exiting...");
            System.exit(1);
        }
    }

    private void initValidation() {
        validationFields = Arrays.asList(tfUsername, tfPassword, tfConfirmPassword);
        errorLabels = Arrays.asList(lblErrorUsername, lblErrorPassword, lblErrorPasswordMatch);
    }

    private void initRepository() throws Exception {
        repository = RepositoryFactory.getRepository();
    }
    
     private boolean formValid() {
        boolean ok = true;
        
        for (int i = 0; i < validationFields.size(); i++) {
            ok &= !validationFields.get(i).getText().trim().isEmpty();
            errorLabels.get(i).setText(validationFields.get(i).getText().trim().isEmpty() ? "X" : "");
            if ("Username".equals(validationFields.get(i).getName())) {
                try {
                    if (repository.checkIfUsernameExists(validationFields.get(i).getText().trim())) {
                        errorLabels.get(i).setText("Username already exists");
                        ok = false;  
                    }   
                } catch (Exception ex) {
                    Logger.getLogger(RegisterPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if ("ConfirmPassword".equals(validationFields.get(i).getName())){
                if (!(Arrays.equals(tfPassword.getPassword(), tfConfirmPassword.getPassword()))) {
                    errorLabels.get(i).setText("Passwords dont match");
                    ok = false;   
                }
            }
        }
        
        return ok;
    }
}