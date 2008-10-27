package xfmigration.migration.exception;

/**
 * An exception indicating that the mapping resulting model is not valid, hence
 * there is no data to be exported.
 */
public class InvalidDataModelException extends ExportingException {

    /**
     * A generated serial version ID.
     */
    private static final long serialVersionUID = -7036981842356448237L;
    
    /**
     * A default constructor.
     */
    public InvalidDataModelException() {
        super();
    }

    /**
     * A constructor.
     * @param message String value of exception message.
     */
    public InvalidDataModelException(String message) {
        super(message);
    }
}
