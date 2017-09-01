package simpl.parser.ast;

import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public abstract class ArithExpr extends BinaryExpr {

    public ArithExpr(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    //À„ ı‘ÀÀ„
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult lr = l.typecheck(E);
        Substitution s0 = lr.s;
        Substitution s1 = lr.t.unify(Type.INT);
        
        TypeResult rr = r.typecheck(E);
        Substitution s2 = rr.s;
        Substitution s3 = rr.t.unify(Type.INT);
        
        return TypeResult.of(s3.compose(s2.compose(s1.compose(s0))) , Type.INT);
    }
}
