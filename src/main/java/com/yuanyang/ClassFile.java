package com.yuanyang;

import com.yuanyang.constant_pool.ConstantItem;
import com.yuanyang.constant_pool.ConstantPool;

import java.io.IOException;
import java.io.InputStream;

public class ClassFile {

    private int minorVersion;
    private int majorVersion;
    private ConstantPool constantPool;
    private int accessFlags;
    private String clz;
    private String parent;
    private String[] interfaces;
    private FieldItem[] fields;
    private MethodItem[] methods;

    public static ClassFile from(InputStream inputStream) throws IOException {
        int magic = StreamUtils.readU4(inputStream);
        if (magic != 0xCAFEBABE) {
            throw new IllegalArgumentException("this file is not a class file");
        }
        ClassFile classFile = new ClassFile();
        classFile.minorVersion = StreamUtils.readU2(inputStream);
        classFile.majorVersion = StreamUtils.readU2(inputStream);
        ConstantPool constantPool = new ConstantPool(ConstantPoolParser.parse(inputStream));
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
        FieldParser fieldParser = new FieldParser();
        classFile.fields = new FieldItem[fieldCount];
        for (int i = 0; i < fieldCount; i++) {
            classFile.fields[i] = fieldParser.parse(inputStream, constantPool);
        }

        int methodCount = StreamUtils.readU2(inputStream);
        MethodParser methodParser = new MethodParser();
        classFile.methods = new MethodItem[methodCount];
        for (int i = 0; i < methodCount; i++) {
            classFile.methods[i] = methodParser.parse(inputStream, constantPool);
        }

        return classFile;

    }
}
