package exceptions;

/**
 * @author dani
 */

public class FindMemberException extends Exception {
    public FindMemberException() {}

    /**
     * @param msg the detail message
     */
    public FindMemberException(String msg) {
        super(msg);
    }
}
