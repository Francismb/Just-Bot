package org.agile.injection.transforms;

import org.agile.bot.api.utilities.Random;
import org.agile.injection.Injector;
import org.agile.injection.Transformer;
import org.agile.reflection.storage.IdentityStorage;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.*;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.ListIterator;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

/**
 * User: Francis(AgileTM)
 * Date: 7/08/13
 * Time: 8:39 AM
 * Project: Client
 * Package: org.agile.injection.transforms
 */
public class ModelCallBackTransformer extends Transformer {

    public ModelCallBackTransformer(Injector instance) {
        super(instance);
    }

    @Override
    public ClassNode transform(ClassNode cn) {
        for (final MethodNode mn : (List<MethodNode>) cn.methods) {
            if ((mn.access & ACC_STATIC) != ACC_STATIC && mn.desc.contains(")V") && mn.tryCatchBlocks.size() == 1 && !mn.name.equals("<init>")) {
                final ListIterator<AbstractInsnNode> iterator = mn.instructions.iterator();
                AbstractInsnNode top;
                while (iterator.hasNext() && (top = iterator.next()) != null) {
                    if (top.getOpcode() == RETURN) {
                        int start = mn.instructions.indexOf(top) - 1;
                        mn.instructions.insert(mn.instructions.get(start), new VarInsnNode(ALOAD, 0));
                        mn.instructions.insert(mn.instructions.get(++start), new TypeInsnNode(NEW, "org/agile/bot/api/wrappers/model/Model"));
                        mn.instructions.insert(mn.instructions.get(++start), new InsnNode(DUP));
                        mn.instructions.insert(mn.instructions.get(++start), new VarInsnNode(ALOAD, 10));
                        mn.instructions.insert(mn.instructions.get(++start), new MethodInsnNode(INVOKESPECIAL, "org/agile/bot/api/wrappers/model/Model", "<init>", "(Ljava/lang/Object;)V"));
                        mn.instructions.insert(mn.instructions.get(++start), new MethodInsnNode(INVOKESTATIC, "org/agile/injection/wrappers/ModelCache", "call", "(Ljava/lang/Object;Lorg/agile/bot/api/wrappers/model/Model;)V"));
                    }
                }
            }
        }
        System.out.println("^ - Injected Renderable Class[CALLBACK]");
        return cn;
    }

    @Override
    public String name() {
        return IdentityStorage.get("Renderable").getClassName();
    }
}
