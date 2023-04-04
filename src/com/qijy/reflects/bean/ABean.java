package com.qijy.reflects.bean;

import java.util.List;

/**
 * @author qijy
 * @version 1.0
 * @description: TODO
 * @date 2023/3/24 14:25
 */
public class ABean extends BBean{
    private List<CBean> content;

    public List<CBean> getContent() {
        return content;
    }

    public void setContent(List<CBean> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ABean{" +
                "content=" + content +
                '}';
    }
}
