package xfmigration.migration.exception;

/**
 * A base XFMigration exception.
 */
public class XFMigrationException extends Exception {
    
    /**
     * A default serial version.
     */
    protected static final long serialVersionUID = 1L;

    /**
     * A default constructor.
     */
    public XFMigrationException() {
        super();
    }
    
    /**
     * A constructor.
     * @param message String value of an exception message.
     */
    public XFMigrationException(String message) {
        super(message);
    }
    
    /**
     * A constructor.
     * @param message String value of an exception message.
     * @param ex {@link Throwable} implementation.
     */
    public XFMigrationException(String message, Throwable ex) {
        super(message, ex);
    }
    
}
