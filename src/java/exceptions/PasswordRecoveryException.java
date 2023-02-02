/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Roke
 */
public class PasswordRecoveryException extends Exception {

    /**
     * Creates a new instance of <code>PasswordRecoveryException</code> without
     * detail message.
     */
    public PasswordRecoveryException() {
    }

    /**
     * Constructs an instance of <code>PasswordRecoveryException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PasswordRecoveryException(String msg) {
        super(msg);
    }
}
