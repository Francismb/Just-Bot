package org.agile.ui;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import org.agile.bot.script.Script;
import org.agile.bot.script.ScriptDefinition;
import org.agile.bot.script.ScriptManifest;

public class SelectorModel extends AbstractTableModel {

    List<ScriptDefinition> definitions;
    private static final String[] columnNames = {"Name", "Version", "Author", "Type"};

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return definitions.size();
    }

    @Override
    public Object getValueAt(int row, int col) {
        ScriptDefinition def = definitions.get(row);
        switch (col) {
            case 0:
                return def.getName();
            case 1:
                return def.getVersion();
            case 2:
                return def.getAuthor();
            case 3:
                return def.getType();
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int i, int j) {
        return false;
    }

    public SelectorModel(JTable table) {
        loadScripts();
    }

    public void loadScripts() {
        definitions = new ArrayList<>();
        loadFolder(definitions, new File("scripts"), new File("scripts"));
    }

    public ScriptDefinition getDefinitionAt(int idx) {
        return definitions.get(idx);
    }

    public void loadFolder(List<ScriptDefinition> scripts, File dir, File parent) {
        for (final File file : (dir == null ? parent : dir).listFiles()) {
            if (file.isDirectory()) {
                loadFolder(scripts, dir, parent);
            } else if (file.isFile()) {
                final String name = file.getName();
                try {
                    if (name.endsWith(".class") && name.indexOf('$') == -1) {
                        final URL src = parent.getCanonicalFile().toURI().toURL();
                        final ClassLoader cl = new URLClassLoader(new URL[]{src});
                        String className = file.getCanonicalPath().substring(parent.getCanonicalPath().length() + 1);
                        className = className.substring(0, className.lastIndexOf('.'));
                        className = className.replace(File.separatorChar, '.');
                        final Class<?> clazz = cl.loadClass(className);
                        if (Script.class.isAssignableFrom(clazz)) {
                            final Class<? extends Script> script = clazz.asSubclass(Script.class);
                            if (script.isAnnotationPresent(ScriptManifest.class)) {
                                final ScriptManifest m = script.getAnnotation(ScriptManifest.class);
                                final ScriptDefinition def = new ScriptDefinition(m, src, className);
                                scripts.add(def);
                            }
                        }
                    }
                } catch (final Exception ignored) {
                }
            }
        }
    }

}
