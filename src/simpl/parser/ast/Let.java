package simpl.parser.ast;

import simpl.interpreter.Env;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.Substitution;

public class Let extends Expr {

    public Symbol x;
    public Expr e1, e2;

    public Let(Symbol x, Expr e1, Expr e2) {
        this.x = x;
        this.e1 = e1;
        this.e2 = e2;
    }

    public String toString() {
        return "(let " + x + " = " + e1 + " in " + e2 + ")";
    }

    @Override
    //Let”Ôæ‰
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult r1 = e1.typecheck(E);
        E = TypeEnv.of(E , x , r1.t);
        
        TypeResult r2 = e2.typecheck(E);
        Substitution s = r2.s.compose(r1.s);
        
        return TypeResult.of(s,s.apply(r2.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value v1 = e1.eval(s);
        Env env = new Env(s.E , x , v1);
        Value v2 = e2.eval(State.of(env , s.M , s.p));
        return v2;
    }
}
