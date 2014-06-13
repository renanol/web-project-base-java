package br.com.kamaleon.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public abstract class Crypto
{

    /** Holds value of property encryptCipher. */
    private Cipher encryptCipher;
    
    /** Holds value of property decryptCipher. */
    private Cipher decryptCipher;
    
    /** Getter for property encryptCipher.
     * @return Value of property encryptCipher.
     *
     */
    public Cipher getEncryptCipher() {
        return this.encryptCipher;
    }
    
    /** Setter for property encryptCipher.
     * @param encryptCipher New value of property encryptCipher.
     *
     */
    public void setEncryptCipher(Cipher encryptCipher) {
        this.encryptCipher = encryptCipher;
    }
    
    /** Getter for property decryptCipher.
     * @return Value of property decryptCipher.
     *
     */
    public Cipher getDecryptCipher() {
        return this.decryptCipher;
    }
    
    /** Setter for property decryptCipher.
     * @param decryptCipher New value of property decryptCipher.
     *
     */
    public void setDecryptCipher(Cipher decryptCipher) {
        this.decryptCipher = decryptCipher;
    }

    /**
     * Método usado para inicializar os criptografadores e decriptografadores
     * dos algoritmos especificos de criptografação.
     */
    public abstract void init()
        throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException,
               NoSuchPaddingException;

    /**
     * Método utilizado para encriptografar uma string.
     */
    public String encrypt(String str)
        throws BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException,
               IOException
    {
        // Encode the string into bytes using utf-8
        byte[] utf8 = str.getBytes("UTF8");

        // Encrypt
        byte[] enc = getEncryptCipher().doFinal(utf8);

        // Encode bytes to base64 to get a string
        return new BASE64Encoder().encode(enc);
    }

    /**
     * Método utilizado para descriptografar uma string criptografada.
     */
    public String decrypt(String str)
        throws BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException,
               IOException
    {
        // Decode base64 to get bytes
        byte[] dec = new BASE64Decoder().decodeBuffer(str);

        // Decrypt
        byte[] utf8 = getDecryptCipher().doFinal(dec);

        // Decode using utf-8
        return new String(utf8, "UTF8");
    }

}
