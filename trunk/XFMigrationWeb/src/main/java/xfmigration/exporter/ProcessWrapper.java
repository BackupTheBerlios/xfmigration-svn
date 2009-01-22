package xfmigration.exporter;

import impl.owls.process.InputListImpl;

import java.net.URI;
import java.util.Iterator;
import java.util.List;

import org.mindswap.owl.OWLClass;
import org.mindswap.owl.OWLEntity;
import org.mindswap.owl.OWLIndividual;
import org.mindswap.owl.OWLIndividualList;
import org.mindswap.owl.OWLObjectProperty;
import org.mindswap.owl.OWLOntology;
import org.mindswap.owls.process.Input;
import org.mindswap.owls.process.InputList;
import org.mindswap.owls.process.Process;
import org.mindswap.query.ValueMap;

import xfmigration.exporter.util.AtomicProcessExecutionType;
import xfmigration.exporter.util.OWLUtils;
import xfmigration.migration.exception.OWLSExecutionException;

/**
 * A class for {@link Process} execution. Accordingly to a process type, which
 * depends on {@link Input} types and number, it executes the process and
 * collects results storing it in an implementation of
 * {@link IProcessingMonitor}.
 */
public class ProcessWrapper {

	/**
	 * A {@link Process} reference.
	 */
	private Process process;

	/**
	 * A reference to data model.
	 */
	private OWLOntology dataModel;

	/**
	 * A reference to process runner.
	 */
	private IServiceRunner runner;

	/**
	 * A flag used to mark that the certain {@link Input} is of {@link OWLClass}
	 * type.
	 */
	public static final boolean OWLCLASS_INPUT_TYPE = true;

	/**
	 * A flag to mark that a certain {@link Input} is of
	 * {@link OWLObjectProperty} type.
	 */
	public static final boolean OWL_OBJ_PROPERTY_INPUT_TYPE = false;

	/**
	 * A constructor.
	 * 
	 * @param p
	 *            A reference to {@link Process}.
	 * @param model
	 *            A reference to data model.
	 */
	public ProcessWrapper(Process p, OWLOntology model) {
		process = p;
		dataModel = model;
		runner = new ServiceRunnerImpl(process);
	}

	/**
	 * A method to determine a process type accordingly to flags defined in
	 * {@link AtomicProcessExecutionType} class.
	 * 
	 * @return A type of a process.
	 */
	private int determineProcessType() {
		int type = 0;
		InputList inputList = process.getInputs();
		InputList classTypeInputList = getInputOfType(inputList,
				OWLCLASS_INPUT_TYPE);
		InputList propertyTypeInputList = getInputOfType(inputList,
				OWL_OBJ_PROPERTY_INPUT_TYPE);
		int inputsCount = process.getInputs().size();

		if (inputsCount == 1 && classTypeInputList.size() == 1) {
			type = AtomicProcessExecutionType.SINGLE_INPUT_CLASS_TYPE;
		} else if (inputsCount == 2 && propertyTypeInputList.size() == 1) {
			type = AtomicProcessExecutionType.SINGLE_PROPERTY_MULTI_VALUE;
		} else if (inputsCount > 2 && propertyTypeInputList.size() >= 2) {
			type = AtomicProcessExecutionType.MULTI_PROPERTY;
		} else {
			type = AtomicProcessExecutionType.NOT_SUPPORTED;
		}
	
		
		return type;
	}
	
