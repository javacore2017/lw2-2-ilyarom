package net.volgatech.Types;

import net.volgatech.Cell.CellValue;

public class StringType extends CellValue {
    public StringType(String value) {
        _value = value;
    }
    private String _value;
    private final ValueType _type = ValueType.STRING;
    @Override
    public ValueType getType() {
        return _type;
    }
}
