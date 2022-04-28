package com.yuanyang;

public class FieldRefItem extends ConstantItem {

    private int classIndex;
    private int nameAndTypeIndex;

    public FieldRefItem(int classIndex, int nameAndTypeIndex) {
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }
}
