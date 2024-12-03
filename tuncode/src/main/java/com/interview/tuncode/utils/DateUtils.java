package com.interview.tuncode.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DateUtils {

    public enum ENUM implements BaseEnum<String> {
        DATE_FORMAT("dd-MM-yyyy");

        private String value;

        ENUM(String val) {
            value = val;
        }

        public String getValue() {
            return value;
        }

        public String getLabel() {
            return name();
        }
    }
}
