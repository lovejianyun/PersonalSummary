package com.qijy.lambada.functiontest;

import com.qijy.lambada.interfaces.BufferReaderProcess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/*
 * @ Description   :  文件函数式测试
 * @ Author        :  qijy
 * @ CreateDate    :  2020/11/10 23:05
 */
public class FunctionTest {
    public static void main(String[] args) throws IOException{
        System.out.println(processFile((BufferedReader br) -> br.readLine() + br.readLine()));
    }
    private static String processFile(BufferReaderProcess bufferReaderProcess) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\Administrator\\Desktop\\dockerfile.txt")));
        return bufferReaderProcess.process(br);
    }

}
