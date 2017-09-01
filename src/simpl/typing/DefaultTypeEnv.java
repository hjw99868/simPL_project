package simpl.typing;

import simpl.parser.Symbol;

public class DefaultTypeEnv extends TypeEnv {
    //È±Ê¡ÀàÐÍ

    private TypeEnv E;

    public DefaultTypeEnv() {
        // TODO
        Symbol fst = Symbol.symbol("fst");
        Symbol snd = Symbol.symbol("snd");
        Symbol hd = Symbol.symbol("hd");
        Symbol tl = Symbol.symbol("tl");
        Symbol iszero = Symbol.symbol("iszero");
        Symbol pred = Symbol.symbol("pred");
        Symbol succ = Symbol.symbol("succ");
        
        //fst:t1*t2->t1
        TypeVar t1 = new TypeVar(false);
        TypeVar t2 = new TypeVar(false);
        ArrowType fstt = new ArrowType(new PairType(t1 ,t2) , t1);
        E = TypeEnv.of(TypeEnv.empty , fst , fstt);
        
        //snd:t1*t2->t2
        t1 = new TypeVar(false);
        t2 = new TypeVar(false);
        ArrowType sndt = new ArrowType(new PairType(t1 ,t2) , t2);
        E = TypeEnv.of(E , snd , sndt);
        
        //hd:t list->t
        t1 = new TypeVar(false);
        ListType t3 = new ListType(t1);
        ArrowType hdt = new ArrowType(t3 , t1);
        E = TypeEnv.of(E , hd , hdt);
        
        //tl:t list->t list
        t1 = new TypeVar(false);
        t3 = new ListType(t1);
        ArrowType tlt = new ArrowType(t3 , t3);
        E = TypeEnv.of(E , tl , tlt);
        
        //iszero:int->bool
        ArrowType iszerot = new ArrowType(Type.INT , Type.BOOL);
        E = TypeEnv.of(E , iszero , iszerot);
        
        //pred:int->int
        ArrowType predt = new ArrowType(Type.INT , Type.INT);
        E = TypeEnv.of(E , pred , predt);
        
        //succ:int->int
        ArrowType succt = new ArrowType(Type.INT , Type.INT);
        E = TypeEnv.of(E , succ , succt);
          
    }

    @Override
    public Type get(Symbol x) {
        return E.get(x);
    }
}
