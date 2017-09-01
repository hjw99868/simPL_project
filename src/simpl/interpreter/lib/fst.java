package simpl.interpreter.lib;

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

public class fst extends FunValue {

    //fst·µ»ØpairµÄv1
    public fst() {
        // TODO
        super(Env.empty , Symbol.symbol("fst") , new Expr(){
            
            public TypeResult typecheck(TypeEnv E) throws TypeError{
                return null;
            }
            
            public Value eval(State s) throws RuntimeError{
                Value v = s.E.get(Symbol.symbol("fst"));
                if (v instanceof PairValue) { return ((PairValue)v).v1; }
                else { throw new RuntimeError("fst must pair!"); }
            }
        });
    }
}
