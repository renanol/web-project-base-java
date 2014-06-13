package br.com.kamaleon.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DES extends Crypto
{

    /** Constante para criptografação usando o algoritmo DES */
    private static final String strKey = "EBRFAMLLL@IOLROOAO";

    public void init()
        throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException,
               NoSuchPaddingException
    {
        DESKeySpec desKeySpec = new DESKeySpec(strKey.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(desKeySpec);

        setEncryptCipher(Cipher.getInstance("DES"));
        setDecryptCipher(Cipher.getInstance("DES"));
        getEncryptCipher().init(Cipher.ENCRYPT_MODE, key);
        getDecryptCipher().init(Cipher.DECRYPT_MODE, key);
    }

    public static void main(String[] args)
    {
        try {
//            byte[] desKeyData = { (byte)0x02, (byte)0x02, (byte)0x07, 
//                (byte)0x04, (byte)0x01, (byte)0x03, (byte)0x08, (byte)0x08 };
//            DESKeySpec desKeySpec = new DESKeySpec(desKeyData);
//            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
//            SecretKey key = keyFactory.generateSecret(desKeySpec);

            // Generate a temporary key. In practice, you would save this key.
            // See also e464 Encrypting with DES Using a Pass Phrase.
//            SecretKey key = KeyGenerator.getInstance("DES").generateKey();

            // Create encrypter/decrypter class
            DES encrypter = new DES();
            encrypter.init();

            // Encrypt
            String encrypted = encrypter.encrypt("12345678");
            System.out.println(encrypted);
            System.out.println(encrypted.length());

            // Decrypt
            String decrypted = encrypter.decrypt(encrypted);
            System.out.println(decrypted);
        }
        catch (Exception e) {
        }
    }

}
