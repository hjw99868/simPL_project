package simpl.parser.ast;

import simpl.interpreter.PairValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.PairType;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Pair extends BinaryExpr {

    public Pair(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(pair " + l + " " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult lr = l.typecheck(E);
        TypeResult rr = r.typecheck(lr.s.compose(E));
        
        return TypeResult.of(rr.s.compose(lr.s) , new PairType(lr.t , rr.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value lv = l.eval(s);
        Value rv = r.eval(s);
        
        return new PairValue(lv , rv);
    }
}
