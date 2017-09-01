package simpl.interpreter.lib;

import simpl.interpreter.ConsValue;
import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.PairValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.parser.ast.Expr;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class hd extends FunValue {

    //hd·µ»ØlistµÄv1
    public hd() {
        // TODO
        super(Env.empty , Symbol.symbol("hd") , new Expr(){
            
            public TypeResult typecheck(TypeEnv E) throws TypeError{
                return null;
            }
            
            public Value eval(State s) throws RuntimeError{
                Value v = s.E.get(Symbol.symbol("hd"));
                if (v instanceof ConsValue) { return ((ConsValue)v).v1; }
                else { throw new RuntimeError("hd must list!"); }
            }
        });
    }
}
