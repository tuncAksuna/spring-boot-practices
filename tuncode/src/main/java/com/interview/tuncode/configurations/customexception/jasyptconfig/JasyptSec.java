package com.interview.tuncode.configurations.customexception.jasyptconfig;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Scanner;

@Configuration
public class JasyptSec {
    public static StringEncryptor defaultEnc = jasyptStringEncryptorBuilder();

    @Bean
    public StringEncryptor jasyptStringEncryptor() {
        return defaultEnc;
    }

    public static StringEncryptor jasyptStringEncryptorBuilder() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("tuncodespringboot-practices");
        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        return encryptor;
    }

    public static void main(String[] args) {
        StringEncryptor stringEncryptor = jasyptStringEncryptorBuilder();
        try (Scanner scanner = new Scanner(System.in)) {
            root:
            while (true) {
                System.out.println("1-Encrypt");
                System.out.println("2-Decrypt");
                System.out.println("Your choose");
                int menuIndex = scanner.nextInt();
                System.out.println("Input : ");
                String value = scanner.next();
                switch (menuIndex) {
                    case 1:
                        System.out.println(stringEncryptor.encrypt(value));
                        break;
                    case 2:
                        System.out.println(stringEncryptor.decrypt(value));
                        break;
                    default:
                        break root;
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
