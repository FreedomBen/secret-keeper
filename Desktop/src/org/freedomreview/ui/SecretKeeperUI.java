/**
 *  Copyright 2014 Benjamin Porter
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License. 
 */

package org.freedomreview.ui;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.text.DefaultEditorKit;

import org.freedomreview.encrypt.EncrypterDecrypter;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Benjamin Porter
 */
public class SecretKeeperUI extends javax.swing.JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2972339944887776607L;
	private static final String COPY_SUCCESS = "Text Copied to Clipboard Successfully";
	private static final String COPY_FAILURE = "Text copied to clipboard did not match the text in the output text area!";
	
	private static final String ABOUT_TITLE = "About Secret Keeper";
	private static final String ABOUT_MESSAGE =
			"Secret Keeper uses AES to encrypt/decrypt files into/from the " +
			"AESCrypt file format.\nThis means you can open your files using " +
			"any AES Crypt compatible program.\n\nText encryption/decryption " +
			"is done using Triple DES (3DES) with an MD5 hash.";
	
	private Color defaultGoButtonColor;
	
	/**
     * Creates new form SecretKeeperMain
     */
    public SecretKeeperUI() {
        initComponents();
        this.defaultGoButtonColor = this.goButton.getBackground();
        this.updateGoButtonColor();
        this.addPopupToInputTextArea();
        this.setLocationRelativeTo( null );
    }

    private void addPopupToInputTextArea() {
    	final String COPY = "Copy";
    	final String CUT = "Cut";
    	final String PASTE = "Paste";
    	final String SELECTALL = "Select All";

    	final JPopupMenu menu = new JPopupMenu();
    	final JMenuItem copyItem = new JMenuItem();
    	copyItem.setAction( this.inputTextArea.getActionMap().get( DefaultEditorKit.copyAction ) );
    	copyItem.setText( COPY );

    	final JMenuItem cutItem = new JMenuItem();
    	cutItem.setAction( this.inputTextArea.getActionMap().get( DefaultEditorKit.cutAction ) );
    	cutItem.setText( CUT );

    	final JMenuItem pasteItem = new JMenuItem( PASTE );
    	pasteItem.setAction( this.inputTextArea.getActionMap().get( DefaultEditorKit.pasteAction ) );
    	pasteItem.setText( PASTE );

    	final JMenuItem selectAllItem = new JMenuItem( SELECTALL );
    	selectAllItem.setAction( this.inputTextArea.getActionMap().get( DefaultEditorKit.selectAllAction ) );
    	selectAllItem.setText( SELECTALL );

    	menu.add( copyItem );
    	menu.add( cutItem );
    	menu.add( pasteItem );
    	menu.add( new JSeparator() );
    	menu.add( selectAllItem );
    	
    	this.inputTextArea.addMouseListener( new MouseAdapter(){
    		@Override
    		public void mouseClicked( MouseEvent event ){
    			if( event.getButton() == MouseEvent.BUTTON3 ){
    				menu.show( inputTextArea, event.getX(), event.getY() );
    			}
    		}
    	} );
    }
    
    private boolean passwordIsWeak( String password ){
    	return !passwordWeakness( password ).isEmpty();
    }

    private String passwordWeakness( String password ){
    	// a good password has the following
    	// 1. at least 12 characters
    	// 2. At least one Upper, Lower, Number, Symbol
    	// 3. No dictionary words
    	if( password.length() < 12 )
    		return "Password is short.  Should be at least 12 characters";
    	else if( password.toUpperCase().equals( password ) )
    		return "Password has no lowercase letters";
    	else if( password.toLowerCase().equals( password ) )
    		return "Password has no uppercase letters";
    	else
    		return "";
    }

    private void updateGoButtonColor(){
    	if( this.encryptRadioButton.isSelected() && this.enforceGoodPasswordMenuItem.isSelected() ) {
    		if( this.passwordIsWeak( this.passwordTextField.getText() ) )
    			this.goButton.setBackground( Color.RED );
    		else
    			this.goButton.setBackground( Color.GREEN );
    	}
    	else {
    		this.goButton.setBackground( this.defaultGoButtonColor );
    	}
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        encryptButtonGroup = new javax.swing.ButtonGroup();
        inputPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        inputTextArea = new javax.swing.JTextArea();
        outputPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        outputTextArea = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        passwordLabel = new javax.swing.JLabel();
        passwordTextField = new javax.swing.JTextField();
        encryptRadioButton = new javax.swing.JRadioButton();
        decryptRadioButton = new javax.swing.JRadioButton();
        goButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        encryptFileMenuItem = new javax.swing.JMenuItem();
        decryptMenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        exitMenuItem = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        enforceGoodPasswordMenuItem = new javax.swing.JCheckBoxMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Secret Keeper V.1.0");

        inputPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Input Text"));
        inputPanel.setToolTipText("Enter either the plaintext you wish to encrypt, or the ciphertext you wish to decrypt");

        inputTextArea.setColumns(20);
        inputTextArea.setLineWrap(true);
        inputTextArea.setRows(5);
        inputTextArea.setWrapStyleWord(true);
        jScrollPane1.setViewportView(inputTextArea);

        javax.swing.GroupLayout inputPanelLayout = new javax.swing.GroupLayout(inputPanel);
        inputPanel.setLayout(inputPanelLayout);
        inputPanelLayout.setHorizontalGroup(
            inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        inputPanelLayout.setVerticalGroup(
            inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
        );

        outputPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Output Text (Right-click to copy to clipboard)"));

        outputTextArea.setEditable(false);
        outputTextArea.setColumns(20);
        outputTextArea.setLineWrap(true);
        outputTextArea.setRows(5);
        outputTextArea.setWrapStyleWord(true);
        outputTextArea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                outputTextAreaMousePressed(evt);
            }
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                outputTextAreaMouseClicked(evt);
//            }
        });
        jScrollPane2.setViewportView(outputTextArea);

        javax.swing.GroupLayout outputPanelLayout = new javax.swing.GroupLayout(outputPanel);
        outputPanel.setLayout(outputPanelLayout);
        outputPanelLayout.setHorizontalGroup(
            outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        outputPanelLayout.setVerticalGroup(
            outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Keep it a Secret!"));

        passwordLabel.setText("Create Password:");

        passwordTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordTextFieldActionPerformed(evt);
            }
        });
        passwordTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                passwordTextFieldKeyTyped(evt);
            }
        });

        encryptButtonGroup.add(encryptRadioButton);
        encryptRadioButton.setSelected(true);
        encryptRadioButton.setText("Encrypt");
        encryptRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encryptRadioButtonActionPerformed(evt);
            }
        });

        encryptButtonGroup.add(decryptRadioButton);
        decryptRadioButton.setText("Decrypt");
        decryptRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decryptRadioButtonActionPerformed(evt);
            }
        });

        goButton.setText("Go!");
        goButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(encryptRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(decryptRadioButton)
                .addGap(18, 18, 18)
                .addComponent(passwordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(goButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(encryptRadioButton)
                    .addComponent(decryptRadioButton)
                    .addComponent(goButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        fileMenu.setText("File");

        encryptFileMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        encryptFileMenuItem.setText("Encrypt entire file...");
        encryptFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encryptFileMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(encryptFileMenuItem);

        decryptMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        decryptMenuItem.setText("Decrypt entire file...");
        decryptMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decryptMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(decryptMenuItem);
        fileMenu.add(jSeparator2);

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        jMenuBar1.add(fileMenu);

        jMenu1.setText("Options");

        enforceGoodPasswordMenuItem.setSelected(true);
        enforceGoodPasswordMenuItem.setText("Enforce good password");
        enforceGoodPasswordMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enforceGoodPasswordMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(enforceGoodPasswordMenuItem);

        jMenuBar1.add(jMenu1);

        helpMenu.setText("Help");

        aboutMenuItem.setText("About");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        jMenuBar1.add(helpMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(outputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(inputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(outputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void encryptRadioButtonActionPerformed( java.awt.event.ActionEvent evt ) {//GEN-FIRST:event_encryptRadioButtonActionPerformed
    	this.passwordLabel.setText( "Create Password:" );
    	this.updateGoButtonColor();
    }//GEN-LAST:event_encryptRadioButtonActionPerformed

    private void decryptRadioButtonActionPerformed( java.awt.event.ActionEvent evt ) {//GEN-FIRST:event_decryptRadioButtonActionPerformed
    	this.passwordLabel.setText( "Enter Password:" );
    	this.updateGoButtonColor();
    }//GEN-LAST:event_decryptRadioButtonActionPerformed

    private void goButtonActionPerformed( java.awt.event.ActionEvent evt ) {//GEN-FIRST:event_goButtonActionPerformed
    	if( this.passwordTextField.getText().trim().isEmpty() ){
    		JOptionPane.showMessageDialog( this, "You didn't enter a password.\nIf you don't enter a password, " +
    				"\nthe text will not be encrypted!  :-)", "Enter a Password", JOptionPane.ERROR_MESSAGE );
    	} else if( this.enforceGoodPasswordMenuItem.isSelected() && this.passwordIsWeak( this.passwordTextField.getText().trim() ) ) { 
    		JOptionPane.showMessageDialog( this, "Your password is weak for the following reasons: " 
    				+ this.passwordWeakness( this.passwordTextField.getText().trim() ) +
    				"\n\nIf you really want to use a weak password unselect \"Enforce strong password\" in the \"Options\" menu"
    				, "Enter a better Password", JOptionPane.ERROR_MESSAGE );
    	}
        else {
    		try {
	    		this.outputTextArea.setText( EncrypterDecrypter.crypt( this.encryptRadioButton.isSelected()
	    				, this.passwordTextField.getText(), this.inputTextArea.getText() ) );
    		} catch( Exception ex ){
    			this.outputTextArea.setText( "There was a problem handling the input text.  Please ensure that " +
    					"you have included the entirety of the text, and that there are no extra characters in" +
    					" the middle that have been added (sometimes email programs like to do this)" );
    		}
    	}
    }//GEN-LAST:event_goButtonActionPerformed

    private void outputTextAreaMousePressed( java.awt.event.MouseEvent evt ) {//GEN-FIRST:event_outputTextAreaMousePressed
	    if( evt.getButton() == MouseEvent.BUTTON3 ) this.copyOutputToClipboard();	
    }//GEN-LAST:event_outputTextAreaMousePressed

    private void exitMenuItemActionPerformed( java.awt.event.ActionEvent evt ) {//GEN-FIRST:event_exitMenuItemActionPerformed
    	System.exit( 0 );
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void aboutMenuItemActionPerformed( java.awt.event.ActionEvent evt ) {//GEN-FIRST:event_aboutMenuItemActionPerformed
    	JOptionPane.showMessageDialog( this, ABOUT_MESSAGE, ABOUT_TITLE, JOptionPane.INFORMATION_MESSAGE );
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void passwordTextFieldActionPerformed( java.awt.event.ActionEvent evt ) {//GEN-FIRST:event_passwordTextFieldActionPerformed
    	this.goButtonActionPerformed( evt );
    }//GEN-LAST:event_passwordTextFieldActionPerformed

    private void enforceGoodPasswordMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enforceGoodPasswordMenuItemActionPerformed
    	this.updateGoButtonColor();
    }//GEN-LAST:event_enforceGoodPasswordMenuItemActionPerformed

    private void passwordTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordTextFieldKeyTyped
    	updateGoButtonColor();
    }//GEN-LAST:event_passwordTextFieldKeyTyped

    private void encryptFileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_encryptFileMenuItemActionPerformed
    	cryptFile( true );
    }//GEN-LAST:event_encryptFileMenuItemActionPerformed

    private void decryptMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decryptMenuItemActionPerformed
    	cryptFile( false );
    }//GEN-LAST:event_decryptMenuItemActionPerformed
    
    private void cryptFile( boolean encrypt ){
    	JFileChooser chooser = new JFileChooser();
    	int returnVal = chooser.showOpenDialog( this );
    	if(returnVal == JFileChooser.APPROVE_OPTION) {
    	    File selectedFile = new File( chooser.getSelectedFile().getAbsolutePath() );
    	    PasswordInputDialog dialog = new PasswordInputDialog( this, true, selectedFile, encrypt );
    	    dialog.setLocationRelativeTo( null );
    	    dialog.setVisible( true );
    	}
    }
    
    private void copyOutputToClipboard(){
    	// don't copy to the clipboard if it is a message to the user
		if( ( !this.outputTextArea.getText().equals( COPY_FAILURE ) ) && ( !this.outputTextArea.getText().equals( COPY_SUCCESS ) ) ){
	    	try {
	    		StringSelection stringSelection = new StringSelection ( this.outputTextArea.getText() );
	    		
	    		Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
	    		clpbrd.setContents ( stringSelection, null );
	    		
	    		// verify that it worked
	    		String data = ( String )Toolkit.getDefaultToolkit().getSystemClipboard().getData( DataFlavor.stringFlavor );
	    		if( data.trim().equals( this.outputTextArea.getText().trim() ) ) 
	    			flashMessage( COPY_SUCCESS, Color.YELLOW );
	    		else
	    			throw new Exception( COPY_FAILURE );
	    	} catch ( Exception ex ){
	    		flashMessage( ex.getMessage(), Color.RED );
	    	} 
		}
    }
    
    private void flashMessage( String message, Color color ){
    	JTextAreaFader.fadeTextArea( this.outputTextArea, color, message, this.outputTextArea.getText() );
    }

    /**
     * @param args the command line arguments
     */
    public static void main( String args[] ) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus ( introduced in Java SE 6 ) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for ( javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels() ) {
                if ( "Nimbus".equals( info.getName() ) ) {
                    javax.swing.UIManager.setLookAndFeel( info.getClassName() );
                    break;
                }
            }
        } catch ( ClassNotFoundException ex ) {
            java.util.logging.Logger.getLogger( SecretKeeperUI.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        } catch ( InstantiationException ex ) {
            java.util.logging.Logger.getLogger( SecretKeeperUI.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        } catch ( IllegalAccessException ex ) {
            java.util.logging.Logger.getLogger( SecretKeeperUI.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        } catch ( javax.swing.UnsupportedLookAndFeelException ex ) {
            java.util.logging.Logger.getLogger( SecretKeeperUI.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater( new Runnable() {
            public void run() {
                new SecretKeeperUI().setVisible( true );
            }
        } );
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem decryptMenuItem;
    private javax.swing.JRadioButton decryptRadioButton;
    private javax.swing.ButtonGroup encryptButtonGroup;
    private javax.swing.JMenuItem encryptFileMenuItem;
    private javax.swing.JRadioButton encryptRadioButton;
    private javax.swing.JCheckBoxMenuItem enforceGoodPasswordMenuItem;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JButton goButton;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JPanel inputPanel;
    private javax.swing.JTextArea inputTextArea;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPanel outputPanel;
    private javax.swing.JTextArea outputTextArea;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JTextField passwordTextField;
    // End of variables declaration//GEN-END:variables
}
