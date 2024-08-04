package com.interview.tuncode.commonutils;

public abstract class CommonUtils {

    public static final class JasyptEncryptor {
        public static final String ALGORITHM = "PBEWITHHMACSHA512ANDAES_256";
        public static final String PASSWORD = "tuncodespringboot-practices";
        public static final String KEY_OBTENTION_ITERATIONS = "100";
        public static final String POOL_SIZE = "1";
        public static final String PROVIDER_NAME = "SunJCE";
        public static final String SALT_GENERATOR_CLASS_NAME = "org.jasypt.salt.RandomSaltGenerator";
        public static final String IV_GENERATOR_CLASS_NAME = "org.jasypt.iv.RandomIvGenerator";
        public static final String STRING_OUTPUT_TYPE = "base64";
    }

    public static final class EntityUtils {
        public static final String PRIMARY_SCHOOL = "PRIMARY_SCHOOL";
    }

}
