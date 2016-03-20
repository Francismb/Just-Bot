package org.agile.injection;

import org.agile.injection.transforms.*;
import org.agile.reflection.storage.IdentityStorage;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Francis
 * Date: 25/03/13
 * Time: 8:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class Injector {

    public final Map<String, ClassNode> content = new HashMap<>();
    private final Transformer[] transformers = new Transformer[]{
            new CanvasClassTransformer(this),
            new KeyboardClassTransformer(this),
            new MouseClassTransform(this),
            new ModelCallBackTransformer(this),
            new InterfaceClassTransformer(this)
    };

    public Injector(final Map<String, byte[]> content) {
        for (final Map.Entry<String, byte[]> entry : content.entrySet()) {
            this.content.put(entry.getKey(), create(entry.getValue()));
        }
    }

    public HashMap<String, byte[]> inject() {
        System.out.println("\n<<< Injecting Dependencies >>>");
        final HashMap<String, byte[]> newContent = new HashMap<>();
        for (final Map.Entry<String, ClassNode> entry : content.entrySet()) {
            for (final Transformer transformer : transformers) {
                if (transformer.name().equals(entry.getKey())) {
                    final ClassNode node = transformer.transform(content.get(transformer.name()));
                    newContent.put(node.name, uncreate(node));
                }
            }
            if (!newContent.containsKey(entry.getKey())) {
                newContent.put(entry.getKey(), uncreate(entry.getValue()));
            }
        }
        return newContent;
    }

    public ClassNode create(final byte[] bytes) {
        final ClassNode node = new ClassNode();
        final ClassReader reader = new ClassReader(bytes);
        reader.accept(node, ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
        return node;
    }

    public byte[] uncreate(final ClassNode node) {
        final ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        node.accept(writer);
        return writer.toByteArray();
    }
}
