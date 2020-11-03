package com.dk.parent.enums;

/**
 * @date 2020-10-24 17:46:45
 */
public enum Enum_001 {

    SPRIGN("1", "春"),
    SUMMER("2", "夏"),
    AUTUMN("3", "秋"),
    WINTER("1", "冬");

    private final String value;
    private final String name;

    Enum_001(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public boolean match(String value){
        return value != null && value.equals(this.value);
    }
}
