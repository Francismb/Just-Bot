package org.agile.injection.transforms;

import org.agile.injection.Injector;
import org.agile.injection.Transformer;
import org.agile.reflection.storage.IdentityStorage;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Francis
 * Date: 26/03/13
 * Time: 5:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class MouseClassTransform extends Transformer {

    public MouseClassTransform(Injector instance) {
        super(instance);
    }

    @Override
    public ClassNode transform(ClassNode cn) {
        int count = 0;
        for (final MethodNode method : (List<MethodNode>) cn.methods) {
            if (method.name.startsWith("mouse")) {
                method.name = "_" + method.name;
                count++;
            }
        }
        cn.superName = "org/agile/injection/wrappers/MouseListener";
        for (final MethodNode method : (List<MethodNode>) cn.methods) {
            if (method.name.equals("<init>")) {
                for (final AbstractInsnNode node : method.instructions.toArray()) {
                    if (node.getOpcode() == INVOKESPECIAL) {
                        final MethodInsnNode edit = (MethodInsnNode) node;
                        if (edit.owner.equals("java/lang/Object")) {
                            edit.owner = "org/agile/injection/wrappers/MouseListener";
                            break;
                        }
                    }
                }
                break;
            }
        }
        System.out.println("^ - Injected Mouse Class[SUPERNAME, INVOCATION, METHOD RENAME(" + count + ")]");
        return cn;
    }

    @Override
    public String name() {
        return IdentityStorage.get("Mouse").getClassName();
    }
}
