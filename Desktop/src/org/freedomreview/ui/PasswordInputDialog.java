
package org.freedomreview.ui;

import java.io.File;

import javax.swing.JOptionPane;

import org.freedomreview.encrypt.EncrypterDecrypter;

public class PasswordInputDialog extends javax.swing.JDialog {
	private static final long serialVersionUID = -8565534822168929525L;

	private File inputFile;
	private boolean encrypt;

	/**
     * Creates new form PasswordInputDialog
     */
    public PasswordInputDialog(java.awt.Frame parent, boolean modal, File inputFile, boolean encrypt ) {
        super(parent, modal);
        this.inputFile = inputFile;
        this.encrypt = encrypt;
        initComponents();
        setEncryptDecryptButton();
    }

    private void setEncryptDecryptButton()
    {
    	if( this.encrypt )
    		this.okButton.setText( "Encrypt" );
    	else
            this.okButton.setText( "Decrypt" );
    }
    
    private void crypt()
    {
    	String outPath;

    	// figure out our output path based on what we are doing
    	if( this.encrypt )
    		outPath = this.inputFile.getAbsolutePath() + ".aes";
    	else {
    		if( this.inputFile.getAbsolutePath().endsWith( ".aes" ) )
    			outPath = this.inputFile.getAbsolutePath().substring( 0, 
    					this.inputFile.getAbsolutePath().length() - 1 - 3 );
    		else
    			outPath = this.inputFile.getAbsolutePath() + ".decrypt";
    	}
    	
    	// encrypt or decrypt the file depending on our current settings
    	try{
            EncrypterDecrypter.crypt( this.encrypt, this.passwordTextField.getText(), 
                              this.inputFile.getAbsolutePath(), outPath );
            if( new File( outPath ).exists() )
            	JOptionPane.showMessageDialog( this,  "File written successfully\n\nOutput file: \"" +
            			outPath + "\"", "File written successfully", JOptionPane.INFORMATION_MESSAGE );
            else
            	JOptionPane.showMessageDialog( this, "An unknown error occurred",
    				"An unknown error occurred", JOptionPane.ERROR_MESSAGE );
    	} catch( Exception ex ) {
    		ex.printStackTrace();
    		File outFile = new File( outPath );
    		if( outFile.exists() ){
    			try{ outFile.delete(); } catch( Exception e ){ e.printStackTrace(); }
    		}
    		JOptionPane.showMessageDialog( this, "There was a problem:\n\n" + ex.getMessage(), 
    				"There was a problem", JOptionPane.ERROR_MESSAGE );
    	}
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        passwordTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Enter password");

        okButton.setText("Decrypt");
        okButton.setEnabled(false);
        okButton.setPreferredSize(new java.awt.Dimension(80, 33));
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.setPreferredSize(new java.awt.Dimension(80, 33));
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Enter Password:");

        passwordTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordTextFieldActionPerformed(evt);
            }
        });
        passwordTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                passwordTextFieldKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 133, Short.MAX_VALUE)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(passwordTextField, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 194, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
    	crypt();
    	this.dispose();
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
    	this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void passwordTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordTextFieldActionPerformed
    	okButtonActionPerformed( evt );
    }//GEN-LAST:event_passwordTextFieldActionPerformed

    private void passwordTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordTextFieldKeyReleased
    	this.okButton.setEnabled( this.passwordTextField.getText().length() > 0 );
    }//GEN-LAST:event_passwordTextFieldKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton okButton;
    private javax.swing.JTextField passwordTextField;
    // End of variables declaration//GEN-END:variables
}