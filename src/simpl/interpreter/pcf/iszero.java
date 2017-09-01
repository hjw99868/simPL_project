package simpl.interpreter.pcf;

import simpl.interpreter.BoolValue;
import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.IntValue;
import simpl.interpreter.PairValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.parser.ast.Expr;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class iszero extends FunValue {

    //iszero·µ»Øv.nÊÇ·ñÎª0
    public iszero() {
        // TODO
        super(Env.empty , Symbol.symbol("iszero") , new Expr(){
            public TypeResult typecheck(TypeEnv E) throws TypeError{
                return null;
            }
            
            public Value eval(State s) throws RuntimeError{
                IntValue v = (IntValue)(s.E.get(Symbol.symbol("iszero")));
                return new BoolValue(v.n == 0);
            }
        });
    }
}
