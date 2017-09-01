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

public class Assign extends BinaryExpr {

    public Assign(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return l + " := " + r;
    }

    @Override
    //赋值表达式
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult lr = l.typecheck(E);
        Substitution s1 = lr.s;
        Type t1 = lr.t;
        
        TypeResult rr = r.typecheck(E);
        Substitution s2 = rr.s;
        Type t2 = rr.t;
        
        Substitution s = t1.unify(new RefType(s1.apply(t2)));
        
        return TypeResult.of(s.compose(s1.compose(s2)) , Type.UNIT);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        RefValue lv = (RefValue) l.eval(s);
        Value rv = r.eval(s);
        
        s.M.put(lv.p , rv);
        return Value.UNIT;
    }
}
