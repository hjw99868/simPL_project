package simpl.interpreter;

class NilValue extends Value {

    protected NilValue() {
    }

    public String toString() {
        return "nil";
    }

    @Override
    public boolean equals(Object other) {
        // TODO
        if (other instanceof ConsValue) { return false; }
        else if (other instanceof NilValue) { return true; }
        else { return false; }
    }
}