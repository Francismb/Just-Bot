package org.agile.injection.transforms;

import org.agile.injection.Injector;
import org.agile.injection.Transformer;
import org.agile.reflection.storage.IdentityStorage;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Francis
 * Date: 26/03/13
 * Time: 5:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class CanvasClassTransformer extends Transformer {

    public CanvasClassTransformer(Injector instance) {
        super(instance);
    }

    @Override
    public ClassNode transform(ClassNode cn) {
        cn.superName = "org/agile/injection/wrappers/Canvas";
        for (final MethodNode method : (List<MethodNode>) cn.methods) {
            if (method.name.equals("<init>")) {
                final ListIterator iterator = method.instructions.iterator();
                while (iterator.hasNext()) {
                    final AbstractInsnNode node = (AbstractInsnNode) iterator.next();
                    if (node.getOpcode() == INVOKESPECIAL) {
                        final MethodInsnNode edit = (MethodInsnNode) node;
                        edit.owner = "org/agile/injection/wrappers/Canvas";
                        break;
                    }
                }
            }
        }
        System.out.println("^ - Injected Canvas Class[SUPERNAME, INVOCATION]");
        return cn;
    }

    @Override
    public String name() {
        return IdentityStorage.get("Canvas").getClassName();
    }
}
