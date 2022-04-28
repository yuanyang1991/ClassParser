package com.yuanyang;

public class MethodRefItem extends ConstantItem {

    int classIndex;
    int nameAndTypeIndex;

    public MethodRefItem(int classIndex, int nameAndTypeIndex) {
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }
}
