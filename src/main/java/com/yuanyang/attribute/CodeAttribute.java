package com.yuanyang.attribute;

public class CodeAttribute extends Attribute {

    private int maxStack;
    private int maxLocals;
    /**
     * 特别注意：instructions里面不仅包含指令，还包含一些立即数
     * 部分指令自带参数，部分指令的参数在指令后面的一个字节，部分指令从
     * 常量池中取
     */
    private int[] instructions;
    private Attribute[] attributes;

    public CodeAttribute(int length, int maxStack, int maxLocals, int[] instructions, Attribute[] attributes) {
        super(length);
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;
        this.instructions = instructions;
        this.attributes = attributes;
    }

    public int getMaxStack() {
        return maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public int[] getInstructions() {
        return instructions;
    }

    public Attribute[] getAttributes() {
        return attributes;
    }
}


