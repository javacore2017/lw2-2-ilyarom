package net.volgatech;

import net.volgatech.Cell.Cell;
import net.volgatech.Cell.CellValue;
import net.volgatech.Types.Formula.Formula;
import net.volgatech.Types.Formula.ReadFormat;
import net.volgatech.Types.Formula.Sign.*;
import net.volgatech.Types.NumberType;
import net.volgatech.Types.ValueType;
import java.util.*;

class SpreadsheetEngine {
    SpreadsheetEngine() {

    }
    void set(String index, String value) {
        field.put(new Cell(index), new CellValue(value));
    }

    void setFormula(String index, String value) {
        field.put(new Cell(index), new CellValue(value, ValueType.FORMULA));
    }

    private Cell getMaxIndex() {
        int x = 0;
        int y = 0;
        for (Map.Entry<Cell, CellValue> element : field.entrySet()) {
            if (element.getKey().getX() > x) {
                x = element.getKey().getX();
            }
            if (element.getKey().getY() > y) {
                y = element.getKey().getY();
            }
        }
        return new Cell(x + 1, y + 1);
    }


    private Formula parseFormula(String strFormula) {
        strFormula = strFormula.replace("(", " ( ");
        strFormula = strFormula.replace(")", " )");
        ReadFormat format = ReadFormat.SIGN;
        String subformula = "";
        Boolean isReadLeft = true;
        CellValue left = new CellValue();
        CellValue right = new CellValue();
        Sign sign = new Plus();
        for (String arg : strFormula.split(" ")) {
            if (arg.isEmpty()) {
                continue;
            }
          //  System.out.println(arg);
            switch(format) {
                case SIGN: {
                    switch (arg) {
                        case "+":  sign = new Plus();
                            break;
                        case "*":  sign = new Multiplication();
                            break;
                        case "/":  sign = new Division();
                            break;
                        case "-":  sign = new Minus();
                            break;
                    }
                    format = ReadFormat.VALUE;
                    continue;
                }
                case VALUE: {
                    if (arg.equals("(")) {
                        format = ReadFormat.FORMULA;
                        continue;
                    }
                    CellValue value;
                    if (Cell.isIndexFormat(arg)) {
                        value = new CellValue(get(arg));
                    }
                    else {
                        value = new CellValue(arg);
                    }
                    if (isReadLeft) {
                        left = value;
                        isReadLeft = false;
                    }
                    else {
                        right = value;
                    }
                    continue;
                }
                case FORMULA: {
                    if (arg.equals(")")) {
                        format = ReadFormat.VALUE;
                        if (isReadLeft) {
                            left = new CellValue(subformula, ValueType.FORMULA);
                            isReadLeft = false;
                        }
                        else {
                            right = new CellValue(subformula, ValueType.FORMULA);
                        }
                        subformula = "";
                        continue;
                    }
                    subformula += (" " + arg);
                }
            }
        }
        return new Formula(left, right, sign);
    }

    private CellValue getFormulaValue(Formula formula) {
        CellValue result = new CellValue();
        if (formula.getLeft().getType() == ValueType.FORMULA) {
            Formula subformula = parseFormula(formula.getLeft().getStringValue());
            formula.setLeft(getFormulaValue(subformula));
        }
        if (formula.getRight().getType() == ValueType.FORMULA) {
             Formula subformula = parseFormula(formula.getRight().getStringValue());
             formula.setRight(getFormulaValue(subformula));
        }
        if (formula.getLeft().getType() != formula.getRight().getType() || formula.getLeft().getType() != ValueType.NUMBER) {
            result = new CellValue("!ERROR");
            return result;
        }
        NumberType left = new NumberType(formula.getLeft().getStringValue());
        NumberType right = new NumberType(formula.getRight().getStringValue());
        result.setType(ValueType.NUMBER);
        result.setValue(Double.toString(formula.getSign().calculate(left.getValue(), right.getValue())));
        return result;
    }

    void display() {
        Cell maxCell = getMaxIndex();
        List<String> stringField = new ArrayList<>();
        for (int i = 0; i < maxCell.getX() * maxCell.getY(); ++i) {
            stringField.add("");
        }

        for (Map.Entry<Cell, CellValue> element : field.entrySet()) {
          //  System.out.println(element.getValue().getStringValue());
            String value = element.getValue().getStringValue();
            if (element.getValue().getType() == ValueType.FORMULA) {
                Formula formula = parseFormula(element.getValue().getStringValue());
                value = getFormulaValue(formula).getStringValue();
            }
            stringField.set(element.getKey().getX() + (element.getKey().getY() * maxCell.getX()), value);
        }
        System.out.print(" ");
        for (int x = 0; x < maxCell.getX(); ++x) {
            System.out.print(Cell.getAlphabet().charAt(x) + " ");
        }
        System.out.println();
        for (int y = 0; y < maxCell.getY(); ++y) {
            System.out.print(y + " ");
            for (int x = 0; x < maxCell.getX(); ++x) {
                System.out.print(stringField.get(x + (y * maxCell.getX())) + " ");
            }
            System.out.println();
        }
        System.out.println(" ");
    }

    private CellValue get(String index) {
        Cell cell = new Cell(index);
        for (Map.Entry<Cell, CellValue> element : field.entrySet()) {
            if (element.getKey().equals(cell)) {
                return new CellValue(element.getValue().getStringValue(), element.getValue().getType());
            }
        }
        return new CellValue("");
    }
    private final Map<Cell, CellValue> field = new TreeMap<>(
            new Comparator<Cell>() {
               @Override
               public int compare(Cell o1, Cell o2) {
                   if (o1.getX() == o2.getX()) {
                       return (o1.getY()> o2.getY()) ? 1 : -1;
                   }
                   return (o1.getX()> o2.getX()) ? 1 : -1;
               }

           });
}
