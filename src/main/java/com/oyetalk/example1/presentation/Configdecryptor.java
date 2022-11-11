package com.oyetalk.example1.presentation;

import org.jasypt.encryption.StringEncryptor;

public class Configdecryptor implements StringEncryptor {
    @Override
    public String encrypt(String s) {
        return s;
    }

    @Override
    public String decrypt(String s) {


        return null;
    }
}
