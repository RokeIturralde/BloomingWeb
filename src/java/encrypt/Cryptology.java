/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
/**
 *
 * @author minyb
 */
public class Cryptology {
    public void generate() {
        KeyPairGenerator generator;
        try {
            generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024); 
            KeyPair keypair = generator.generateKeyPair();
            PublicKey publicKey = keypair.getPublic();
            PrivateKey privateKey = keypair.getPrivate();

            // Public
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey.getEncoded());
            FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\minyb\\OneDrive\\Documentos\\Reto2\\BloomingWeb\\src\\java\\encrypt\\public.key");
            fileOutputStream.write(x509EncodedKeySpec.getEncoded());
            fileOutputStream.close();

            // Private
            PKCS8EncodedKeySpec pKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
            fileOutputStream = new FileOutputStream("C:\\Users\\minyb\\OneDrive\\Documentos\\Reto2\\BloomingWeb\\src\\java\\encrypt\\private.key");
            fileOutputStream.write(pKCS8EncodedKeySpec.getEncoded());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }

    private void fileWriter(String path, byte[] text) {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(text);
        } catch (IOException e) {
            Logger.getLogger(Cryptology.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public byte[] encrypt(String mensaje) {
        byte[] encodedMessage = null;
        try {
            byte fileKey[] = fileReader(getClass().getResource("public.key").getPath());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(fileKey);
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            c.init(Cipher.ENCRYPT_MODE, publicKey);
            encodedMessage = c.doFinal(mensaje.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodedMessage;
    }
    
    public static String hashPassword(String texto) {
        MessageDigest messageDigest;
        String passwordHashed = "";
        try {
            messageDigest = MessageDigest.getInstance("MD5");//"SHA-256"; Otra vez SHA-256???? es MD5
            byte dataBytes[] = messageDigest.digest(texto.getBytes()); // Texto a bytes
            messageDigest.update(dataBytes);
            byte resumen[] = messageDigest.digest(dataBytes); // Se calcula el resumen
            passwordHashed = hexadecimal(resumen);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return passwordHashed;
    }
    
    public static String hexadecimal(byte[] encryptedText) {
        char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        StringBuffer buf = new StringBuffer();
        
        for (int j = 0; j < encryptedText.length; j++) {
            buf.append(hexDigit[(encryptedText[j] >> 4) & 0x0f]);
            buf.append(hexDigit[encryptedText[j] & 0x0f]);
        }
        return buf.toString();
    }
    
    public byte[] decrypt(byte[] mensaje) {
        byte[] decodedMessage = null;
        try {
            byte fileKey[] = fileReader(getClass().getResource("private.key").getPath());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(fileKey);
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            c.init(Cipher.DECRYPT_MODE, privateKey);
            decodedMessage = c.doFinal(mensaje);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decodedMessage;
    }
    
    public static PublicKey readPublicKey(String filename) {
        X509EncodedKeySpec publicSpec;
        KeyFactory keyFactory;
        PublicKey publicKey = null;
        try {
            publicSpec = new X509EncodedKeySpec(fileReader(filename));
            keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(publicSpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(Cryptology.class.getName()).log(Level.SEVERE, null, ex);
        }
        return publicKey;
    }

    private static byte[] fileReader(String path) {
        byte ret[] = null;
        File file = new File(path);
        try {
            ret = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            Logger.getLogger(Cryptology.class.getName()).log(Level.SEVERE, null, e);
        }
        return ret;
    }
    
    public PrivateKey readPrivateKey(String filename) {
        PKCS8EncodedKeySpec keySpec;
        KeyFactory keyFactory;
        PrivateKey privateKey = null;
        try {
            keySpec = new PKCS8EncodedKeySpec(fileReader(filename));
            keyFactory = KeyFactory.getInstance("RSA");
            privateKey = keyFactory.generatePrivate(keySpec);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException ex) {
            Logger.getLogger(Cryptology.class.getName()).log(Level.SEVERE, null, ex);
        }
        return privateKey;
    }
}
