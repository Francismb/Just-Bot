package org.agile.reflection.loader;

import org.agile.reflection.storage.ClassStorage;

import java.awt.*;
import java.io.File;
import java.io.FilePermission;
import java.net.SocketPermission;
import java.net.URL;
import java.security.CodeSigner;
import java.security.CodeSource;
import java.security.Permissions;
import java.security.ProtectionDomain;
import java.util.HashMap;
import java.util.Map;
import java.util.PropertyPermission;

/**
 * Created with IntelliJ IDEA.
 * User: Francis
 * Date: 26/03/13
 * Time: 5:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class ByteClassLoader extends java.lang.ClassLoader {

    public final Map<String, byte[]> classes;
    private ProtectionDomain domain;

    public ByteClassLoader(final Map<String, byte[]> classes) {
        this.classes = classes;
        try {
            CodeSource codeSource = new CodeSource(new URL("http://runescape.com/"), (CodeSigner[]) null);
            domain = new ProtectionDomain(codeSource, getPermissions());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Permissions getPermissions() {
        Permissions ps = new Permissions();
        ps.add(new AWTPermission("accessEventQueue"));
        ps.add(new PropertyPermission("user.home", "read"));
        ps.add(new PropertyPermission("java.vendor", "read"));
        ps.add(new PropertyPermission("java.version", "read"));
        ps.add(new PropertyPermission("os.name", "read"));
        ps.add(new PropertyPermission("os.arch", "read"));
        ps.add(new PropertyPermission("os.version", "read"));
        ps.add(new SocketPermission("*", "connect,resolve"));
        String uDir = System.getProperty("user.home");
        if (uDir != null) {
            uDir += "/";
        } else {
            uDir = "~/";
        }
        String[] dirs = {"c:/rscache/", "/rscache/", "c:/windows/", "c:/winnt/", "c:/", uDir, "/tmp/", "."};
        String[] rsDirs = {".jagex_cache_32", ".file_store_32"};
        for (String dir : dirs) {
            File f = new File(dir);
            ps.add(new FilePermission(dir, "read"));
            if (!f.exists()) continue;
            dir = f.getPath();
            for (String rsDir : rsDirs) {
                ps.add(new FilePermission(dir + File.separator + rsDir + File.separator + "-", "read"));
                ps.add(new FilePermission(dir + File.separator + rsDir + File.separator + "-", "write"));
            }
        }
        ps.setReadOnly();
        return ps;
    }

    public final Class<?> loadClass(String name) {
        try {
            if (classes.containsKey(name)) {
                byte buffer[] = classes.remove(name);
                Class<?> clazz = defineClass(name, buffer, 0, buffer.length, domain);
                ClassStorage.classes.put(name, clazz);
                return clazz;
            } else {
                Class<?> clazz = super.loadClass(name);
                ClassStorage.classes.put(name, clazz);
                return clazz;
            }
        } catch (final ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        return null;
    }

}
