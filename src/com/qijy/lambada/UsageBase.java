package com.qijy.lambada;

import com.qijy.lambada.beans.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UsageBase {
    private List<Apple> appleList=null;

    public UsageBase() {
        this.appleList = Arrays.asList(new Apple("green", 100), new Apple("red", 120),new Apple("red",130));
    }

    protected List<Apple> getAppleList(){
        return appleList;
    }
}
