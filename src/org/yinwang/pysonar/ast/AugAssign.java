package org.yinwang.pysonar.ast;

import org.yinwang.pysonar.Indexer;
import org.yinwang.pysonar.Scope;
import org.yinwang.pysonar.types.Type;

public class AugAssign extends Node {

    static final long serialVersionUID = -6479618862099506199L;

    public Node target;
    public Node value;
    public Name op;

    public AugAssign(Node target, Node value, Name op) {
        this(target, value, op, 0, 1);
    }

    public AugAssign(Node target, Node value, Name op, int start, int end) {
        super(start, end);
        this.target = target;
        this.value = value;
        this.op = op;
        addChildren(target, value);
    }

    @Override
    public Type resolve(Scope s, int tag) throws Exception {
        resolveExpr(target, s, tag);
        resolveExpr(value, s, tag);
        return Indexer.idx.builtins.Cont;
    }

    @Override
    public String toString() {
        return "<AugAssign:" + target + " " + op + "= " + value + ">";
    }

    @Override
    public void visit(NodeVisitor v) {
        if (v.visit(this)) {
            visitNode(target, v);
            visitNode(value, v);
        }
    }
}