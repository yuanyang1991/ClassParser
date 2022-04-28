package com.yuanyang.constant_pool;

public class StringConstantItem extends ConstantItem {

    private int stringIndex;

    public StringConstantItem(int stringIndex) {
        this.stringIndex = stringIndex;
    }

    public int getStringIndex() {
        return stringIndex;
    }
}
