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
public class LoginDoesNotExistException extends Exception {

    /**
     * Creates a new instance of <code>LoginDoesNotExistException</code> without
     * detail message.
     */
    public LoginDoesNotExistException() {
    }

    /**
     * Constructs an instance of <code>LoginDoesNotExistException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public LoginDoesNotExistException(String msg) {
        super("That login is not found, try with another one");
    }
}
