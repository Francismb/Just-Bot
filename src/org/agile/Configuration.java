package org.agile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;

/**
 * User: Francis(AgileTM)
 * Date: 11/08/13
 * Time: 4:05 PM
 * Project: Client
 * Package: org.agile
 */
public class Configuration {

    public static final String home = System.getProperty("user.home") + File.separator + "Agile" + File.separator;

    private static final Queue<ConfigurationEntry> queue = new LinkedList<ConfigurationEntry>() {
        {
            offer(new ConfigurationEntry("PlayImage.png", "https://dl.dropboxusercontent.com/u/28468007/images/start.png", home + "images"));
            offer(new ConfigurationEntry("PlayHoverImage.png", "https://dl.dropboxusercontent.com/u/28468007/images/start_hover.png", home + "images"));
            offer(new ConfigurationEntry("PauseImage.png", "https://dl.dropboxusercontent.com/u/28468007/images/pause.png", home + "images"));
            offer(new ConfigurationEntry("PauseHoverImage.png", "https://dl.dropboxusercontent.com/u/28468007/images/pause_hover.png", home + "images"));
            offer(new ConfigurationEntry("StopImage.png", "https://dl.dropboxusercontent.com/u/28468007/images/stop.png", home + "images"));
            offer(new ConfigurationEntry("StopHoverImage.png", "https://dl.dropboxusercontent.com/u/28468007/images/stop_hover.png", home + "images"));
            offer(new ConfigurationEntry("RestartImage.png", "https://dl.dropboxusercontent.com/u/28468007/images/restart.png", home + "images"));
            offer(new ConfigurationEntry("RestartHoverImage.png", "https://dl.dropboxusercontent.com/u/28468007/images/restart_hover.png", home + "images"));
            offer(new ConfigurationEntry("SettingImage.png", "https://dl.dropboxusercontent.com/u/28468007/images/settings.png", home + "images"));
            offer(new ConfigurationEntry("SettingHoverImage.png", "https://dl.dropboxusercontent.com/u/28468007/images/settings_hover.png", home + "images"));
            offer(new ConfigurationEntry("Logo.png", "https://dl.dropboxusercontent.com/u/28468007/images/logo.png", home + "images"));
            offer(new ConfigurationEntry("KeyboardImage.png", "https://dl.dropboxusercontent.com/u/28468007/images/keyboard.png", home + "images"));
            offer(new ConfigurationEntry("KeyboardHoverImage.png", "https://dl.dropboxusercontent.com/u/28468007/images/keyboard_hover.png", home + "images"));
            offer(new ConfigurationEntry("CloseImage.png", "https://dl.dropboxusercontent.com/u/28468007/images/close.png", home + "images"));
            offer(new ConfigurationEntry("CloseHoverImage.png", "https://dl.dropboxusercontent.com/u/28468007/images/close_hover.png", home + "images"));
        }
    };

    public static boolean valid() {
        for (final ConfigurationEntry entry : queue) {
            if (entry.updatable()) {
                return false;
            }
        }
        return true;
    }

    public static void installDirectories() {
        int count = 0;
        final File base = new File(home);
        if (!base.exists()) {
            base.mkdir();
            count++;
        }
        final String[] subs = new String[]{"images"};
        for (final String sub : subs) {
            final File file = new File(home + sub);
            if (!file.exists()) {
                file.mkdir();
                count++;
            }
        }
        if (count > 0) {
            System.out.println("^ Installed " + count + " Folders");
        }
    }

    public static void installFiles() {
        int count = 0;
        ConfigurationEntry top;
        while ((top = queue.peek()) != null) {
            if (top.updatable()) {
                if (top.download()) {
                    queue.remove(top);
                    count++;
                } else {
                    System.out.println("Failed To Download " + top.name);
                }
            }
        }
        if (count > 0) {
            System.out.println("^ Installed " + count + " Files\n");
        }
    }

    public static class ConfigurationEntry {

        private final String name;
        private final String global;
        private final String location;

        public ConfigurationEntry(String name, String global, String location) {
            this.name = name;
            this.global = global;
            this.location = location;
        }

        public String getLocation() {
            return location;
        }

        public String getGlobal() {
            return global;
        }

        public String getName() {
            return name;
        }

        public boolean updatable() {
            return !new File(location + File.separator + name).exists();
        }

        public boolean download() {
            BufferedInputStream in = null;
            FileOutputStream fout = null;
            try {
                in = new BufferedInputStream(new URL(global).openStream());
                fout = new FileOutputStream(new File(location + File.separator + name));
                byte data[] = new byte[1024];
                int count;
                while ((count = in.read(data, 0, 1024)) != -1) {
                    fout.write(data, 0, count);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } finally {
                try {
                    if (in != null)
                        in.close();
                    if (fout != null)
                        fout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            return true;
        }

        public File load() {
            return updatable() ? null : new File(location + File.separator + name);
        }
    }
}
