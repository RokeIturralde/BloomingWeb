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
        final String algorithm = "RSA";
        KeyPairGenerator keyPairGenerator;
        try {
            //Creating a KeyPairGenerator Instance
            keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
            //Initializing the KeyPairGenerator
            keyPairGenerator.initialize(2048);
            //Generating a Key Pair
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic(); // Clave P�blica
            PrivateKey privateKey = keyPair.getPrivate(); // Clave Privada
            //Create the file to save the public key
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey.getEncoded());
            FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\minyb\\OneDrive\\Documentos\\Reto2\\BloomingWeb\\src\\files\\public.key");
            fileOutputStream.write(x509EncodedKeySpec.getEncoded());
            fileOutputStream.close();
            //FileOutputStream filePublic = new FileOutputStream("C:\\Users\\minyb\\OneDrive\\Documentos\\Reto2\\BloomingWeb\\src\\files\\public.key");
            //Create the file to save the private key
            PKCS8EncodedKeySpec pKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
            fileOutputStream = new FileOutputStream("C:\\Users\\minyb\\OneDrive\\Documentos\\Reto2\\BloomingWeb\\src\\files\\private.key");
            fileOutputStream.write(pKCS8EncodedKeySpec.getEncoded());
            fileOutputStream.close();
            //FileOutputStream filePrivate = new FileOutputStream("C:\\Users\\minyb\\OneDrive\\Documentos\\Reto2\\BloomingWeb\\src\\files\\private.key");

        } catch (Exception e){
            
        }
    }

    private void fileWriter(String path, byte[] text) {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(text);
        } catch (IOException e) {
            Logger.getLogger(Cryptology.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public byte[] encrypt(byte[] mensaje) {
        Cipher cipher;
        byte[] contrasenaCifrada = null;
        PublicKey key;
        try {
            // Leemos la clave publica del archivo en el cual lo hemos escrito
            key = readPublicKey(getClass().getResource("Public.key").getPath());
            // Obtenemos una instancide de Cipher con el algoritmos que vamos a usar "RSA/ECB/OAEPWithSHA1AndMGF1Padding"
            cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
            // Iniciamos el Cipher en ENCRYPT_MODE y le pasamos la clave publica
            cipher.init(Cipher.ENCRYPT_MODE, key);
            // Le decimos que cifre (método doFinal(mensaje))
            contrasenaCifrada = cipher.doFinal(mensaje);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(Cryptology.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contrasenaCifrada;
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
    
    public byte[] decrypt(byte[] ciphertext) {
        Cipher cipher;
        byte[] bs = null;
        PrivateKey key;
        try {
            // Leemos la clave privada del archivo en el cual lo hemos escrito
            key = readPrivateKey(getClass().getResource("Private.key").getPath());
            // Obtenemos una instancide de Cipher con el algoritmos que vamos a usar "RSA/ECB/OAEPWithSHA1AndMGF1Padding"
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            // Iniciamos el Cipher en DECRYPT_MODE y le pasamos la clave privada
            cipher.init(Cipher.DECRYPT_MODE, key);
            // Le decimos que descifre (método doFinal(mensaje))
            bs = cipher.doFinal(ciphertext);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(Cryptology.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bs;
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
