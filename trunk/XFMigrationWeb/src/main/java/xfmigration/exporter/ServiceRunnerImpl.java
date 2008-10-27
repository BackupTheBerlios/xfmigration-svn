package xfmigration.exporter;

import impl.owls.process.InputListImpl;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.mindswap.owl.OWLClass;
import org.mindswap.owl.OWLEntity;
import org.mindswap.owl.OWLFactory;
import org.mindswap.owl.OWLKnowledgeBase;
import org.mindswap.owl.OWLObjectProperty;
import org.mindswap.owls.OWLSFactory;
import org.mindswap.owls.process.Input;
import org.mindswap.owls.process.InputList;
import org.mindswap.owls.process.Output;
import org.mindswap.owls.process.OutputList;
import org.mindswap.owls.process.Process;
import org.mindswap.owls.process.execution.ProcessExecutionEngine;
import org.mindswap.owls.service.Service;
import org.mindswap.query.ValueMap;

import xfmigration.exporter.util.OWLUtils;
import xfmigration.migration.exception.OWLSExecutionException;

/**
 * A default implementation of IServiceRunner interface. Provides a
 * functionality to invoke SWS described by a given OWL-S document.
 */
public class ServiceRunnerImpl implements IServiceRunner {
    /**
     * An process execution engine. A routine part of SWS execution in mindswap
     * API.
     */
    private ProcessExecutionEngine exec;

    /**
     * A reference to a Service to be called.
     */
    private Service service;

    /**
     * A String value of an URL of OWL-S document.
     */
    private String serviceOWLSurl;

    /**
     * A constructor.
     * 
     * @param owlsUrl
     *            A String value of OWL-S URL.
     * @throws OWLSExecutionException
     *             exception
     */
    public ServiceRunnerImpl(String owlsUrl) throws OWLSExecutionException {
        serviceOWLSurl = owlsUrl;
        exec = OWLSFactory.createExecutionEngine();
        loadService(owlsUrl);
    }

    /**
     * A constructor.
     * 
     * @param process
     *            A reference to {@link Process} instance.
     */
    public ServiceRunnerImpl(Process process) {
        exec = OWLSFactory.createExecutionEngine();
        service = process.getService();
    }

    /**
     * A method to create a Service object from url.
     * 
     * @param owlsUrl
     *            string value of service url.
     * @throws OWLSExecutionException
     *             exception.
     */
    private void loadService(String owlsUrl) throws OWLSExecutionException {
        System.out.println("service owls url: " + serviceOWLSurl);
        if (serviceOWLSurl == null || !serviceOWLSurl.endsWith(OWLUtils.OWL_FILE_EXT)) {
            throw new OWLSExecutionException("Invalid OWLS file URL.");
        }

        try {
            service = OWLFactory.createKB().readService(URI.create(serviceOWLSurl));
        } catch (Exception e) {
            throw new OWLSExecutionException(e.getMessage());
        }
    }

    /**
     * Implements method executeService from {@link IServiceRunner}.
     * 
     * @param paramsMap
     *            A ValueMap of input parameters.
     * @return A String value of service output.
     * @throws OWLSExecutionException
     *             when errors occur during service invoking.
     */
    public String executeService(ValueMap paramsMap) throws OWLSExecutionException {
        String outValue = null;

        // if (service != null && service.getProcess() != null && exec != null)
        // {
        try {
            paramsMap = exec.execute(service.getProcess(), paramsMap);
        } catch (Exception e) {
            throw new OWLSExecutionException(e.getMessage());
        }
        if (paramsMap == null) {
            throw new OWLSExecutionException("OWLS execution not successful. No result returned.");
        }
        
        outValue = paramsMap.getValue(service.getProcess().getOutputs().outputAt(0)).toString();
        // } else {
        // throw new OWLSServiceException("OWLS service not initialized
        // properly.");
        // }
        return outValue;
    }

    /**
     * An implementation of getInputParameterNames method from
     * {@link IServiceRunner}.
     * 
     * @return A {@link List} of String values of parameter names.
     */
    @SuppressWarnings("unchecked")
    public List<String> getInputParameterNames() {

        List<String> inputParamNamesList = new ArrayList<String>();
        if (service != null) {
            InputList inputsList = service.getProcess().getInputs();
            for (Iterator<Input> itr = inputsList.iterator(); itr.hasNext();) {
                Input input = itr.next();
                System.out.println(input.getLocalName() + " -> "
                        + input.getParamType().getURI().toString());
                inputParamNamesList.add(input.getLocalName());
            }
        }
        return inputParamNamesList;
    }

    /**
     * An implementation of getOutputParameters method from
     * {@link IServiceRunner}.
     * 
     * @return An {@link OutputList} of SWS {@link Output} objects.
     */
    public OutputList getOutputParameters() {
        OutputList result = null;
        if (service != null && service.getProcess() != null) {
            result = service.getProcess().getOutputs();
        }
        return result;
    }

    /**
     * An implementation of getInputParameters method from
     * {@link IServiceRunner}.
     * 
     * @return An {@link InputList} of SWS {@link Input} objects.
     */
    public InputList getInputParameters() {
        InputList result = null;
        if (service != null && service.getProcess() != null) {
            result = service.getProcess().getInputs();
        }
        return result;
    }

    /**
     * An implementation of getOutputParameterNames method from
     * {@link IServiceRunner}.
     * 
     * @return A {@link List} of String values of output parameters' names.
     */
    @SuppressWarnings("unchecked")
    public List<String> getOutputParameterNames() {

        List<String> outputParamNamesList = new ArrayList<String>();
        if (service != null) {
            OutputList outputsList = service.getProcess().getOutputs();
            for (Iterator<Output> itr = outputsList.iterator(); itr.hasNext();) {
                Output output = itr.next();
                outputParamNamesList.add(output.getLocalName());
            }
        }
        return outputParamNamesList;
    }

    /**
     * An implementation of dispose method from {@link IServiceRunner}.
     */
    public void dispose() {
        if (service != null) {
            service = null;
        }
        if (exec != null) {
            exec = null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public InputList getOWLClassInputs(OWLKnowledgeBase kb) {
        InputList list = new InputListImpl();
        InputList baseInputs = service.getProcess().getInputs();
        for (Iterator<Input> itr = baseInputs.iterator(); itr.hasNext();) {
            Input tmp = itr.next();
            OWLEntity paramEntity = kb.getEntity(tmp.getParamType().getURI());
            if (paramEntity instanceof OWLClass) {
                list.add(tmp);
            }
        }
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public InputList getOWLObjectPropertyInputs(OWLKnowledgeBase kb) {
        InputList list = new InputListImpl();
        InputList baseInputs = service.getProcess().getInputs();
        for (Iterator<Input> itr = baseInputs.iterator(); itr.hasNext();) {
            Input tmp = itr.next();
            OWLEntity paramEntity = kb.getEntity(tmp.getParamType().getURI());
            if (paramEntity instanceof OWLObjectProperty) {
                list.add(tmp);
            }
        }
        return list;
    }

}
