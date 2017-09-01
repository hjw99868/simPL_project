package simpl.parser.ast;

import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.ArrowType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class App extends BinaryExpr {

    public App(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeVar tv = new TypeVar(false);
        TypeResult lr = l.typecheck(E);
        TypeResult rr = r.typecheck(E);
        Substitution s = rr.s.compose(lr.s);
        s = (new ArrowType(rr.t,tv)).unify(lr.t).compose(s);
        Type t = s.apply(tv);
        return TypeResult.of(s , t);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        FunValue fun = (FunValue) l.eval(s);
        Value v = r.eval(s);
        State ss = State.of(new Env(fun.E , fun.x , v) , s.M , s.p);
        Value result = fun.e.eval(ss);
        return result;
    }
}
