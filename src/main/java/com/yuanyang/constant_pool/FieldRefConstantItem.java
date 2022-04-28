package com.yuanyang.constant_pool;

public class FieldRefConstantItem extends ConstantItem {

    private int classIndex;
    private int nameAndTypeIndex;

    public FieldRefConstantItem(int classIndex, int nameAndTypeIndex) {
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }
}
