package xfmigration.web.util;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * A forge service form validator class.
 */
public class ForgeServiceValidator implements Validator {

    /**
     * A method to determine if validator supports a given class.
     * 
     * @param aClass
     *            A reference to {@link Class} instance.
     *            @return true if a class is supported by a validator, false otherwise.
     */
    @SuppressWarnings("unchecked")
    public boolean supports(Class aClass) {
        return aClass.equals(ForgeServiceForm.class);
    }

    /**
     * An implementation of validate method.
     * 
     * @param command
     *            a {@link ForgeServiceForm} reference.
     * @param errors
     *            a collection of errors.
     */
    public void validate(Object command, Errors errors) {
        ForgeServiceForm formBean = (ForgeServiceForm) command;

        if (formBean.getServiceName().equals("")) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "serviceName",
                    "error.field-required", "Please provide service name");
        }
        if (formBean.getServicePath().equals("")) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "servicePath",
                    "error.field-required", "Please provide service owl-s name");
        }
    }

}
