package com.yuanyang.constant_pool;

public class MethodRefConstantItem extends ConstantItem {

    int classIndex;
    int nameAndTypeIndex;

    public MethodRefConstantItem(int classIndex, int nameAndTypeIndex) {
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }
}
