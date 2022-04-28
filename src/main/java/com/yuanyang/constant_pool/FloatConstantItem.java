package com.yuanyang.constant_pool;

public class FloatConstantItem extends ConstantItem {

    private float value;

    public FloatConstantItem(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }
}
