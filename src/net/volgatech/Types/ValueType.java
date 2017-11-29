package net.volgatech.Types;

import java.util.ArrayList;
import java.util.List;

public enum ValueType {
    NUMBER, DATE, STRING, FORMULA;
    public static final List<ValueType> types = createList();
    public static final List<ValueType> formulaTypes = createFormulaList();
    private static List<ValueType> createList() {
        List<ValueType> list = new ArrayList<>();
        list.add(FORMULA);
        list.add(DATE);
        list.add(STRING);
        list.add(NUMBER);
        return list;
    }
    private static List<ValueType> createFormulaList() {
        List<ValueType> list = new ArrayList<>();
        list.add(FORMULA);
        list.add(NUMBER);
        return list;
    }
}
