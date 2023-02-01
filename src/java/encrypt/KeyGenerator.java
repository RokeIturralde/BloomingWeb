/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encrypt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roke
 */
public class KeyGenerator {

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
            //Create the file to save the public key
            File filePublic = new File("./src/files/public.key");
            //Writting the public key into an external File
            fileWriter(filePublic.getPath(), keyPair.getPublic().getEncoded());
            //Create the file to save the private key
            File filePrivate = new File("./src/files/private.key");
            //Writting the private key into an external File
            fileWriter(filePrivate.getPath(), keyPair.getPrivate().getEncoded());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(KeyGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fileWriter(String path, byte[] text) {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(text);
        } catch (IOException e) {
            Logger.getLogger(KeyGenerator.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
