package com.yuanyang;

import com.yuanyang.constant_pool.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ConstantPoolParser {


    public static ConstantItem[] parse(InputStream inputStream) throws IOException {
        /**
         * 需要特别注意，class文件中的索引都是从1开始的，但是数组是从0开始的，所以在通过索引找值时需要将索引-1
         */
        int constantPoolSize = StreamUtils.readU2(inputStream) - 1;
        ConstantItem[] constantPool = new ConstantItem[constantPoolSize];
        int index = 0;
        while (index < constantPoolSize) {
            ConstantItem item = null;
            int constantType = StreamUtils.readU1(inputStream);
            switch (constantType) {
                case 1:
                    item = parseUTF8(inputStream);
                    break;
                case 3:
                    item = parseInteger(inputStream);
                    break;
                case 4:
                    item = parseFloat(inputStream);
                    break;
                case 5:
                    item = parseLong(inputStream);
                    break;
                case 6:
                    item = parseDouble(inputStream);
                    break;
                case 7:
                    item = parseClassInfo(inputStream);
                    break;
                case 8:
                    item = parseString(inputStream);
                    break;
                case 9:
                    item = parseField(inputStream);
                    break;
                case 10:
                    item = parseMethod(inputStream);
                    break;
                case 11:
                    item = parseInterface(inputStream);
                    break;
                case 12:
                    item = parseNameAndType(inputStream);
            }
            constantPool[index] = item;
            index++;
        }
        return constantPool;
    }

    private static ConstantItem parseField(InputStream inputStream) throws IOException {
        int classIndex = StreamUtils.readU2(inputStream);
        int nameAndTypeIndex = StreamUtils.readU2(inputStream);
        return new FieldRefConstantItem(classIndex, nameAndTypeIndex);
    }

    private static ConstantItem parseInterface(InputStream inputStream) throws IOException {
        int classIndex = StreamUtils.readU2(inputStream);
        int nameAndTypeIndex = StreamUtils.readU2(inputStream);
        return new InterfaceMethodRefConstantItem(classIndex, nameAndTypeIndex);
    }

    private static ConstantItem parseNameAndType(InputStream inputStream) throws IOException {
        int nameIndex = StreamUtils.readU2(inputStream);
        int descriptorIndex = StreamUtils.readU2(inputStream);
        return new NameAndTypeConstantItem(nameIndex, descriptorIndex);
    }

    private static ConstantItem parseString(InputStream inputStream) throws IOException {
        int stringIndex = StreamUtils.readU2(inputStream);
        return new StringConstantItem(stringIndex);
    }

    private static ConstantItem parseClassInfo(InputStream inputStream) throws IOException {
        int index = StreamUtils.readU2(inputStream);
        return new ClassConstantItem(index);
    }

    private static ConstantItem parseDouble(InputStream inputStream) throws IOException {
        StreamUtils.readU8(inputStream);
        return null;
    }

    private static ConstantItem parseLong(InputStream inputStream) throws IOException {
        StreamUtils.readU8(inputStream);
        return null;
    }

    private static ConstantItem parseFloat(InputStream inputStream) throws IOException {
        StreamUtils.readU4(inputStream);
        return null;
    }

    private static ConstantItem parseMethod(InputStream inputStream) throws IOException {
        int classIndex = StreamUtils.readU2(inputStream);
        int nameAndTypeIndex = StreamUtils.readU2(inputStream);
        return new MethodRefConstantItem(classIndex, nameAndTypeIndex);
    }

    private static ConstantItem parseInteger(InputStream inputStream) throws IOException {
        int value = StreamUtils.readU4(inputStream);
        return new IntegerConstantItem(value);
    }

    private static ConstantItem parseUTF8(InputStream inputStream) throws IOException {
        int length = StreamUtils.readU2(inputStream);
        byte[] bytes = new byte[length];
        inputStream.read(bytes, 0, length);
        return new UTF8ConstantItem(new String(bytes, StandardCharsets.UTF_8));
    }
}
