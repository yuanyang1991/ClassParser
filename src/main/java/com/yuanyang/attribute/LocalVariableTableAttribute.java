package com.yuanyang.attribute;

public class LocalVariableTableAttribute extends Attribute {

    LocalVariableTable[] localVariableTables;

    public LocalVariableTableAttribute(int length, LocalVariableTable[] localVariableTables) {
        super(length);
        this.localVariableTables = localVariableTables;
    }

    public LocalVariableTable[] getLocalVariableTables() {
        return localVariableTables;
    }

    public static class LocalVariableTable {
        /**
         * 该变量在{@link CodeAttribute#instructions}中作用的起始索引
         */
        private int startPc;

        /**
         * 变量在指令中的作用范围，则该变量的作用范围为[start, start+length)
         */
        private int length;

        /**
         * 变量名称索引
         */
        private String name;

        /**
         * 变量descriptor
         */
        private String descriptor;

        /**
         * 变量在局部变量数组索引
         */
        private int index;

        public LocalVariableTable(int startPc, int length, String name, String descriptor, int index) {
            this.startPc = startPc;
            this.length = length;
            this.name = name;
            this.descriptor = descriptor;
            this.index = index;
        }

        public int getStartPc() {
            return startPc;
        }

        public int getLength() {
            return length;
        }

        public String getNameIndex() {
            return name;
        }

        public String getDescriptor() {
            return descriptor;
        }

        public int getIndex() {
            return index;
        }
    }
}
