package simpl.parser.ast;

import simpl.interpreter.RefValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.RefType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Deref extends UnaryExpr {

    public Deref(Expr e) {
        super(e);
    }

    public String toString() {
        return "!" + e;
    }

    @Override
    //dereference
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult r = e.typecheck(E);
        Type v = new TypeVar(false);
        Type t = r.t;
        Substitution s = r.s;
        t = s.apply(t);
        
        if (t instanceof RefType) { return TypeResult.of(s,((RefType)t).t); }
        if (t instanceof TypeVar){
            s = t.unify(new RefType(v)).compose(s);
            v = s.apply(v);
            return TypeResult.of(s , v);
        }
        throw new TypeError("Deref must Reftype!");
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        RefValue v = (RefValue) e.eval(s);
        
        return s.M.get(v.p);
    }
}
