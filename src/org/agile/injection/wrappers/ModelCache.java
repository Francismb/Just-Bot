package org.agile.injection.wrappers;

import org.agile.bot.api.wrappers.model.CacheModel;
import org.agile.bot.api.wrappers.model.Model;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Francis(AgileTM)
 * Date: 10/08/13
 * Time: 12:34 PM
 * Project: Client
 * Package: org.agile.injection.wrappers
 */
public class ModelCache {

    private static HashMap<Object, CacheModel> cache = new HashMap<>();

    public static void call(final Object instance, final Model model) {
        if (model != null) {
            if (!cache.containsKey(instance)) {
                final CacheModel entry = new CacheModel() {{
                    assign(model.getXTriangles(), model.getYTriangles(), model.getZTriangles(), model.getXVertices(), model.getYVertices(), model.getZVertices());
                }};
                cache.put(instance, entry);
            } else {
                cache.get(instance).assign(model.getXTriangles(), model.getYTriangles(), model.getZTriangles(), model.getXVertices(), model.getYVertices(), model.getZVertices());
            }
        }
    }

    public static CacheModel get(final Object instance) {
        return cache.get(instance);
    }

    public static void clear() {
        cache.clear();
    }

    public static boolean clearable() {
        return cache.size() > 0;
    }

}
