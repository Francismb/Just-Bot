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
 * Date: 29/03/13
 * Time: 11:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class KeyboardClassTransformer extends Transformer {

    public KeyboardClassTransformer(Injector instance) {
        super(instance);
    }

    @Override
    public ClassNode transform(ClassNode cn) {
        int count = 0;
        for (final MethodNode method : (List<MethodNode>) cn.methods) {
            if (method.name.startsWith("key") || method.name.startsWith("focus")) {
                method.name = "_" + method.name;
                count++;
            }
        }
        cn.superName = "org/agile/injection/wrappers/KeyListener";
        for (final MethodNode method : (List<MethodNode>) cn.methods) {
            if (method.name.equals("<init>")) {
                for (final AbstractInsnNode node : method.instructions.toArray()) {
                    if (node.getOpcode() == INVOKESPECIAL) {
                        final MethodInsnNode edit = (MethodInsnNode) node;
                        if (edit.owner.equals("java/lang/Object")) {
                            edit.owner = "org/agile/injection/wrappers/KeyListener";
                            break;
                        }
                    }
                }
                break;
            }
        }
        System.out.println("^ - Injected Keyboard Class[SUPERNAME, INVOCATION, METHOD RENAME(" + count + ")]");
        return cn;
    }

    @Override
    public String name() {
        return IdentityStorage.get("Keyboard").getClassName();
    }
}
