package xfmigration.migration.exception;

/**
 *  An exception indicating that no project data is imported from a source forge. 
 */
public class NoProjectDataFoundException extends ImportingException {

    /**
     * A generated serial version ID.
     */
    private static final long serialVersionUID = -700833509922074525L;

    /**
     * A default constructor.
     */
    public NoProjectDataFoundException() {
        super();
    }
    
    /**
     * A constructor.
     * @param message String value of exception message.
     */
    public NoProjectDataFoundException(String message) {
        super(message);
    }
    
    /**
     * A constructor.
     * @param message String value of exception message.
     * @param ex {@link Throwable} implementation.
     */
    public NoProjectDataFoundException(String message, Throwable ex) {
        super(message, ex);
    }
}
