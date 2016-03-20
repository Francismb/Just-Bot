package org.agile.injection;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Francis
 * Date: 26/03/13
 * Time: 5:06 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Transformer implements Opcodes {

    private final String interfacePath = "org/agile/injection/wrappers";
    public final Injector instance;

    public Transformer(final Injector instance) {
        this.instance = instance;
    }

    public abstract ClassNode transform(final ClassNode cn);

    public abstract String name();

    public void addGetter(Map<String, ClassNode> classes, ClassNode cn, String desc, String field_name, String multiplier, String name, String owner, String cname) {
        FieldNode field = getFieldNode(classes, owner, field_name);
        String returnType = null;
        if (cname != null && !cname.equals("")) {
            if (cname.startsWith("org")) {
                returnType = "L" + cname + ";";
            } else if (desc.endsWith(";") && desc.contains("L") && !desc.contains("Ljava")) {
                returnType = desc.substring(0, desc.indexOf("L") + 1) + interfacePath + cname + ";";
            }
        } else {
            returnType = desc;
        }
        MethodNode mn = new MethodNode(ACC_PUBLIC, name, "()" + returnType, null, null);
        boolean isStatic = (field.access & ACC_STATIC) == ACC_STATIC;
        if (isStatic) {
            mn.visitFieldInsn(GETSTATIC, owner, field_name, desc);
        } else {
            mn.visitVarInsn(ALOAD, 0);
            mn.visitFieldInsn(GETFIELD, owner, field_name, desc);
        }
        if (multiplier != null && !multiplier.isEmpty()) {
            mn.visitLdcInsn(Integer.parseInt(multiplier));
            mn.visitInsn(IMUL);
        }
        mn.visitInsn(getReturnOpcode(desc));
        mn.visitMaxs(0, 0);
        mn.visitEnd();
        mn.accept(cn);
    }

    public FieldNode getFieldNode(Map<String, ClassNode> classes, String owner, String field_name) {
        ClassNode cn = classes.get(owner);
        for (FieldNode fn : (List<FieldNode>) cn.fields) {
            if (fn.name.equals(field_name)) {
                return fn;
            }
        }
        return null;
    }

    public void addInterface(ClassNode cn, String classID) {
        System.out.println("injecting " + classID + " for " + cn.name);
        cn.interfaces.add(classID);
    }

    public void addSuper(ClassNode cn, String superName, NodeList renamed) {
        String oldSuper = cn.superName;
        cn.superName = superName;
        cn.interfaces = new ArrayList<>();
        Map<String, String> renamedMethods = null;
        if (renamed != null) {
            renamedMethods = new HashMap<>();
            for (int i = 0; i < renamed.getLength(); ++i) {
                Element element = (Element) renamed.item(i);
                String name = element.getAttribute("name");
                String newName = element.getAttribute("newname");
                System.out.println("RENAMED " + name + "=>" + newName);
                renamedMethods.put(name, newName);
            }
        }

        for (MethodNode mn : (List<MethodNode>) cn.methods) {
            if (renamedMethods != null) {
                String newName = renamedMethods.get(mn.name);
                if (newName != null) {
                    System.out.println("Swapped " + mn.name + " for " + newName);
                    mn.name = newName;
                }
            }
            for (AbstractInsnNode insn : mn.instructions.toArray()) {
                if (insn instanceof MethodInsnNode) {
                    MethodInsnNode min = (MethodInsnNode) insn;
                    if (min.getOpcode() == INVOKESPECIAL && min.owner.equals(oldSuper)) {
                        mn.instructions.set(min, new MethodInsnNode(INVOKESPECIAL, superName, min.name, min.desc));
                    }
                }
            }
        }
    }

    private int getReturnOpcode(String desc) {
        desc = desc.substring(desc.indexOf(")") + 1);
        if (desc.length() > 1) {
            return Opcodes.ARETURN;
        }
        final char c = desc.charAt(0);
        switch (c) {
            case 'I':
            case 'Z':
            case 'B':
            case 'S':
            case 'C':
                return Opcodes.IRETURN;
            case 'J':
                return Opcodes.LRETURN;
            case 'F':
                return Opcodes.FRETURN;
            case 'D':
                return Opcodes.DRETURN;
        }
        throw new RuntimeException("Bad Return");
    }

}
