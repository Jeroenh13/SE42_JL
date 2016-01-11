/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webCalculator;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import service.NegativeNumberException;
import service.PositiveCalculatorService;

/**
 *
 * @author jeroe
 */
@WebService(serviceName = "WebCalculator")
public class WebCalculator {

    /**
     * Web service operation
     */
    
    private PositiveCalculatorService positiveCalculatorService = new PositiveCalculatorService();

    @WebMethod(operationName = "add")
    public int add(int x, int y) throws NegativeNumberException {
        return positiveCalculatorService.add(x, y);
    }

    @WebMethod(operationName = "minus")
    public int minus(int x, int y) throws NegativeNumberException {
        return positiveCalculatorService.minus(x, y);
    }

    @WebMethod(operationName = "times")
    public int times(int x, int y) throws NegativeNumberException {
        return positiveCalculatorService.times(x, y);
    }
}
