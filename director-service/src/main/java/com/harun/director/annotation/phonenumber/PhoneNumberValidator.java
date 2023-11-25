package com.harun.director.annotation.phonenumber;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberValidation, String> {

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        if (phoneNumber.isEmpty())
            throw new NullPointerException("Phone number cant not be null!");
        if (!phoneNumber.startsWith("0"))
            throw new IllegalArgumentException("Phone number must start with 0!");
        if (!phoneNumber.matches("^0[0-9]{10}$")){
            throw new IllegalArgumentException("Phone number must be 11 digits!");}
        return true;
    }
}