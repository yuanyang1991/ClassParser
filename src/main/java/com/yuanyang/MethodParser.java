package com.yuanyang;

import com.yuanyang.attribute.Attribute;
import com.yuanyang.attribute.AttributeParser;
import com.yuanyang.constant_pool.ConstantPool;

import java.io.IOException;
import java.io.InputStream;

public class MethodParser implements Parser<MethodItem> {

    @Override
    public MethodItem parse(InputStream inputStream, ConstantPool constantPool) throws IOException {
        int accessFlags = StreamUtils.readU2(inputStream);
        String methodName = ValueUtils.getUTF8(StreamUtils.readU2(inputStream), constantPool);
        String descriptor = ValueUtils.getUTF8(StreamUtils.readU2(inputStream), constantPool);
        int attributeCount = StreamUtils.readU2(inputStream);
        AttributeParser attributeParser = new AttributeParser();
        Attribute[] attributes = new Attribute[attributeCount];
        for (int i = 0; i < attributeCount; i++) {
            attributes[i] = attributeParser.parse(inputStream, constantPool);
        }
        return new MethodItem(accessFlags, methodName, descriptor, attributes);
    }
}