	/**
	 * A method to execute a given {@link Process} accordingly to its type
	 * defined by determineProcessType() method.
	 * 
	 * @param monitor
	 *            A reference to {@link IProcessingMonitor} implementation.
	 * @throws OWLSExecutionException thrown if owls service execution errors occur.             
	 */
	@SuppressWarnings("unchecked")
	public void executeProcess(IProcessingMonitor monitor) throws OWLSExecutionException {
		// List<String> results = new ArrayList<String>();
		int type = determineProcessType();

		monitor.addMessage("[Exporter] Executing process "
				+ process.getLocalName() + " ..");

		if (type == AtomicProcessExecutionType.SINGLE_INPUT_CLASS_TYPE) {
			Input input = (Input) process.getInputs().get(0);
			URI typeUri = input.getParamType().getURI();
			OWLIndividualList owlInstances = OWLUtils.getOWLClassInstances(
					dataModel, typeUri, false);
			for (Iterator itr = owlInstances.iterator(); itr.hasNext();) {
				OWLIndividual individual = (OWLIndividual) itr.next();
				ValueMap inputMap = new ValueMap();
				inputMap.setValue(input, individual);
				//System.out.println(individual.toRDF());
				String result = runner.executeService(inputMap);
				monitor.addMessage("  SWS response: " + result);
				// results.add(result);
			}
			runner.dispose();
		} else if (type == AtomicProcessExecutionType.SINGLE_PROPERTY_MULTI_VALUE) {
			Input objPropInput = (Input) getInputOfType(process.getInputs(),
					OWL_OBJ_PROPERTY_INPUT_TYPE).get(0);
			Input domainClassInput = (Input) getInputOfType(
					process.getInputs(), OWLCLASS_INPUT_TYPE).get(0);
			OWLIndividualList individualsList = OWLUtils.getOWLClassInstances(
					dataModel, domainClassInput.getParamType().getURI(), false);

			for (Iterator indvItr = individualsList.iterator(); indvItr
					.hasNext();) {
				OWLIndividual indv = (OWLIndividual) indvItr.next();
				if (objPropInput != null) {
					OWLIndividualList objPropertyValues = getObjectPropertyValues(
							objPropInput.getParamType().getURI(), indv);
					ValueMap inputsMap = new ValueMap();
					inputsMap.setValue(domainClassInput, indv);
					for (Iterator objPropItr = objPropertyValues.iterator(); objPropItr
							.hasNext();) {
						OWLIndividual value = (OWLIndividual) objPropItr.next();
//						System.out.println(value.toRDF());
//						System.out.println(value.getType().getLabel());
						inputsMap.setValue(objPropInput, value);
						String result = runner.executeService(inputsMap);
						monitor.addMessage("\tResult: " + result);
						// results.add(result);
					}
				}
			}
			runner.dispose();
		} else if (type == AtomicProcessExecutionType.MULTI_PROPERTY) {
//			System.out.println("creating multiproperty single value");
			InputList classTypeInputList = getInputOfType(process.getInputs(),
					OWLCLASS_INPUT_TYPE);
			Input classTypeInput = (Input) classTypeInputList.get(0);
			InputList propertyTypeInputList = getInputOfType(process
					.getInputs(), OWL_OBJ_PROPERTY_INPUT_TYPE);

			OWLIndividualList individualsList = OWLUtils.getOWLClassInstances(
					dataModel, classTypeInput.getParamType().getURI(), false);
			// for each OWLClass Type individual
			for (Iterator indvItr = individualsList.iterator(); indvItr
					.hasNext();) {
				OWLIndividual indv = (OWLIndividual) indvItr.next();
				ValueMap inputMap = new ValueMap();
				inputMap.setValue(classTypeInput, indv);

				List singleValuePropTypeInputs = getPropertyTypeInputsByValuesNumber(
						propertyTypeInputList, indv, true);
				if (singleValuePropTypeInputs.size() == propertyTypeInputList
						.size()) {
					// if all property type inputs have single value.
					for (Iterator propInputItr = propertyTypeInputList
							.iterator(); propInputItr.hasNext();) {
						Input propInput = (Input) propInputItr.next();
						OWLIndividualList propValuesList = getObjectPropertyValues(
								propInput.getParamType().getURI(), indv);
						// OWLObjectProperty objProperty =
						// dataModel.getObjectProperty(propertyTypeUri);
						OWLIndividual propValue = (OWLIndividual) propValuesList
								.get(0);  
						inputMap.setValue(propInput, propValue);
					}

					String result = runner.executeService(inputMap);
					monitor.addMessage("\tResult: " + result);
					// results.add(result);

				} else if (singleValuePropTypeInputs.size() < propertyTypeInputList
						.size()) {
					// if one property type input has several values and other
					// property type inputs have single values
					// first add single valued property type inputs
					for (Iterator inputItr = singleValuePropTypeInputs
							.iterator(); inputItr.hasNext();) {
						Input singleValueInput = (Input) inputItr;
						OWLIndividualList valuesList = getObjectPropertyValues(
								singleValueInput.getParamType().getURI(), indv);
						OWLIndividual propValue = (OWLIndividual) valuesList
								.get(0);
						inputMap.setValue(singleValueInput, propValue);
					}
					// iterate over multivalue property type input
					List multiValuePropertyInputs = getPropertyTypeInputsByValuesNumber(
							propertyTypeInputList, indv, false);
					if (!multiValuePropertyInputs.isEmpty()) {
						Input propInput = (Input) multiValuePropertyInputs
								.get(0);
						OWLIndividualList propValues = getObjectPropertyValues(
								propInput.getParamType().getURI(), indv);
						for (Iterator valueItr = propValues.iterator(); valueItr
								.hasNext();) {
							OWLIndividual value = (OWLIndividual) valueItr
									.next();
							inputMap.setValue(propInput, value);
							String result = runner.executeService(inputMap);
							monitor.addMessage("\tResult: " + result);
							// results.add(result);
						}
					}
				}
			}
		} else {
			/*monitor.addMessage("[Exporter] Process " + process.getLocalName()
					+ " not supported for execution.");*/
			throw new OWLSExecutionException("Process " + process.getLocalName()
					+ " not supported for execution.");
		}
		runner.dispose();
	}

