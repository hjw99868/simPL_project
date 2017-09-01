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

public class Cond extends Expr {

    public Expr e1, e2, e3;

    public Cond(Expr e1, Expr e2, Expr e3) {
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
    }

    public String toString() {
        return "(if " + e1 + " then " + e2 + " else " + e3 + ")";
    }

    @Override
    //if then else”Ôæ‰
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult e1r = e1.typecheck(E);
        Substitution s1 = e1r.s;
        Substitution s2 = e1r.t.unify(Type.BOOL); 
        
        s2 = s2.compose(s1);  
        
        TypeResult e2r = e2.typecheck(s2.compose(E));
        s2 = e2r.s.compose(s2);
        
        TypeResult e3r = e3.typecheck(s2.compose(E));
        s2 = e3r.s.compose(s2);
        Substitution s3 = e3r.t.unify(e3r.s.apply(e2r.t));
        
        return TypeResult.of(s3.compose(s2) , s3.apply(e3r.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        BoolValue test = (BoolValue) e1.eval(s);

        if (test.b) { return e2.eval(s); }
        else { return e3.eval(s); }
    }
}
