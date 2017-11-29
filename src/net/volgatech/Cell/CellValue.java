package net.volgatech.Cell;

import net.volgatech.Types.DateType;
import net.volgatech.Types.NumberType;
import net.volgatech.Types.ValueType;

public class CellValue {
    public CellValue(CellValue cellValue) {
        this.value = cellValue.value;
        setType(cellValue.getType());
    }
    private ValueType type;
    public CellValue() {}
    public CellValue(String arg) {
        try {
            new NumberType(arg);
            this.type = ValueType.NUMBER;
        }
        catch(Exception exc) {
            try {
                new DateType(arg);
                this.type = ValueType.DATE;
            }
            catch (Exception exc1) {
                this.type = ValueType.STRING;
            }
        }
        this.value = arg;
    }
    public CellValue(String arg, ValueType type) {
        this.value = arg;
        this.type = type;
    }
    private String value;
    public ValueType getType() {
        return this.type;
    }
    public void setType(ValueType type) {
        this.type = type;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getStringValue() {
        return this.value;
    }

}

