package simpl.parser.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Loop extends Expr {

    public Expr e1, e2;

    public Loop(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public String toString() {
        return "(while " + e1 + " do " + e2 + ")";
    }

    @Override
    //while”Ôæ‰ while e1 do e2
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult e1r = e1.typecheck(E);
        TypeResult e2r = e2.typecheck(E);
        Substitution s = e2r.s.compose(e1r.s);
        s = s.compose(e1r.t.unify(Type.BOOL));
        return TypeResult.of(s , Type.UNIT);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        BoolValue test = (BoolValue) e1.eval(s);
        
        if (test.b) { return new Seq(e2 , this).eval(s); }
        else { return Value.UNIT; }        
    }
}
