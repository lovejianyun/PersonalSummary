package com.qijy.lambada.interfaces;

import java.io.BufferedReader;
import java.io.IOException;

@FunctionalInterface
public interface BufferReaderProcess {
    String process(BufferedReader br) throws IOException;
}
