/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import webcalculator.NegativeNumberException_Exception;

/**
 *
 * @author jeroe
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NegativeNumberException_Exception {
        int i = 3;
        int j = 4;
        int result = add(i, j);
        System.out.println("Result = " + result);
    }

    private static int add(int arg0, int arg1) throws NegativeNumberException_Exception {
        webcalculator.WebCalculator_Service service = new webcalculator.WebCalculator_Service();
        webcalculator.WebCalculator port = service.getWebCalculatorPort();
        return port.add(arg0, arg1);
    }

}
