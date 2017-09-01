package simpl.interpreter.lib;

import simpl.interpreter.ConsValue;
import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.parser.ast.Expr;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class tl extends FunValue {

    //tl����list��v2
    public tl() {
        // TODO
        super(Env.empty , Symbol.symbol("tl") , new Expr(){
            
            public TypeResult typecheck(TypeEnv E) throws TypeError{
                return null;
            }
            
            public Value eval(State s) throws RuntimeError{
                Value v = s.E.get(Symbol.symbol("tl"));
                if (v instanceof ConsValue) { return ((ConsValue)v).v2; }
                else { throw new RuntimeError("tl must list!"); }
            }
        });
    }
}
