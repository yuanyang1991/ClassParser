package com.yuanyang.constant_pool;

public class UTF8ConstantItem extends ConstantItem {

    private final String value;

    public UTF8ConstantItem(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
