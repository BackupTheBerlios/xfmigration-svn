package xfmigration.web.util;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * A validator to handle validation process of {@link RegisterForgeForm} beans.
 */
public class ForgeFormValidator implements Validator {

	/**
	 * Validator supports {@link RegisterForgeForm} class.
	 * 
	 * @param aClass
	 *            a {@link Class} to check if is supported.
	 * @return true if class supported, false otherwise.
	 */
	@SuppressWarnings("unchecked")
	public boolean supports(Class aClass) {
		return aClass.equals(RegisterForgeForm.class);
	}

	/**
	 * An implementation of validate method.
	 * @param command An instance of {@link RegisterForgeForm}.
	 * @param errors A collection of processing errors.
	 */
	public void validate(Object command, Errors errors) {
		RegisterForgeForm formBean = (RegisterForgeForm) command;

		if (formBean.getForgeName().equals("")) {
			errors.rejectValue("forgeName", "error.not-specified", null,
					"Please provide a forge name");
		}

		if (formBean.getForgeDescription().equals("")) {
			errors.rejectValue("forgeDescription", "error.not-specified", null,
					"Please provide a forge description");
		}

		if (formBean.getForgeOntURL().equals("")) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "forgeOntURL",
					"error.field-required", "Please provide forge url");
		}

		if (formBean.getOwlsCreateIndividualsSequencePath().equals("")) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,
					"owlsCreateIndividualsSequencePath",
					"error.field-required",
					"Please provide create individuals owl-s sequence name");
		}

		if (formBean.getOwlsCreateObjPropertiesSequencePath().equals("")) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,
					"owlsCreateObjPropertiesSequencePath",
					"error.field-required",
					"Please provide create properties owl-s sequence name");
		}

	}

}