	/**
	 * A method to extract object property values of a given type and for a
	 * given individual.
	 * 
	 * @param propertyTypeURI
	 *            URI of type of object property to be extracted.
	 * @param individual
	 *            A reference to an individual.
	 * @return A {@link OWLIndividualList} of object property values.
	 */
	private OWLIndividualList getObjectPropertyValues(URI propertyTypeURI,
			OWLIndividual individual) {
		OWLObjectProperty objectProperty = dataModel
				.getObjectProperty(propertyTypeURI);
		OWLIndividualList objPropValuesList = dataModel.getProperties(
				individual, objectProperty);
		return objPropValuesList;
	}

	/**
	 * A method to extract Inputs of object property type, with single values or
	 * multiple values.
	 * 
	 * @param list
	 *            A list of {@link Input} values.
	 * @param selectedIndividual
	 *            A reference to an individual.
	 * @param singleValuedProperty
	 *            a flag to mark if single value or multiple value inputs should
	 *            be returned.
	 * @return A list of {@link Input} references.
	 */
	@SuppressWarnings("unchecked")
	private InputList getPropertyTypeInputsByValuesNumber(InputList list,
			OWLIndividual selectedIndividual, boolean singleValuedProperty) {

		InputList resultList = new InputListImpl();
		for (Iterator<Input> itr = list.iterator(); itr.hasNext();) {
			Input tmp = itr.next();
			URI propertyTypeUri = tmp.getParamType().getURI();
			OWLObjectProperty property = dataModel
					.getObjectProperty(propertyTypeUri);
			OWLIndividualList propertyValues = dataModel.getProperties(
					selectedIndividual, property);
			if (singleValuedProperty && propertyValues.size() == 1) {
				resultList.add(tmp);
			} else if (!singleValuedProperty && propertyValues.size() > 1) {
				resultList.add(tmp);
			}
		}
		return resultList;
	}

	/**
	 * A method to return a list of inputs of type OWLClass or
	 * OWLObjectProperty.
	 * 
	 * @param list
	 *            A list of {@link Input} objects.
	 * @param inputIsOWLCLass
	 *            true if OWLClass, false if OWLObjectProperty
	 * @return A list of {@link Input} objects.
	 */
	@SuppressWarnings("unchecked")
	private InputList getInputOfType(InputList list, boolean inputIsOWLCLass) {
		InputList inputList = new InputListImpl();
		for (Iterator<Input> itr = list.iterator(); itr.hasNext();) {
			Input tmp = itr.next();
			OWLEntity paramEntity = dataModel.getKB().getEntity(
					tmp.getParamType().getURI());
			if (inputIsOWLCLass && paramEntity instanceof OWLClass) {
				inputList.add(tmp);
			} else if (!inputIsOWLCLass
					&& paramEntity instanceof OWLObjectProperty) {
				inputList.add(tmp);
			}
		}
		return inputList;
	}
}
