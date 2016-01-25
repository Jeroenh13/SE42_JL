/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryption;

import Utils.Utils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.security.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;

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
            Key publicKey = utils.getKey(new File("PublicKey.p12"));
            
            Signature sign = Signature.getInstance("SHA1withRSA");
            sign.initSign(privateKey);
            byte[] signLength = sign.sign();
            
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("INPUT(SIGNEDbyLK.EXT")));
            
            //Cipher cipher = Cipher.getInstance("RSA");
            ///cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            //byte[] ciphered = cipher.doFinal(input.getBytes("UTF8"));
            
            oos.writeInt(signLength.length);
            oos.writeObject(signLength);
            oos.writeObject(input);
                        
            oos.close();
        } catch (Exception e) {
            Logger.getLogger(Application2.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }
}
