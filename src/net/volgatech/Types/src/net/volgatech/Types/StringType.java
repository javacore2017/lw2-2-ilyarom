package net.volgatech.Types;

import net.volgatech.Cell.CellValue;

public class StringType extends CellValue {
    public StringType(String value) {
        this.value = value;
    }
    private String this.value;
    private final ValueType this.type = ValueType.STRING;
    @Override
    public ValueType getType() {
        return this.type;
    }
}
