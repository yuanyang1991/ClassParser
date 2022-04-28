package com.yuanyang;

public class InterfaceMethodRefItem extends ConstantItem {

    int classIndex;
    int nameAndTypeIndex;

    public InterfaceMethodRefItem(int classIndex, int nameAndTypeIndex) {
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }
}
