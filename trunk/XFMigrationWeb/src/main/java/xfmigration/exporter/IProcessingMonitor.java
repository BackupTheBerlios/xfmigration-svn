package xfmigration.exporter;

import java.util.Collection;

/**
 * An interface to define a type of object to store log messages of migration
 * process.
 */
public interface IProcessingMonitor {

    /**
     * A method to add messages to a collection.
     * 
     * @param msg
     *            A string value of message.
     */
    void addMessage(String msg);

    /**
     * A method to get a collection of messages.
     * 
     * @return A {@link Collection} of message String values.
     */
    @SuppressWarnings("unchecked")
    Collection getMessages();

    /**
     * A method to clear a {@link Collection} of messages.
     */
    void clear();
}
