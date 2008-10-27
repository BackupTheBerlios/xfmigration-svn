package xfmigration.exporter.util;

/**
 * A class to define values for process types.
 */
public final class AtomicProcessExecutionType {
	
	/**
	 *  To mark a process with a single input. 
	 */
	public static final int SINGLE_INPUT_CLASS_TYPE = 1;
	
	/**
	 *  To mark a process with a single input of object property type. 
	 */
	public static final int SINGLE_PROPERTY_MULTI_VALUE = 2;
	
	/**
	 *  To mark a process with a number of object property type inputs. 
	 */
	public static final int MULTI_PROPERTY = 3;
	
	/**
	 *  To mark a process type which is not supported by execution method. 
	 */
	public static final int NOT_SUPPORTED = -1;
	
	/**
	 * A private constructor of an utility class.
	 */
	private AtomicProcessExecutionType() {
		
	}
}
