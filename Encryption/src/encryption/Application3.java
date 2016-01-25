/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryption;

import Utils.Utils;
import static encryption.Application2.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author jeroe
 */
public class Application3 {

    static Utils utils = new Utils();

    public static void main(String[] args) {
        try {
            File input = new File("INPUT(SIGNEDbyLK.EXT");
            PublicKey publicKey = (PublicKey) utils.getKey(new File("PublicKey.p12"));
            Signature sign = Signature.getInstance("SHA1withRSA");
            sign.initVerify(publicKey);

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(input));

            
            int length = (int)ois.readInt();
            byte[] sig = (byte[]) ois.readObject();
            String data = (String) ois.readObject();
            //byte[] ciphered = Base64.getDecoder().decode();
            
            //Cipher cipher = Cipher.getInstance("RSA");
            //cipher.init(Cipher.DECRYPT_MODE, publicKey);
            //byte[] decrypted = cipher.doFinal((byte[]) ois.readObject());

            //System.out.println(data);
                       
            boolean verifies = sign.verify(sig);
            
            if(verifies)
            {
                System.out.println(verifies + "\n" + data);
            }
            else
            {
                System.out.println(verifies);
            }

        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException | ClassNotFoundException | SignatureException ex) {
            Logger.getLogger(Application3.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
