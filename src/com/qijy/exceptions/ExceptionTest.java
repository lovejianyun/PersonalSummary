package com.qijy.exceptions;

public class ExceptionTest {
    public static void main(String[] args) {
        try {
            String id = "";
            if("".equals(id)){
                throw new BizException(BizErrorCodeEnum.ID_IS_NULL);
            }
        } catch (BizException e) {
            System.out.println(e.getMessage());
        }
    }

}
