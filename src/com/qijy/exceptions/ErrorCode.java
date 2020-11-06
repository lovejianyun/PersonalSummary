package com.qijy.exceptions;
/*
 * @ Description   :  接口
 * @ Author        :  qijy
 * @ CreateDate    :  2020/6/5 10:58
 */
public interface ErrorCode {
    /**
     * 获取错误码
     * @return
     */
    String getCode();
    /**
     * 获取错误信息
     * @return
     */
    String getDescription();
}
