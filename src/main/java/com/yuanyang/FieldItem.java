package com.yuanyang;

public class FieldItem extends ConstantItem {

    private int classIndex;
    private int nameAndTypeIndex;

    public FieldItem(int classIndex, int nameAndTypeIndex) {
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }
}
