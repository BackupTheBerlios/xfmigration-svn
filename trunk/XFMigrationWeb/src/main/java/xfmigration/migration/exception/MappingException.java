package xfmigration.migration.exception;

/**
 * A class to represent a mapping exception.
 */
public class MappingException extends XFMigrationException {

    /**
     * Id for unique serialization id. 
     */
    private static final long serialVersionUID = 1L;

    /**
     * A default constructor.
     */
    public MappingException() {
        super();
    }

    /**
     * A constructor.
     * @param msg String error message.
     */
    public MappingException(String msg) {
        super(msg);
    }

    /**
     * A constructor.
     * @param message String error message.
     * @param ex A root cause.
     */
    public MappingException(String message, Throwable ex) {
        super(message, ex);
    }

}
