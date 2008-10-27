package xfmigration.mapping;

import xfmigration.exporter.IProcessingMonitor;
import edu.stanford.smi.protegex.owl.jena.JenaOWLModel;

/**
 * An interface to define methods to perform mapping process.
 */
public interface IMappingService {

    /**
     * Transforms instances of the source ontology into the instances of the
     * target ontology.
     * 
     * Assumes that instances are separated from the ontology definition.
     * 
     * @param mappingOntURL
     *            mapping ontology URL
     * @param dataURL
     *            URL to a file containing instances of source ontology
     * @param monitor
     *            An implementation of {@link IProcessingMonitor} which holds
     *            String values of messages returned by method execution.
     * @return resulting OWL model or null when mapping has failed
     */
    JenaOWLModel map(String mappingOntURL, String dataURL, IProcessingMonitor monitor);

    /**
     * Transforms instances of the source ontology into the instances of the
     * target ontology. Instances of the source ontology are located at the same
     * URL that the source ontology definition.
     * 
     * @param mappingOntURL
     *            mapping ontology URL
     * @param monitor
     *            an implementation of {@link IProcessingMonitor} which holds
     *            log data as String values.
     * @return resulting OWL model or null when mapping has failed
     */
    JenaOWLModel map(String mappingOntURL, IProcessingMonitor monitor);

}
