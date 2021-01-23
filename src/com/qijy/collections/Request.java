package com.qijy.collections;

import com.qijy.collections.annotation.DateTime;
import com.qijy.collections.annotation.FiledLike;

public class Request {
    @FiledLike
    private String name;
    @FiledLike
    private String id;
    @DateTime(name="qijy",type = "start")
    private String starttime;
    @DateTime(name = "qijy")
    private String time;
    @DateTime(name = "qijy",type="end")
    private String endtime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
}
