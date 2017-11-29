package net.volgatech.Types;

import net.volgatech.Cell.CellValue;


public class NumberType extends CellValue {
    public NumberType(double value) {
        this.value = value;
    }
    private double value;
    public NumberType(String arg) {
        this.value = Double.parseDouble(arg);
    }
    public Double getValue() {
        return this.value;
    }
    private final ValueType _type = ValueType.NUMBER;
}
