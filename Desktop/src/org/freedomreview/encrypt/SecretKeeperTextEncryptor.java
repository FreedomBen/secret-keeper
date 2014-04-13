package org.freedomreview.encrypt;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.util.text.TextEncryptor;

public final class SecretKeeperTextEncryptor implements TextEncryptor {

    private final StandardPBEStringEncryptor encryptor;
    
    
    /**
     * Creates a new instance of <tt>SecretKeeperTextEncryptor</tt>.
     * 
     * This implementation uses MD5 hashing with 3DES block encryption
     * 
     * The choice of using 3DES here instead of AES is a result of an 
     * open bug with Java where SecretKeyFactory (a dependent class)
     * does not support AES.  3DES is considered unbreakable as long as
     * the size of the data is less than 2^64/2 blocks (or about 32 GB)
     */
    public SecretKeeperTextEncryptor() {
        super();
        this.encryptor = new StandardPBEStringEncryptor();
        this.encryptor.setAlgorithm("PBEWithMD5AndTripleDES");
    }

    
    /**
     * Sets a password.
     * 
     * @param password the password to be set.
     */
    public void setPassword(final String password) {
        this.encryptor.setPassword(password);
    }

    
    /**
     * Sets a password, as a char[]
     * 
     * @since 1.8
     * @param password the password to be set.
     */
    public void setPasswordCharArray(final char[] password) {
        this.encryptor.setPasswordCharArray(password);
    }

    
    /**
     * Encrypts a message.
     * 
     * @param message the message to be encrypted.
     * @see StandardPBEStringEncryptor#encrypt(String)
     */
    public String encrypt(final String message) {
        return this.encryptor.encrypt(message);
    }

    
    /**
     * Decrypts a message.
     * 
     * @param encryptedMessage the message to be decrypted.
     * @see StandardPBEStringEncryptor#decrypt(String)
     */
    public String decrypt(final String encryptedMessage) {
        return this.encryptor.decrypt(encryptedMessage);
    }
}


