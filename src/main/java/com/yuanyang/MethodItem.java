package com.yuanyang;

import com.yuanyang.attribute.Attribute;

public class MethodItem {

    private int accessFlags;

    private String name;

    private String descriptor;

    private Attribute[] attributes;

    public MethodItem(int accessFlags, String name, String descriptor, Attribute[] attributes) {
        this.accessFlags = accessFlags;
        this.name = name;
        this.descriptor = descriptor;
        this.attributes = attributes;
    }
}
