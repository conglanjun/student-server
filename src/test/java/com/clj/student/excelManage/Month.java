package com.clj.student.excelManage;

public enum Month {
    JANUARY(1, "January", "一月"),
    FEBRUARY(2, "February", "二月"),
    MARCH(3, "March", "三月"),
    APRIL(4, "April", "四月"),
    MAY(5, "May", "五月"),
    JUNE(6, "June", "六月"),
    JULY(7, "July", "七月"),
    AUGUST(8, "August", "八月"),
    SEPTEMBER(9, "September", "九月"),
    OCTOBER(10, "October", "十月"),
    NOVEMBER(11, "November", "十一月"),
    DECEMBER(12, "December", "十二月");

    private final int value;
    private final String name;
    private final String nameLan;

    Month(int value, String name, String nameLan) {
        this.value = value;
        this.name = name;
        this.nameLan = nameLan;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String getNameLan() {
        return nameLan;
    }

    @Override
    public String toString() {
        return name;
    }
}
