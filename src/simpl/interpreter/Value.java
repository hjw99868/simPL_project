package simpl.interpreter;

public abstract class Value {

    public static final Value NIL = new NilValue();
    public static final Value UNIT = new UnitValue();
    public static final Value FALSE = new BoolValue(false);
    public static final Value TRUE = new BoolValue(true);
    

    public abstract boolean equals(Object other);
}
