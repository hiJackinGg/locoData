package com.parse;

/**
 * Клас для збереження секції.
 */
public enum Section {

    SECTION_A("A"), SECTION_B("B"), SECTION_UNKNOWN("U");

    private String name;

    Section(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "\"" + name + "\"";
    }

    public static Section getSection(byte sectionByte) {
        if (sectionByte == -64) {
            return SECTION_A;
        }
        if (sectionByte == -63) {
            return SECTION_B;
        }
        return SECTION_UNKNOWN;
    }
}
