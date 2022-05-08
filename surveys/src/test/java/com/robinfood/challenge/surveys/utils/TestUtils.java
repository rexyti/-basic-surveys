package com.robinfood.challenge.surveys.utils;

import java.nio.charset.Charset;
import java.util.Random;

public class TestUtils {
    public static String generateString(int size){
        byte[] array = new byte[size];
        new Random().nextBytes(array);
        return new String(array, Charset.forName("UTF-8"));
    }
}
