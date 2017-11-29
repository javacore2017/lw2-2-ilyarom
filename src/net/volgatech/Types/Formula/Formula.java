package net.volgatech.Types.Formula;
import net.volgatech.Cell.CellValue;
import net.volgatech.Types.Formula.Sign.Sign;
import net.volgatech.Types.ValueType;

public class Formula extends CellValue {

    public Formula(CellValue left, CellValue right, Sign sign) {
        this.left = left;
        this.right = right;
        this.sign = sign;
    }
    public Sign getSign() {
        return sign;
    }

    public CellValue getLeft() {
        return left;
    }

    public CellValue getRight() {
        return right;
    }

    public ValueType getType() {return type;}

    public void setLeft(CellValue value) {
        this.left = new CellValue(value.getStringValue(), value.getType());
    }

    public void setRight(CellValue value) {
        this.right = new CellValue(value.getStringValue(), value.getType());
    }

    private final ValueType type = ValueType.FORMULA;
    private CellValue left;
    private CellValue right;
    private Sign sign;
}
