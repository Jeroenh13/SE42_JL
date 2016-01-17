/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryption;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.rmi.CORBA.Util;

/**
 *
 * @author jeroe
 */
public class Encryption {

    static Utils utils = new Utils();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        try {
            File privateKey = new File("PrivateKey");
            String inputPrivateKey = utils.readFile(privateKey);
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(Base64.decode(inputPrivateKey));
            System.out.println(inputPrivateKey);
            
            //utils.WriteFile("JJ");
        }
    }

    
}
