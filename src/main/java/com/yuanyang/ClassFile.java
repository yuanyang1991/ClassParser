package com.yuanyang;

import java.io.IOException;
import java.io.InputStream;

public class ClassFile {

    private int minorVersion;
    private int majorVersion;
    private ConstantItem[] constantPool;
    private int accessFlags;


    public static ClassFile from(InputStream inputStream) throws IOException {
        int magic = StreamUtils.readU4(inputStream);
        if (magic != 0xCAFEBABE) {
            throw new IllegalArgumentException("this file is not a class file");
        }
        ClassFile classFile = new ClassFile();
        classFile.minorVersion = StreamUtils.readU2(inputStream);
        classFile.majorVersion = StreamUtils.readU2(inputStream);
        int constantPoolSize = StreamUtils.readU2(inputStream);
        ConstantItem[] constantPool = new ConstantItem[constantPoolSize];
        int index = 0;
        while (index < constantPoolSize) {
            constantPool[index] = ConstantItemFactory.create(StreamUtils.readU1(inputStream), inputStream);
            index++;
        }
        classFile.constantPool = constantPool;
        classFile.accessFlags = StreamUtils.readU2(inputStream);
        return null;
    }
}
