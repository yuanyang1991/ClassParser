package com.yuanyang.attribute;

public class OtherAttribute extends Attribute{

    private byte[] data;

    public OtherAttribute(int length, byte[] data) {
        super(length);
        this.data = data;
    }
}
