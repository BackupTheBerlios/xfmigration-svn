package xfmigration.migration.exception;

/**
 * An exception indicating errors during importing process. 
 */
public class ImportingException extends XFMigrationException {

    /**
     * A serial version id.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * A default constructor.
     */
    public ImportingException() {
        super();
    }
    
    /**
     * A constructor.
     * @param msg String value of exception message.
     */
    public ImportingException(String msg) {
        super(msg);
    }
    
    /**
     * A constructor.
     * @param message String value of exception message.
     * @param ex {@link Throwable} implementation.
     */
    public ImportingException(String message, Throwable ex) {
        super(message, ex);
    }
}
