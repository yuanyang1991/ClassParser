package com.yuanyang;

import com.yuanyang.attribute.Attribute;

public class FieldItem {

    /**
     * 字段访问符
     */
    private int accessFlags;

    /**
     * 字段名称
     */
    private String name;

    /**
     * 字段描述（类型）
     */
    private String descriptor;

    /**
     * 其他修饰符
     */
    private Attribute[] attributes;

    public FieldItem(int accessFlags, String name, String descriptor, Attribute[] attributes) {
        this.accessFlags = accessFlags;
        this.name = name;
        this.descriptor = descriptor;
        this.attributes = attributes;
    }
}
