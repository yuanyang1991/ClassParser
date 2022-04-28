package com.yuanyang.constant_pool;

public class DoubleConstantItem extends ConstantItem {

    private double value;

    public DoubleConstantItem(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
