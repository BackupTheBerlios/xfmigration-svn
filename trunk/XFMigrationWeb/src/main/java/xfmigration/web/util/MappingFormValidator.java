package xfmigration.web.util;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * A validator to support {@link RegisterMappingController}.
 */
public class MappingFormValidator implements Validator {

    /**
     * Supports {@link RegisterMappingForm} class as a backing bean.
     * @param aClass a class
     * @return true if class supported, false otherwise.
     */
    @SuppressWarnings("unchecked")
    public boolean supports(Class aClass) {
        return aClass.equals(RegisterMappingForm.class);
    }

    /**
     * An implementation of validate method.
     * 
     * @param command
     *            form backing bean, instance of {@link RegisterMappingForm}.
     * @param errors
     *            A collection of processing errors.
     */
    public void validate(Object command, Errors errors) {
        RegisterMappingForm formBean = (RegisterMappingForm) command;
        if (formBean.getSrcForgeName().equals("0")) {
            errors.rejectValue("srcForgeName", "error.not-specified", null,
                    "Please select a forge name");
        }
        if (formBean.getDestForgeName().equals("0")) {
            errors.rejectValue("destForgeName", "error.not-specified", null,
                    "Please select a forge name");
        }
        if (formBean.getMappingName().equals("")) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mappingName", "error.field-required",
                    "Please provide mapping name");
        }
    }
}
