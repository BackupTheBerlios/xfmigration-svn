/*
 * IST-FP6-034763 QualiPSo: QualiPSo is a unique alliance 
 * of European, Brazilian and Chinese ICT industry players, 
 * SMEs, governments and academics to help industries and 
 * governments fuel innovation and competitiveness with Open 
 * Source software. To meet that goal, the QualiPSo consortium 
 * intends to define and implement the technologies, processes 
 * and policies to facilitate the development and use of Open 
 * Source software components, with the same level of trust 
 * traditionally offered by proprietary software. QualiPSo is 
 * partially funded by the European Commission under EU’s sixth 
 * framework program (FP6), as part of the Information Society 
 * Technologies (IST) initiative. 
 * 
 * This program has been created as part of the QualiPSo work 
 * package on "Semantic Interoperability". The basic idea is to show 
 * how semantic technologies can be used to cope with the diversity 
 * and heterogeneity of software and services in the OSS domain.
 *
 * You can redistribute this program and/or modify it under 
 * the terms of the European Union Public License v1.0 (EUPL v1.0)
 * as published by the European Commission.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  
 *
 * You should have received a copy of the EU Public License
 * along with this program; if not, please have a look at 
 * http://ec.europa.eu/idabc/en/document/6523 
 * to obtain the full license text.
 *
 * Author of this program: 
 * Fraunhofer Institute FOKUS, http://www.fokus.fraunhofer.de
 * 
 *
 */
package xfmigration.mapping;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import org.apache.log4j.Logger;
import edu.stanford.smi.protege.model.Cls;
import edu.stanford.smi.protegex.owl.jena.JenaOWLModel;
import edu.stanford.smi.protegex.owl.model.OWLClass;
import edu.stanford.smi.protegex.owl.model.OWLIndividual;
import edu.stanford.smi.protegex.owl.model.RDFIndividual;
import edu.stanford.smi.protegex.owl.model.RDFProperty;

/**
 * An utility class for debugging.
 */
public final class DebugUtils {

    private static Logger logger = Logger.getLogger(DebugUtils.class);

    /**
     * Default Constructor.
     */
    private DebugUtils() {
    }

    /**
     * Logs information about inconsistent concepts.
     * 
     * @param owlModel
     *            A reference to {@link JenaOWLModel} object.
     */
    @SuppressWarnings("unchecked")
    public static void printInconsistentConcepts(JenaOWLModel owlModel) {
        logger.debug("*** Printing inconsistent classes '" + owlModel.getName() + "' ***");

        Collection inconsistentClasses = owlModel.getInconsistentClasses();
        if (inconsistentClasses.size() == 0) {
            logger.debug("*** There are no inconsistent classes ***");
            return;
        }
        logger.debug("*** There are following inconsistent classes in the model: ***");
        for (Iterator iter = inconsistentClasses.iterator(); iter.hasNext();) {
            Class element = (Class) iter.next();
            logger.debug("    inconsistentClass: " + element);
        }
    }

    /**
     * Logs the content of a given {@link JenaOWLModel}.
     * 
     * @param owlModel
     *            A reference to {@link JenaOWLModel} object.
     */
    @SuppressWarnings("unchecked")
    public static void printIndividualsWithAssertedTypes(JenaOWLModel owlModel) {
        logger.debug("*** Printing asserted Individuals in '"
                + owlModel.getNamespaceManager().getDefaultNamespace() + "' ***");

        Collection<OWLIndividual> individuals = owlModel.getOWLIndividuals();
        Collection<Cls> types = null;
        logger.debug("Number of individuals: " + individuals.size());
        for (Iterator<OWLIndividual> it = individuals.iterator(); it.hasNext();) {
            OWLIndividual individual = it.next();
            if (!individual.isDeleted()) {
                logger.debug("Individual '" + individual.getLocalName() + "' (namespace: "
                        + individual.getNamespace() + ")");
                if (individual.getNamespace() == null) {
                    continue;
                }
                // if (individual.getNamespace().indexOf(sourceOntURI) == -1
                // && individual.getName().indexOf("SWRLCreated") == -1) {
                // continue;
                // }
                logger.debug("has asserted types and properties: ");
                logger.debug(" Types:");
                types = individual.getRDFTypes();
                for (Iterator<Cls> typesIt = types.iterator(); typesIt.hasNext();) {
                    Cls type = typesIt.next();
                    logger.debug("    " + type.getName());
                }
                
                printIndividualProperties(individual);
            }

        }
        logger.debug("*** (finished) printing asserted Individuals ***");

    }

