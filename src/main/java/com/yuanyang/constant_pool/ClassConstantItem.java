package com.yuanyang.constant_pool;

public class ClassConstantItem extends ConstantItem {

    private int index;

    public ClassConstantItem(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
