package xfmigration.web.util;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * A validator of form controller {@link ForgeDetailsFormController}.
 */
public class ForgeUpdateFormValidator implements Validator {

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public boolean supports(Class aClass) {
        return aClass.equals(ForgeDetailsForm.class);
    }

    /**
     * An implementation of validate method for
     * {@link ForgeDetailsFormController} support.
     * 
     * @param command
     *            A {@link ForgeDetailsForm} object reference.
     * @param errors
     *            A collection of errors.d
     */
    public void validate(Object command, Errors errors) {
        ForgeDetailsForm formBean = (ForgeDetailsForm) command;

        if (formBean.getForge().getForgeName().equals("")) {
            errors.rejectValue("forge.forgeName", "error.not-specified", null,
                    "Please provide a forge name");
        }

        if (formBean.getForge().getForgeDescription().equals("")) {
            errors.rejectValue("forge.forgeDescription", "error.not-specified", null,
                    "Please provide a forge description");
        }

        /*if (formBean.getForge().getOntologyUri().equals("")) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "forge.ontologyUri",
                    "error.field-required", "Please provide forge uri");
        }*/

/*        if (formBean.getForge().getOntologyUrl().equals("")) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "forge.ontologyUrl",
                    "error.field-required", "Please provide forge url");
        }
*/
/*        if (formBean.getForge().getHomeDir().equals("")) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "forge.homeDir",
                    "error.field-required", "Please provide forge home dir name");
        }
*/
/*        if (formBean.getForge().getOwlsCreateIndividualsSequencePath().equals("")) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                    "forge.owlsCreateIndividualsSequencePath", "error.field-required",
                    "Please provide create individuals owl-s sequence name");
        }
*/
/*        if (formBean.getForge().getOwlsCreateObjPropertiesSequencePath().equals("")) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                    "forge.owlsCreateObjPropertiesSequencePath", "error.field-required",
                    "Please provide create properties owl-s sequence name");
        }
*/
/*        if (formBean.getForge().getTestdataDir().equals("")) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "forge.testdataDir",
                    "error.field-required", "Please provide forge test dir name");
        }
*/    }

}
