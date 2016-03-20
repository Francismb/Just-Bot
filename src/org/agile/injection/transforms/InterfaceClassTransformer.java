package org.agile.injection.transforms;

import org.agile.injection.Injector;
import org.agile.injection.Transformer;
import org.agile.reflection.storage.IdentityStorage;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.*;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

/**
 * User: Francis(AgileTM)
 * Date: 14/08/13
 * Time: 8:01 PM
 * Project: Client
 * Package: org.agile.injection.transforms
 */
public class InterfaceClassTransformer extends Transformer {

    public InterfaceClassTransformer(Injector instance) {
        super(instance);
    }

    @Override
    public ClassNode transform(ClassNode cn) {
        cn.fields.add(new FieldNode(ACC_PUBLIC, "masterX", "I", null, null));
        cn.fields.add(new FieldNode(ACC_PUBLIC, "masterY", "I", null, null));
        for (final ClassNode cnode : instance.content.values()) {
            for (final MethodNode mnode : (List<MethodNode>) cnode.methods) {
                if ((mnode.access & ACC_STATIC) == ACC_STATIC && mnode.desc.contains("[L" + IdentityStorage.get("Component").getClassName() + ";") && getCount(new String[]{"I", "B"}, mnode.desc) > 8) {
                    AbstractInsnNode top;
                    boolean found = false;
                    final ListIterator<AbstractInsnNode> iterator = mnode.instructions.iterator();
                    while ((top = iterator.next()) != null) {
                        if (top.getOpcode() == GETFIELD) {
                            final FieldInsnNode field = (FieldInsnNode) top;
                            if (field.owner.equals(IdentityStorage.get("Component").getClassName()) && field.name.equals(IdentityStorage.get("Component").getIdentity("getX").getFieldName())) {
                                found = true;
                            }
                        } else if (found && top.getOpcode() == ASTORE) {
                            int start = mnode.instructions.indexOf(top);
                            mnode.instructions.insert(mnode.instructions.get(start), new VarInsnNode(ALOAD, 11));
                            mnode.instructions.insert(mnode.instructions.get(++start), new VarInsnNode(ILOAD, 6));
                            mnode.instructions.insert(mnode.instructions.get(++start), new FieldInsnNode(PUTFIELD, IdentityStorage.get("Component").getClassName(), "masterX", "I"));
                            mnode.instructions.insert(mnode.instructions.get(++start), new VarInsnNode(ALOAD, 11));
                            mnode.instructions.insert(mnode.instructions.get(++start), new VarInsnNode(ILOAD, 7));
                            mnode.instructions.insert(mnode.instructions.get(++start), new FieldInsnNode(PUTFIELD, IdentityStorage.get("Component").getClassName(), "masterY", "I"));
                            break;
                        }
                    }
                }
            }
        }
        System.out.println("^ - Injected Interface Class[MASTER X/Y]");
        return cn;
    }

    public int getCount(final String[] type, final String input) {
        int count = 0;
        for (final String string : input.split("")) {
            for (final String ty : type) {
                if (ty.equals(string)) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void dump(final ClassNode cn, final String fileName) {
        try {
            FileOutputStream stream = new FileOutputStream(new File(fileName));
            JarOutputStream out = new JarOutputStream(stream);
            JarEntry je = new JarEntry(cn.name + ".class");
            out.putNextEntry(je);
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            cn.accept(cw);
            out.write(cw.toByteArray());
            out.close();
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String name() {
        return IdentityStorage.get("Component").getClassName();
    }
}
