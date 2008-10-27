package xfmigration.exporter;

import java.util.List;

import org.mindswap.owl.OWLKnowledgeBase;
import org.mindswap.owls.process.InputList;
import org.mindswap.owls.process.OutputList;
import org.mindswap.query.ValueMap;

import xfmigration.migration.exception.OWLSExecutionException;

/**
 * An interface to define operations regarding calling a semantic web service in
 * owl-s API.
 */
public interface IServiceRunner {

    /**
     * A method to invoke a semantic web service.
     * 
     * @param paramsMap
     *            a Map implementation containing parameters for service call.
     *            Key of type String and a value of type String.
     * @return String value returned by a called service.
     * @throws OWLSExecutionException
     *             thrown when service has been unsuccessfully initialized.
     */
    String executeService(ValueMap paramsMap) throws OWLSExecutionException;

    /**
     * A method to retrieve a List of SWS inputs' names as String values.
     * 
     * @return A List of service input parameters' names of type String.
     */
    List<String> getInputParameterNames();

    /**
     * A method to retrieve a List of SWS outputs' names as String values.
     * 
     * @return A List of SWS output parameters' names of type String.
     */
    List<String> getOutputParameterNames();

    /**
     * A method to retrieve a List of SWS input parameters as mindswap API Input
     * objects.
     * 
     * @return An InputList of Input objects.
     */
    InputList getInputParameters();

    /**
     * A method to retrieve a List of SWS output parameters as mindswap API
     * Output objects.
     * 
     * @return An OutputList of Output objects.
     */
    OutputList getOutputParameters();

    /**
     * A method to dispose resources.
     */
    void dispose();

    /**
     * Provides an {@link InputList} of {@link Input} instances of type
     * {@link OWLClass}.
     * 
     * @param kb
     *            Knowledge base reference.
     * @return InputList of Input.
     */
    InputList getOWLClassInputs(OWLKnowledgeBase kb);

    /**
     * Provides an {@link InputList} of {@link Input} instances of type
     * {@link OWLClass}.
     * 
     * @param kb
     *            Knowledge base reference.
     * @return {@link InputList} of {@link Input}.
     */
    InputList getOWLObjectPropertyInputs(OWLKnowledgeBase kb);
}
