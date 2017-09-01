package simpl.parser.ast;

import simpl.typing.ListType;
import simpl.typing.PairType;
import simpl.typing.RefType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public abstract class EqExpr extends BinaryExpr {

    public EqExpr(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    //œ‡µ»typecheck
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult lr = l.typecheck(E);
        TypeResult rr = r.typecheck(E);
        Substitution s = rr.t.unify(rr.s.apply(lr.t));

        if (s.apply(rr.t).isEqualityType()) {
            return TypeResult.of(s.compose(rr.s.compose(lr.s)) , Type.BOOL);           
        }
        else {
            throw new TypeError("EqExp error");           
        }
    }
}