    /**
     * A method to log individual properties.
     * @param individual An instance of {@link OWLIndividual}
     */
     @SuppressWarnings("unchecked")
    public static void printIndividualProperties(OWLIndividual individual) {
         
         logger.debug("Individual '" + individual.getName() + "' has properties:");
         Collection rdfProperties = individual.getRDFProperties();
         for (Iterator iter = rdfProperties.iterator(); iter.hasNext();) {
             RDFProperty property = (RDFProperty) iter.next();
             logger.debug("    property '" + property.getName() + " (namespace: "
                     + property.getNamespace() + ")");
             logger.debug("    has values: ");
             Collection propValues = individual.getPropertyValues(property, true);
             for (Iterator iterator = propValues.iterator(); iterator.hasNext();) {
                 Object propValue = (Object) iterator.next();
                 logger.debug("        " + propValue);
             }
             logger.debug("");
         }
  
     }
    
    /**
     * Logs the content of individuals of inferred types.
     * 
     * @param owlModel
     *            A reference to {@link JenaOWLModel} object.
     */
    @SuppressWarnings("unchecked")
    public static void printIndividualsWithInferredTypes(JenaOWLModel owlModel) {
        logger.debug("*** Printing inferred Individuals in '"
                + owlModel.getNamespaceManager().getDefaultNamespace() + "' ***");

        Collection<OWLIndividual> individuals = owlModel.getOWLIndividuals(true);
        Collection<Cls> types = null;
        logger.debug("Number of inferred Individuals: " + individuals.size());
        for (Iterator<OWLIndividual> it = individuals.iterator(); it.hasNext();) {
            OWLIndividual individual = it.next();
            if (!individual.isDeleted()) {
                types = individual.getInferredTypes();
                if (types.size() == 0) {
                    continue;
                }
                logger.debug("Individual '" + individual.getName() + "' " + " (namespace: "
                        + individual.getNamespace() + ")" + " has inferred types:");
                for (Iterator<Cls> typesIt = types.iterator(); typesIt.hasNext();) {
                    Cls type = typesIt.next();

                    logger.debug("    " + type.getName() + " (instances: "
                            + type.getInstanceCount() + "):" + " (namespace: "
                            + individual.getNamespace() + ")");

                }
                printIndividualProperties(individual);
            }
        }
        logger.debug("*** (finished) Printing inferred Individuals ***");
    }

    /**
     * An utility method to log the used defined OWL classes.
     * 
     * @param clses
     *            A {@link Collection} of {@link OWLClass} objects.
     */
    @SuppressWarnings("unchecked")
    public static void printUserClasses(Collection clses) {
        logger.debug("*** Printing User Definied CLases ***");
        logger.debug("Number of classes: " + clses.size());
        Collection<RDFIndividual> instances = new HashSet<RDFIndividual>();
        for (Iterator<OWLClass> classIt = clses.iterator(); classIt.hasNext();) {
            OWLClass cls = classIt.next();
            logger.debug(cls.getName() + "(" + cls.getInstances(true).size() + ")");
            instances.addAll(cls.getInstances(true));
        }
        logger.debug("Number of ins: " + instances.size());
        for (Iterator<RDFIndividual> iti = instances.iterator(); iti.hasNext();) {
            RDFIndividual individual = iti.next();
            logger.debug(" individual: " + individual.getName());
        }
        logger.debug("*** (finished) Printing User Definied CLases ***");
    }

}
