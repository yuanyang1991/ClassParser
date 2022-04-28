package com.yuanyang;

import com.yuanyang.attribute.Attribute;

import java.io.IOException;
import java.io.InputStream;

public class AttributeFactory {

    public static Attribute parseAttribute(InputStream inputStream, ConstantItem[] constantPool) throws IOException {
        int nameIndex = StreamUtils.readU2(inputStream);
        UTF8Item utf8Item = (UTF8Item) constantPool[nameIndex - 1];
        String name = utf8Item.getValue();
        return null;
    }
}
