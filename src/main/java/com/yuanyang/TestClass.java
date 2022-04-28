package com.yuanyang;

import java.io.Serializable;

public class TestClass implements Serializable {

    private static final int integer = 123;
    private static final int integer1 = 2000;
    private boolean aBoolean = true;
    private byte aByte = 11;
    private char aChar = 'a';

    private int getValue() {
        int a = 1;
        int b = 2;
        return a + b;
    }


//    private long longV = 2L;
//    private short aShort = 3;
//    private float aFloat = 1.1f;
//    private double aDouble = 1.2d;
}
