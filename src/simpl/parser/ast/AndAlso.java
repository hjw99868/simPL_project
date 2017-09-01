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

public class AndAlso extends BinaryExpr {

    public AndAlso(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " andalso " + r + ")";
    }

    @Override
    //andalso��Ч�ʸ��ߵ�and
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult lr = l.typecheck(E);
        TypeResult rr = r.typecheck(E);
        Substitution s = rr.s.compose(lr.s);
        s = s.compose(lr.t.unify(Type.BOOL));
        s = s.compose(rr.t.unify(Type.BOOL));
        return TypeResult.of(s , Type.BOOL);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        BoolValue lv = (BoolValue) l.eval(s);
        BoolValue rv = (BoolValue) r.eval(s);
        if (lv.equals(Value.FALSE)) return Value.FALSE;
        else return rv;
    }
}
