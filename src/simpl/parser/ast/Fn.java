package simpl.parser.ast;

import simpl.interpreter.FunValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.ArrowType;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Fn extends Expr {

    public Symbol x;
    public Expr e;

    public Fn(Symbol x, Expr e) {
        this.x = x;
        this.e = e;
    }

    public String toString() {
        return "(fn " + x + "." + e + ")";
    }

    @Override
    //function fn x e
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeVar v = new TypeVar(false);
        TypeResult r = e.typecheck(TypeEnv.of(E , x , v));
        
        return TypeResult.of(r.s , new ArrowType(r.s.apply(v) , r.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        return new FunValue(s.E , x , e);
    }
}
