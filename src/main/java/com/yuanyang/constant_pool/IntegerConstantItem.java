package com.yuanyang.constant_pool;

public class IntegerConstantItem extends ConstantItem {

    private int value;

    public IntegerConstantItem(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
