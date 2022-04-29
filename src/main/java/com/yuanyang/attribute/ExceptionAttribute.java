package com.yuanyang.attribute;

public class ExceptionAttribute extends Attribute {

    private String[] exceptions;

    public ExceptionAttribute(int length, String[] exceptions) {
        super(length);
        this.exceptions = exceptions;
    }
}
