/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author 2dam
 */
public class NotThePasswordException extends Exception {

    /**
     * Creates a new instance of <code>NotThePasswordException</code> without
     * detail message.
     */
    public NotThePasswordException() {
    }

    /**
     * Constructs an instance of <code>NotThePasswordException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NotThePasswordException(String msg) {
        super("Wrong password, try another one or try to recover it");
    }
}
