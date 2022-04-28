package com.yuanyang;

import com.yuanyang.attribute.Attribute;
import com.yuanyang.attribute.AttributeParser;
import com.yuanyang.constant_pool.ConstantPool;

import java.io.IOException;
import java.io.InputStream;

public class FieldParser implements Parser<FieldItem> {


    @Override
    public FieldItem parse(InputStream inputStream, ConstantPool constantPool) throws IOException {
        int accessFlags = StreamUtils.readU2(inputStream);
        int nameIndex = StreamUtils.readU2(inputStream);
        String name = ValueUtils.getUTF8(nameIndex, constantPool);
        int descriptorIndex = StreamUtils.readU2(inputStream);
        String descriptor = ValueUtils.getUTF8(descriptorIndex, constantPool);
        int attributesCount = StreamUtils.readU2(inputStream);
        Attribute[] attributes = new Attribute[attributesCount];
        AttributeParser attributeParser = new AttributeParser();
        for (int i = 0; i < attributesCount; i++) {
            attributes[i] = attributeParser.parse(inputStream, constantPool);
        }
        return new FieldItem(accessFlags, name, descriptor, attributes);
    }
}
