package com.yuanyang.constant_pool;

public class NameAndTypeConstantItem extends ConstantItem {

    private int nameIndex;
    private int descriptorIndex;

    public NameAndTypeConstantItem(int nameIndex, int descriptorIndex) {
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
    }
}
