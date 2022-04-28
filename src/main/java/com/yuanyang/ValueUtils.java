package com.yuanyang;

import java.io.IOException;
import java.io.InputStream;

public class ValueUtils {

    public static String getClass(InputStream inputStream, ConstantItem[] constantPool) throws IOException {
        int index = StreamUtils.readU2(inputStream);
        ClassInfoItem classInfoItem = (ClassInfoItem) constantPool[index - 1];
        UTF8Item utf8Item = (UTF8Item) constantPool[classInfoItem.getIndex() - 1];
        return utf8Item.getValue();
    }

    public static String getUTF8(int index, ConstantItem[] constantPool) {
        UTF8Item constantItem = (UTF8Item) constantPool[index];
        return constantItem.getValue();
    }
}
