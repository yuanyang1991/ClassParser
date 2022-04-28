package com.yuanyang;

import com.yuanyang.constant_pool.ConstantPool;

import java.io.IOException;
import java.io.InputStream;

/**
 * 解析类抽象
 *
 * @param <T>
 */
public interface Parser<T> {


    T parse(InputStream inputStream, ConstantPool constantPool) throws IOException;
}
