package simpl.typing;

import simpl.parser.Symbol;

public class TypeVar extends Type {

    private static int tvcnt = 0;

    private boolean equalityType;
    private Symbol name;

    public TypeVar(boolean equalityType) {
        this.equalityType = equalityType;
        name = Symbol.symbol("tv" + ++tvcnt);
    }

    @Override
    public boolean isEqualityType() {
        return equalityType;
    }

    @Override
    public Substitution unify(Type t) throws TypeCircularityError {
        // TODO
        if (t instanceof TypeVar) {
            return Substitution.of(this , t);
        }
        if (t.contains(this)) {
            throw new TypeCircularityError();
        }
        else {
             return Substitution.of(this , t) ;
        }
    }

    public String toString() {
        return "" + name;
    }

    @Override
    public boolean contains(TypeVar tv) {
        // TODO
        if (this.name.equals(tv.name)) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        // TODO
        if (a.name.equals(this.name)) {
            return t;
        }
        else {
            return this;
        }
    }
}
