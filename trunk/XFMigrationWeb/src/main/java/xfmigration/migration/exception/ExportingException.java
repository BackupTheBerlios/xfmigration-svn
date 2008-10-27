package xfmigration.migration.exception;


/**
 * A Class of exceptions that may occur during export.
 */
public class ExportingException extends XFMigrationException {
    
    /**
     * A generated serial version id.
     */
    static final long serialVersionUID = 583241635256073760L;

    /**
     * A Default Constructor.
     */
    public ExportingException() {
        super();
    }

    /**
     * A constructor.
     * 
     * @param message
     *            String value of an error message.
     */
    public ExportingException(String message) {
        super(message);
    }
    
    /**
     * A constructor.
     * @param message String value of exception message.
     * @param ex {@link Throwable} implementation.
     */
    public ExportingException(String message, Throwable ex) {
        super(message, ex);
    }
}
