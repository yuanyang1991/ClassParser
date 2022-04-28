package com.yuanyang.attribute;

public class CodeAttribute extends Attribute {

    private int maxStack;
    private int maxLocals;
    private int[] instructions;

    public CodeAttribute(int length, int maxStack, int maxLocals, int[] instructions) {
        super(length);
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;
        this.instructions = instructions;
    }
}
