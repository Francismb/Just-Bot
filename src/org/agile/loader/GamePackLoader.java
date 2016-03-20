package org.agile.loader;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.jar.Pack200;
import java.util.zip.GZIPInputStream;

/**
 * Created with IntelliJ IDEA.
 * User: Francis
 * Date: 2/12/12
 * Time: 5:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class GamePackLoader {

    public final HashMap<String, byte[]> classes = new HashMap<>();

    public static byte[] getGamePackBytes(final URL url) {
        try {
            System.out.println("^ - Downloading Game Pack");
            final URLConnection conn = url.openConnection();
            final int contentLength = conn.getContentLength();
            final byte[] buffer;
            final DataInputStream dataInputStream = new DataInputStream(conn.getInputStream());
            buffer = new byte[contentLength];
            dataInputStream.readFully(buffer);
            return buffer;
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    public static Map<String, byte[]> extract(byte[] gamepack) {
        Map<String, byte[]> classes = new HashMap<>();
        try {
            JarInputStream jarInputStream = new JarInputStream(new ByteArrayInputStream(gamepack));
            JarEntry entry;
            while ((entry = jarInputStream.getNextJarEntry()) != null) {
                String entryName = entry.getName();
                if (entryName.endsWith(".class")) {
                    final byte[] read = read(jarInputStream);
                    entryName = entryName.replace('/', '.');
                    final String name = entryName.substring(0, entryName.length() - 6);
                    classes.put(name, read);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("^ - Loaded " + classes.size() + " Classes");
        return classes;
    }

    private static byte[] read(final JarInputStream inputStream) throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final byte[] buffer = new byte[2048];
        int read;
        while (inputStream.available() > 0) {
            read = inputStream.read(buffer, 0, buffer.length);
            if (read < 0) {
                break;
            }
            out.write(buffer, 0, read);
        }
        return out.toByteArray();
    }

}
