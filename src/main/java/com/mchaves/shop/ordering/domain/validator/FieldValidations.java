package com.mchaves.shop.ordering.domain.validator;

import java.util.Objects;

import org.apache.commons.validator.routines.EmailValidator;

public class FieldValidations {

    private FieldValidations() {
    }

    public static void requiresValidEmail(String email) {
        requiresValidEmail(email, null);
    }

    public static void requiresValidEmail(String email, String errorMesssage) {
        Objects.requireNonNull(email, errorMesssage);
        if (email.isBlank()) {
            throw new IllegalArgumentException(errorMesssage);
        }
        if (!EmailValidator.getInstance().isValid(email)) {
            throw new IllegalArgumentException();
        }

    }

}
