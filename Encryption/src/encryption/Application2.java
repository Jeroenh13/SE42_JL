/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryption;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jeroe
 */
public class Application2 {

    static Utils utils = new Utils();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            String input = utils.readFile(new File("INPUT.EXT"));
            PrivateKey privateKey = (PrivateKey)utils.getKey(new File("PrivateKey.p12"));
            
            Signature sign = Signature.getInstance("SHA1withRSA");
            sign.initSign( privateKey);
            byte[] signLength = sign.sign();
            
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("INPUT(SIGNEDbyLK.EXT")));
            
            oos.writeInt(signLength.length);
            oos.writeObject(signLength);
            oos.writeObject(input);
                        
            oos.close();
        } catch (Exception e) {
            Logger.getLogger(Application2.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }
}
