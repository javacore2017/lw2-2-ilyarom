package net.volgatech.Types;

import net.volgatech.Cell.CellValue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateType extends CellValue {
    public DateType(Date value) {
        this.value = value;
    }
    public Date getValue() {
        return this.value;
    }
    private Date value;
    private final ValueType _type = ValueType.DATE;
    public DateType(String arg) {
        try {
            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("dd.MM.yyyy");
            this.value = format.parse(arg);
        } catch (ParseException e) {
        }
    }

}
