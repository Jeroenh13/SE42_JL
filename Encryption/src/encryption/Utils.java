/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryption;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jeroe
 */
public class Utils {

    public Utils() {

    }

    public String readFile(File file) {
        FileInputStream fis = null;
        String result = "";
        try {
            fis = new FileInputStream(file);
            byte[] input = new byte[(int) file.length()];
            fis.read(input);
            result = new String(input);
        } catch (IOException ex) {
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public Key loadPrivateKey(String filename) {
        ObjectInputStream fileIn = null;
        Key privateKey = null;
        try {
            fileIn = new ObjectInputStream(new FileInputStream(filename));
            File result = null;
            privateKey = (Key) fileIn.readObject();

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileIn.close();
            } catch (IOException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return privateKey;
    }

    public void generateKeys() throws NoSuchAlgorithmException, IOException {
        int KEYSIZE = 1024;
        KeyPairGenerator pairgen = KeyPairGenerator.getInstance("RSA");
        SecureRandom random = new SecureRandom();
        pairgen.initialize(KEYSIZE, random);
        KeyPair keyPair = pairgen.generateKeyPair();
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("PublicKey"))) {
            out.writeObject(keyPair.getPublic());
        }
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("PrivateKey"))) {
            out.writeObject(keyPair.getPrivate());
        }
    }

    public void WriteFile(String name) {

    }

}
