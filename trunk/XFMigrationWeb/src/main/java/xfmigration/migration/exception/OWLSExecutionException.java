package xfmigration.migration.exception;

/**
 * An exception thrown if OWLS service file execution errors occur. 
 */
public class OWLSExecutionException extends XFMigrationException {
    
    /**
     * A generated serial version id.
     */
    private static final long serialVersionUID = 1L;

    /**
     * A default constructor.
     */
    public OWLSExecutionException() {
        super();
    }
    
    /**
     * A constructor.
     * @param message String value of error message.
     */
    public OWLSExecutionException(String message) {
        super(message);
    }
    
    /**
     * A constructor.
     * @param message A String value of exception message.
     * @param ex {@link Throwable} implementation.
     */
    public OWLSExecutionException(String message, Throwable ex) {
        super(message, ex);
    }
}
