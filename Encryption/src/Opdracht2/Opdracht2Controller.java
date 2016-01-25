/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Opdracht2;

import Utils.Utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 * FXML Controller class
 *
 * @author jeroe
 */
public class Opdracht2Controller implements Initializable {

    @FXML
    private PasswordField pwField;
    @FXML
    private Button btnEncrypt;
    @FXML
    private Button btnDecrypt;

    Utils utils = new Utils();
    @FXML
    private TextArea taMessage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Encrypt(ActionEvent event) {
        try {
            File file = utils.getEncryptFile();

            byte[] salt = new byte[8];
            SecureRandom srnd = new SecureRandom();
            srnd.nextBytes(salt);

            PBEParameterSpec paramSpec = new PBEParameterSpec(salt, 1000);

            char[] pwd = pwField.getText().toCharArray();

            PBEKeySpec keySpec = new PBEKeySpec(pwd);
            SecretKeyFactory keyFac = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
            SecretKey secretKey = keyFac.generateSecret(keySpec);

            Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, paramSpec);

            byte[] input = taMessage.getText().getBytes();

            input = cipher.doFinal(input);

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(salt);
                oos.writeObject(input);
            }

            taMessage.setText("");
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | IOException ex) {
            Logger.getLogger(Opdracht2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Decrypt(ActionEvent event) {
        try {
            File file = utils.getDecryptFile();

            byte[] salt;
            byte[] input;
            char[] pwd = pwField.getText().toCharArray();

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                salt = (byte[]) ois.readObject();
                input = (byte[]) ois.readObject();
            }

            PBEKeySpec keySpec = new PBEKeySpec(pwd);
            SecretKeyFactory keyFac = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
            SecretKey secretKey = keyFac.generateSecret(keySpec);

            PBEParameterSpec paramSpec = new PBEParameterSpec(salt, 1000);
            Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, paramSpec);

            try {
                byte[] out = cipher.doFinal(input);
                taMessage.setText(new String(out, "UTF-8"));
            } catch (BadPaddingException ex) {
                taMessage.setText("Invalid Password");
            }
        } catch (IOException | ClassNotFoundException | InvalidKeySpecException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException ex) {
            Logger.getLogger(Opdracht2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
