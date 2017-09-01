package simpl.parser.ast;

import simpl.interpreter.RefValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.*;

public class Ref extends UnaryExpr {

    public Ref(Expr e) {
        super(e);
    }

    public String toString() {
        return "(ref " + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult r = e.typecheck(E);
        Type t = r.t;
        Substitution s = r.s;
        t = s.apply(t);
        return TypeResult.of(s , new RefType(t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        int p = s.p.get();
        Value v = e.eval(s);
        s.p.set(p + 1);
        s.M.put(p , v);
        
        return new RefValue(p);
    }
}
