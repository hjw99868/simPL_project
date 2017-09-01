package simpl.interpreter.pcf;

import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.parser.ast.Expr;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class succ extends FunValue {

    //succ·µ»Øv.n+1
    public succ() {
        // TODO
        super(Env.empty , Symbol.symbol("succ") , new Expr(){
            public TypeResult typecheck(TypeEnv E) throws TypeError{
                return null;
            }
            
            public Value eval(State s) throws RuntimeError{
                IntValue v = (IntValue)(s.E.get(Symbol.symbol("succ")));
                return new IntValue(v.n+1);
            }
        });
    }
}
