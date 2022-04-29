package com.yuanyang.attribute;

import com.yuanyang.Parser;
import com.yuanyang.StreamUtils;
import com.yuanyang.ValueUtils;
import com.yuanyang.constant_pool.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

public class AttributeParser implements Parser<Attribute> {

    private static final String CONSTANT_VALUE_NAME = "ConstantValue";
    private static final String CONSTANT_VALUE_CODE = "Code";
    private static final String STACK_MAP_TABLE = "StackMapTable";
    private static final String EXCEPTIONS = "Exceptions";
    private static final String INNER_CLASSES = "InnerClasses";
    private static final String CONSTANT_VALUE_ENCLOSINGMETHOD = "EnclosingMethod";
    private static final String CONSTANT_VALUE_SYNTHETIC = "Synthetic";
    private static final String CONSTANT_VALUE_SIGNATURE = "Signature";
    private static final String CONSTANT_VALUE_SOURCEFILE = "SourceFile";
    private static final String CONSTANT_VALUE_SOURCEDEBUGEXTENSION = "SourceDebugExtension";
    private static final String CONSTANT_VALUE_LINENUMBERTABLE = "LineNumberTable";
    private static final String LOCAL_VARIABLE_TABLE = "LocalVariableTable";
    private static final String LOCAL_VARIABLE_TYPE_TABLE = "LocalVariableTypeTable";
    private static final String CONSTANT_VALUE_DEPRECATED = "Deprecated";
    private static final String CONSTANT_VALUE_RUNTIMEVISIBLEANNOTATIONS = "RuntimeVisibleAnnotations";
    private static final String CONSTANT_VALUE_RUNTIMEINVISIBLEANNOTATIONS = "RuntimeInvisibleAnnotations";
    private static final String CONSTANT_VALUE_RUNTIMEVISIBLEPARAMETERANNOTATIONS = "RuntimeVisibleParameterAnnotations";
    private static final String CONSTANT_VALUE_RUNTIMEINVISIBLEPARAMETERANNOTATIONS = "RuntimeInvisibleParameterAnnotations";
    private static final String CONSTANT_VALUE_ANNOTATIONDEFAULT = "AnnotationDefault";
    private static final String CONSTANT_VALUE_BOOTSTRAPMETHODS = "BootstrapMethods";

    @Override
    public Attribute parse(InputStream inputStream, ConstantPool constantPool) throws IOException {
        int nameIndex = StreamUtils.readU2(inputStream);
        String name = ValueUtils.getUTF8(nameIndex, constantPool);
        int length = StreamUtils.readU4(inputStream);
        if (CONSTANT_VALUE_NAME.equals(name)) {
            return getConstantValueAttribute(inputStream, constantPool);
        } else if (CONSTANT_VALUE_CODE.equals(name)) {
            return getCodeAttribute(inputStream, length, constantPool);
        } else if (LOCAL_VARIABLE_TABLE.equals(name)) {
            int localVariableTableLen = StreamUtils.readU2(inputStream);
            LocalVariableTableAttribute.LocalVariableTable[] localVariableTables =
                    new LocalVariableTableAttribute.LocalVariableTable[localVariableTableLen];
            for (int i = 0; i < localVariableTableLen; i++) {
                localVariableTables[i] = getLocalVTable(inputStream, constantPool);
            }
            return new LocalVariableTableAttribute(length, localVariableTables);
        } else if (EXCEPTIONS.equals(name)) {
            int exceptionLen = StreamUtils.readU2(inputStream);
            String[] exceptions = new String[exceptionLen];
            for (int i = 0; i < exceptionLen; i++) {
                exceptions[i] = ValueUtils.getClass(inputStream, constantPool);
            }
            return new ExceptionAttribute(length, exceptions);
        } else {
            byte[] data = new byte[length];
            inputStream.read(data);
            return new OtherAttribute(length, data);
        }
    }

    private LocalVariableTableAttribute.LocalVariableTable getLocalVTable(InputStream inputStream, ConstantPool constantPool)
            throws IOException {
        int startPc = StreamUtils.readU2(inputStream);
        int length = StreamUtils.readU2(inputStream);
        int nameIndex = StreamUtils.readU2(inputStream);
        String name = ValueUtils.getUTF8(nameIndex, constantPool);
        int descriptorIndex = StreamUtils.readU2(inputStream);
        String descriptor = ValueUtils.getUTF8(descriptorIndex, constantPool);
        int index = StreamUtils.readU2(inputStream);
        return new LocalVariableTableAttribute.LocalVariableTable(startPc, length, name, descriptor, index);
    }


    private CodeAttribute getCodeAttribute(InputStream inputStream, int length, ConstantPool constantPool) throws IOException {
        int maxStack = StreamUtils.readU2(inputStream);
        int maxLocals = StreamUtils.readU2(inputStream);
        int codeLen = StreamUtils.readU4(inputStream);
        int[] instructions = new int[codeLen];
        for (int i = 0; i < codeLen; i++) {
            instructions[i] = inputStream.read();
        }
        int exceptionLen = StreamUtils.readU2(inputStream);
        byte[] exceptions = new byte[exceptionLen];
        inputStream.read(exceptions);
        int attributeLen = StreamUtils.readU2(inputStream);
        Attribute[] attributes = new Attribute[attributeLen];
        for (int i = 0; i < attributeLen; i++) {
            attributes[i] = parse(inputStream, constantPool);
        }
        return new CodeAttribute(length, maxStack, maxLocals, instructions, attributes);
    }


    private ConstantValueAttribute<? extends Serializable> getConstantValueAttribute(InputStream inputStream, ConstantPool constantPool) throws IOException {
        int valueIndex = StreamUtils.readU2(inputStream);
        ConstantItem item = constantPool.get(valueIndex);
        if (item instanceof IntegerConstantItem) {
            return new ConstantValueAttribute<>(((IntegerConstantItem) item).getValue());
        } else if (item instanceof StringConstantItem) {
            String value = ((UTF8ConstantItem) constantPool.get(((StringConstantItem) item).getStringIndex())).getValue();
            return new ConstantValueAttribute<>(value);
        } else if (item instanceof LongConstantItem) {
            return new ConstantValueAttribute<>(((LongConstantItem) item).getValue());
        } else if (item instanceof FloatConstantItem) {
            return new ConstantValueAttribute<>(((FloatConstantItem) item).getValue());
        } else if (item instanceof DoubleConstantItem) {
            return new ConstantValueAttribute<>(((DoubleConstantItem) item).getValue());
        } else {
            throw new IllegalArgumentException("incorrect constant value constant");
        }
    }
}
