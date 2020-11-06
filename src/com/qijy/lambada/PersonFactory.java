package com.qijy.lambada;

public interface PersonFactory <T extends Person> {
    T create(String name ,String age);
}
