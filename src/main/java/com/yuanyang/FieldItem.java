package com.yuanyang;

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
    private int[] attributes; // !有问题

    public FieldItem(int accessFlags, String name, String descriptor, int[] attributes) {
        this.accessFlags = accessFlags;
        this.name = name;
        this.descriptor = descriptor;
        this.attributes = attributes;
    }
}
