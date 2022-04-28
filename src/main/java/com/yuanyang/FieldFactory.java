package com.yuanyang;

import java.io.IOException;
import java.io.InputStream;

public class FieldFactory {

    public static FieldItem createField(InputStream inputStream, ConstantItem[] constantPool) throws IOException {
        int accessFlags = StreamUtils.readU2(inputStream);
        int nameIndex = StreamUtils.readU2(inputStream);
        String name = ValueUtils.getUTF8(nameIndex - 1, constantPool);
        int descriptorIndex = StreamUtils.readU2(inputStream);
        String descriptor = ValueUtils.getUTF8(descriptorIndex - 1, constantPool);
        int attributesCount = StreamUtils.readU2(inputStream);
        int[] attributes = new int[attributesCount];
        for (int i = 0; i < attributesCount; i++) {
            attributes[i] = StreamUtils.readU2(inputStream);
        }
        return new FieldItem(accessFlags, name, descriptor, attributes);
    }
}
