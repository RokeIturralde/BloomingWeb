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
public class FindAllException extends Exception {

    /**
     * Creates a new instance of <code>FindAllContent</code> without detail
     * message.
     */
    public FindAllException() {
    }

    /**
     * Constructs an instance of <code>FindAllContent</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public FindAllException(String msg) {
        super(msg);
    }
}
