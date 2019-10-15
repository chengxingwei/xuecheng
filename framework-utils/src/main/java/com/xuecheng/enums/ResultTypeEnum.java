package com.xuecheng.enums;

public enum ResultTypeEnum {
    SUCCESS(0),FAIL(1);

    private int value;

    private ResultTypeEnum(int num) {
        this.value = num;
    }

    public int toValue() {
        return value;
    }
}
