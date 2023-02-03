/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Nerea
 */
public class ReadException extends Exception {

    /**
     * Creates a new instance of <code>ReadException</code> without detail
     * message.
     */
    public ReadException() {
    }

    /**
     * Constructs an instance of <code>ReadException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public ReadException(String msg) {
        super(msg);
    }
}
