package com.yuanyang.constant_pool;

public class InterfaceMethodRefConstantItem extends ConstantItem {

    int classIndex;
    int nameAndTypeIndex;

    public InterfaceMethodRefConstantItem(int classIndex, int nameAndTypeIndex) {
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }
}
