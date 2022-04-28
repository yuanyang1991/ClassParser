package com.yuanyang.attribute;

public class ConstantValueAttribute<T> extends Attribute {

    T data;

    public ConstantValueAttribute(T data) {
        super(2);
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
