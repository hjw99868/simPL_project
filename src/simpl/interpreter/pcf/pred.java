package simpl.interpreter.pcf;

import simpl.interpreter.BoolValue;
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

public class pred extends FunValue {

    //pred·µ»Øv.n-1
    public pred() {
        // TODO
        super(Env.empty , Symbol.symbol("pred") , new Expr(){
            public TypeResult typecheck(TypeEnv E) throws TypeError{
                return null;
            }
            
            public Value eval(State s) throws RuntimeError{
                IntValue v = (IntValue)(s.E.get(Symbol.symbol("pred")));
                return new IntValue(v.n-1);
            }
        });
    }
}
