/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryption;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import static java.lang.Math.random;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 *
 * @author jeroe
 */
public class Application1 {

    static Utils utils = new Utils();

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        utils.generateKeys();
    }
}
