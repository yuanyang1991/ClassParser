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
    private static final String CONSTANT_VALUE_STACKMAPTABLE = "StackMapTable";
    private static final String CONSTANT_VALUE_EXCEPTIONS = "Exceptions";
    private static final String CONSTANT_VALUE_INNERCLASSES = "InnerClasses";
    private static final String CONSTANT_VALUE_ENCLOSINGMETHOD = "EnclosingMethod";
    private static final String CONSTANT_VALUE_SYNTHETIC = "Synthetic";
    private static final String CONSTANT_VALUE_SIGNATURE = "Signature";
    private static final String CONSTANT_VALUE_SOURCEFILE = "SourceFile";
    private static final String CONSTANT_VALUE_SOURCEDEBUGEXTENSION = "SourceDebugExtension";
    private static final String CONSTANT_VALUE_LINENUMBERTABLE = "LineNumberTable";
    private static final String CONSTANT_VALUE_LOCALVARIABLETABLE = "LocalVariableTable";
    private static final String CONSTANT_VALUE_LOCALVARIABLETYPETABLE = "LocalVariableTypeTable";
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
        } else if (CONSTANT_VALUE_CODE.equals(name)){
            byte[] data = new byte[length];
            inputStream.read(data);
        } else {
            byte[] data = new byte[length];
            inputStream.read(data);
        }
        return null;
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
