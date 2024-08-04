package com.interview.tuncode.configurations.jasyptconfig;

import com.interview.tuncode.commonutils.CommonUtils;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        config.setPassword(CommonUtils.JasyptEncryptor.PASSWORD);
        config.setAlgorithm(CommonUtils.JasyptEncryptor.ALGORITHM);
        config.setKeyObtentionIterations(CommonUtils.JasyptEncryptor.KEY_OBTENTION_ITERATIONS);
        config.setPoolSize(CommonUtils.JasyptEncryptor.POOL_SIZE);
        config.setProviderName(CommonUtils.JasyptEncryptor.PRODIVER_NAME);
        config.setSaltGeneratorClassName(CommonUtils.JasyptEncryptor.SALT_GENERATOR_CLASS_NAME);
        config.setIvGeneratorClassName(CommonUtils.JasyptEncryptor.IV_GENERATOR_CLASS_NAME);
        config.setStringOutputType(CommonUtils.JasyptEncryptor.STRING_OUTPUT_TYPE);
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
                System.out.println("2-Exit");
                System.out.println("Your choose: ");
                int menuIndex = scanner.nextInt();

                if(menuIndex == 3){
                    System.out.println("Exit..");
                    break;
                }

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
