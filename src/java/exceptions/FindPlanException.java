/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author minyb
 */
public class FindPlanException extends Exception {

    /**
     * Creates a new instance of <code>FindContentException</code> without
     * detail message.
     */
    public FindPlanException() {
    }

    /**
     * Constructs an instance of <code>FindContentException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public FindPlanException(String msg) {
        super(msg);
    }
}
