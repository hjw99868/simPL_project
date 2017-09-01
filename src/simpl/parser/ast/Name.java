package simpl.parser.ast;

import simpl.interpreter.RecValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Name extends Expr {

    public Symbol x;

    public Name(Symbol x) {
        this.x = x;
    }

    public String toString() {
        return "" + x;
    }

    @Override
    //name
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        Type t = E.get(x);
        
        if (t == null) { throw new TypeError("Name undefine!"); }
        else { return TypeResult.of(Substitution.IDENTITY , t); }
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value v = s.E.get(x);
        
        if (v == null) { throw new RuntimeError("Name undefine!"); }
        if (v instanceof RecValue) { return ((RecValue)v).e.eval(s); }
        return v;
    }
}
