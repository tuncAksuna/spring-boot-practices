package com.interview.tuncode.configurations.customexception.jpa;

import com.interview.tuncode.configurations.customexception.jasyptconfig.JasyptSec;

import javax.persistence.AttributeConverter;

public class PasswordConverter implements AttributeConverter<String,String> {
    @Override
    public String convertToDatabaseColumn(String attribute) {
        return JasyptSec.defaultEnc.encrypt(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return JasyptSec.defaultEnc.decrypt(dbData);
    }
}
