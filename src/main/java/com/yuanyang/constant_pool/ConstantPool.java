package com.yuanyang.constant_pool;

public class ConstantPool {

    private final ConstantItem[] constantItems;

    public ConstantPool(ConstantItem[] constantItems) {
        this.constantItems = constantItems;
    }

    public ConstantItem get(int index) {
        return constantItems[index - 1];
    }
}
