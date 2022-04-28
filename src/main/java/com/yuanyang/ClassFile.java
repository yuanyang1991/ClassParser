package com.yuanyang;

import javax.swing.plaf.ViewportUI;
import java.io.IOException;
import java.io.InputStream;

public class ClassFile {

    private int minorVersion;
    private int majorVersion;
    private ConstantItem[] constantPool;
    private int accessFlags;
    private String clz;
    private String parent;
    private String[] interfaces;
    private FieldItem[] fields;

    public static ClassFile from(InputStream inputStream) throws IOException {
        int magic = StreamUtils.readU4(inputStream);
        if (magic != 0xCAFEBABE) {
            throw new IllegalArgumentException("this file is not a class file");
        }
        ClassFile classFile = new ClassFile();
        classFile.minorVersion = StreamUtils.readU2(inputStream);
        classFile.majorVersion = StreamUtils.readU2(inputStream);
        /*
        需要特别注意，class文件中的索引都是从1开始的，但是数组是从0开始的，所以在通过索引找值时需要将索引-1
         */
        int constantPoolSize = StreamUtils.readU2(inputStream) - 1;
        ConstantItem[] constantPool = new ConstantItem[constantPoolSize];
        int index = 0;
        while (index < constantPoolSize) {
            constantPool[index] = ConstantItemFactory.create(StreamUtils.readU1(inputStream), inputStream);
            index++;
        }
        classFile.constantPool = constantPool;
        // 类的访问符
        classFile.accessFlags = StreamUtils.readU2(inputStream);
        // 获取类信息
        classFile.clz = ValueUtils.getClass(inputStream, constantPool);
        // 获取父类信息
        classFile.parent = ValueUtils.getClass(inputStream, constantPool);
        // 获取接口信息
        int interfaceCount = StreamUtils.readU2(inputStream);
        classFile.interfaces = new String[interfaceCount];
        for (int i = 0; i < interfaceCount; i++) {
            classFile.interfaces[i] = ValueUtils.getClass(inputStream, constantPool);
        }
        // 获取字段
        int fieldCount = StreamUtils.readU2(inputStream);
        classFile.fields = new FieldItem[fieldCount];
        for (int i = 0; i < fieldCount; i++) {
            classFile.fields[i] = FieldFactory.createField(inputStream, constantPool);
        }

        return null;

    }
}
