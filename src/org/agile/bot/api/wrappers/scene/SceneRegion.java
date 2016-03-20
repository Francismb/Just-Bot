package org.agile.bot.api.wrappers.scene;

import org.agile.reflection.storage.IdentityStorage;
import org.agile.reflection.storage.type.ClassIdentity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Francis(AgileTM)
 * Date: 18/08/13
 * Time: 8:08 PM
 * Project: Client
 * Package: org.agile.bot.api.wrappers.scene
 */
public class SceneRegion {

    private final Object instance;
    private final ClassIdentity identity = IdentityStorage.get("SceneRegion");

    public SceneRegion(final Object instance) {
        this.instance = instance;
    }

    public List<SceneBlock> getBlocks() {
        final List<SceneBlock> blocks = new ArrayList<>();
        final Field field = identity.getField(identity.getIdentity("getSceneBlocks"));
        if (field != null) {
            try {
                final Object[][][] data = (Object[][][]) field.get(instance);
                if (data != null) {
                    for (int dim1 = 0; dim1 < data.length; dim1++) {
                        final Object[][] dim1Data = data[dim1];
                        for (int dim2 = 0; dim2 < dim1Data.length; dim2++) {
                            final Object[] dim2Data = data[dim1][dim2];
                            for (int dim3 = 0; dim3 < dim2Data.length; dim3++) {
                                final Object dim3Data = data[dim1][dim2][dim3];
                                if (dim3Data != null) {
                                    blocks.add(new SceneBlock(dim3Data, dim1, dim2, dim3));
                                }
                            }
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return blocks;
    }

}
