package xfmigration.exporter;

/**
 * An implementation of {@link IProcessingMonitor}.
 */
import java.util.ArrayList;
import java.util.Collection;

/**
 * A default implementation of {@link IProcessingMonitor} interface. Used to store processing 
 * information.
 */
public class ProcessingMonitorImpl implements IProcessingMonitor {

    /**
     * A {@link Collection} of String messages.
     */
    private Collection<String> messages = new ArrayList<String>();

    /**
     * A method to add message to a collection.
     * 
     * @param msg
     *            a message String value.
     */
    public synchronized void addMessage(String msg) {
        messages.add(msg);
    }

    /**
     * A method to retrieve a {@link Collection} of String messages.
     * 
     * @return A {@link Collection} of String values.
     */
    public synchronized Collection<String> getMessages() {
        return messages;
    }

    /**
     * A method to clear the {@link Collection} of String messages.
     */
    public synchronized void clear() {
        messages.clear();
    }
}
