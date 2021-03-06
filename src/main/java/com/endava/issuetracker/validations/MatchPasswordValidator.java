package com.endava.issuetracker.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class MatchPasswordValidator implements ConstraintValidator<MatchPassword, Object> {

    private String passwordFieldName;

    private String passwordVerificationFieldName;

    public void initialize(MatchPassword constraintAnnotation) {
        this.passwordFieldName = constraintAnnotation.passwordFieldName();
        this.passwordVerificationFieldName = constraintAnnotation.passwordVerificationFieldName();
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        try {
            String password = (String) ValidatorUtil.getFieldValue(value, passwordFieldName);
            String passwordVerification = (String) ValidatorUtil.getFieldValue(value, passwordVerificationFieldName);

            if (passwordsAreNotEqual(password, passwordVerification)) {
                ValidatorUtil.addValidationError(passwordFieldName, context);
                ValidatorUtil.addValidationError(passwordVerificationFieldName, context);

                return false;
            }
        }
        catch (Exception ex) {
            throw new RuntimeException("Exception occurred during validation", ex);
        }

        return true;
    }

    private boolean passwordsAreNotEqual(String password, String passwordVerification) {
        return !(password == null ? passwordVerification == null : password.equals(passwordVerification));
    }
}
