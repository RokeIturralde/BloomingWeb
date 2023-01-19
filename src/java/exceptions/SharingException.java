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
public class SharingException extends Exception {

    /**
     * Creates a new instance of <code>SharingException</code> without detail
     * message.
     */
    public SharingException() {
    }

    /**
     * Constructs an instance of <code>SharingException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public SharingException(String msg) {
        super("Error while sharing an album, try again later");
    }
}
