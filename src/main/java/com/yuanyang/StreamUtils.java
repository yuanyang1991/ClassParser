package com.yuanyang;

import java.io.IOException;
import java.io.InputStream;

public class StreamUtils {

    public static int readU4(InputStream inputStream) throws IOException {
        int b0 = inputStream.read();
        int b1 = inputStream.read();
        int b2 = inputStream.read();
        int b3 = inputStream.read();
        return (b0 << 24) + (b1 << 16) + (b2 << 8) + b3;
    }

    public static int readU2(InputStream inputStream) throws IOException {
        int b0 = inputStream.read();
        int b1 = inputStream.read();
        return (b0 << 8) + b1;
    }

    public static int readU1(InputStream inputStream) throws IOException {
        return inputStream.read();
    }

    public static int readBytes(byte[] bytes, InputStream inputStream) throws IOException {
        return inputStream.read(bytes);
    }

    public static long readU8(InputStream inputStream) throws IOException {
        int b0 = inputStream.read();
        int b1 = inputStream.read();
        int b2 = inputStream.read();
        int b3 = inputStream.read();
        int b4 = inputStream.read();
        int b5 = inputStream.read();
        int b6 = inputStream.read();
        int b7 = inputStream.read();
        return (b0 << 24) + (b1 << 16) + (b2 << 8) + b3;
    }
}
