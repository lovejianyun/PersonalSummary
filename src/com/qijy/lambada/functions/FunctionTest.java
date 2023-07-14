package com.qijy.lambada.functions;

import java.util.function.Function;

/**
 * @author qijy
 * @version 1.0
 * @description: TODO
 * @date 2023/7/10 17:23
 */
public class FunctionTest {
    public static void main(String[] args) {
        demo001();
        demo002();
    }
    /**
     * @description: compose 是从后往前推， andThen是从前往后推
     * @author qijy
     * @date 2023/7/10 17:29
     * @version 1.0
     */
    public static void demo001(){
        Function<Integer,Integer> square = n -> n*n;
        Function<Integer,Integer> squareAndCube = square.andThen(n -> n*n*n+1);
        System.out.println(squareAndCube.apply(2));
    }
    /**
     * @description: compose 是从后往前推， andThen是从前往后推
     * @author qijy
     * @date 2023/7/10 17:29
     * @version 1.0
     */
    public static  void demo002(){
        Function<Integer,Integer> square = n -> n*n;
        Function<Integer,Integer> squareAndCube = square.compose(n -> n*n*n + 1);
        System.out.println(squareAndCube.apply(2));
    }

}
