package com.yuanyang;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ConstantItemFactory {


    public static ConstantItem create(int constantType, InputStream inputStream) throws IOException {
        switch (constantType) {
            case 1:
                return parseUTF8(inputStream);
            case 3:
                return parseInteger(inputStream);
            case 4:
                return parseFloat(inputStream);
            case 5:
                return parseLong(inputStream);
            case 6:
                return parseDouble(inputStream);
            case 7:
                return parseClassInfo(inputStream);
            case 8:
                return parseString(inputStream);
            case 9:
                return parseField(inputStream);
            case 10:
                return parseMethod(inputStream);
            case 11:
                return parseInterface(inputStream);
            case 12:
                return parseNameAndType(inputStream);
        }
        return null;
    }

    private static ConstantItem parseField(InputStream inputStream) throws IOException {
        int classIndex = StreamUtils.readU2(inputStream);
        int nameAndTypeIndex = StreamUtils.readU2(inputStream);
        return new FieldItem(classIndex, nameAndTypeIndex);
    }

    private static ConstantItem parseInterface(InputStream inputStream) throws IOException {
        int classIndex = StreamUtils.readU2(inputStream);
        int nameAndTypeIndex = StreamUtils.readU2(inputStream);
        return new InterfaceMethodRefItem(classIndex, nameAndTypeIndex);
    }

    private static ConstantItem parseNameAndType(InputStream inputStream) throws IOException {
        int nameIndex = StreamUtils.readU2(inputStream);
        int descriptorIndex = StreamUtils.readU2(inputStream);
        return new NameAndTypeItem(nameIndex, descriptorIndex);
    }

    private static ConstantItem parseString(InputStream inputStream) throws IOException {
        int stringIndex = StreamUtils.readU2(inputStream);
        return new StringItem(stringIndex);
    }

    private static ConstantItem parseClassInfo(InputStream inputStream) throws IOException {
        int index = StreamUtils.readU2(inputStream);
        return new ClassInfoItem(index);
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
        return new MethodRefItem(classIndex, nameAndTypeIndex);
    }

    private static ConstantItem parseInteger(InputStream inputStream) throws IOException {
        int value = StreamUtils.readU4(inputStream);
        return new IntegerItem(value);
    }

    private static ConstantItem parseUTF8(InputStream inputStream) throws IOException {
        int length = StreamUtils.readU2(inputStream);
        byte[] bytes = new byte[length];
        StreamUtils.readBytes(bytes, inputStream);
        return new UTF8Item(new String(bytes, StandardCharsets.UTF_8));
    }
}
