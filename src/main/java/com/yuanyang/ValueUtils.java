package com.yuanyang;

import com.yuanyang.constant_pool.ClassConstantItem;
import com.yuanyang.constant_pool.ConstantItem;
import com.yuanyang.constant_pool.ConstantPool;
import com.yuanyang.constant_pool.UTF8ConstantItem;

import java.io.IOException;
import java.io.InputStream;

public class ValueUtils {

    public static String getClass(InputStream inputStream, ConstantPool constantPool) throws IOException {
        int index = StreamUtils.readU2(inputStream);
        ClassConstantItem classInfoItem = (ClassConstantItem) constantPool.get(index);
        UTF8ConstantItem utf8Item = (UTF8ConstantItem) constantPool.get(classInfoItem.getIndex());
        return utf8Item.getValue();
    }

    public static String getUTF8(int index, ConstantPool constantPool) {
        UTF8ConstantItem constantItem = (UTF8ConstantItem) constantPool.get(index);
        return constantItem.getValue();
    }
}
