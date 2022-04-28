package com.yuanyang;

public class NameAndTypeItem extends ConstantItem {

    private int nameIndex;
    private int descriptorIndex;

    public NameAndTypeItem(int nameIndex, int descriptorIndex) {
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
    }
}
