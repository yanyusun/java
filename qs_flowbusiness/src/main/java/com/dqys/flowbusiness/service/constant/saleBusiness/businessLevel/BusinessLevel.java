package com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel;


/**
 * Created by yan on 16-12-26.
 */
public  abstract class BusinessLevel {
    public static Integer level;
    public static String name;

    public static Integer getLevel() {
        return level;
    }

    public static void Level(Integer level) {
        level = level;
    }

    public static String getName() {
        return name;
    }

    public static void  setName(String name) {
        name = name;
    }
}
