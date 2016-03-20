package org.agile.loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Francis
 * Date: 2/12/12
 * Time: 6:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class SessionLoader {

    public HashMap<String, String> parameters = new HashMap<>();
    public String gamePack = null;
    public String baseLink = null;
    private final int[] invalid = new int[]{7, 15, 23, 24, 31, 32, 39, 40, 47, 48, 55, 56, 63, 64, 71, 72};

    public boolean accept() {
        System.out.println("^ - Grabbing Session Data");
        final String source = getPageSource(getWorldUrl() + "/j1");
        if (source != null && source.length() > 0) {
            Pattern pattern = Pattern.compile("archive=(.*) ");
            Matcher matcher = pattern.matcher(source);
            if (matcher.find()) {
                gamePack = matcher.group(1).split(" '")[0];
            }
            pattern = Pattern.compile("<param name=\"([^\\s]+)\"\\s+value=\"([^>]*)\">");
            matcher = pattern.matcher(source);
            while (matcher.find()) {
                parameters.put(matcher.group(1), matcher.group(2));
            }
            if (parameters.get("haveie6") != null) {
                parameters.put("haveie6", "false");
            }
            if (gamePack != null && parameters.size() > 0) {
                System.out.println("^ - Grabbed GamePack URL And " + parameters.size() + " Parameters");
                return true;
            }
        } else {
            throw new RuntimeException("Couldn't Load Source");
        }
        throw new RuntimeException("Couldn't Identify GamePack Or Parameters");
    }

    private String getPageSource(String URL) {
        try {
            final HttpURLConnection connection = (HttpURLConnection) new URL(URL).openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 ( ; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)");
            connection.setRequestProperty("Referer", "http://oldschool.runescape.com/slu");
            final BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            final StringBuffer sb = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            return sb.toString();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }

    public String getWorldUrl() {
        if (baseLink == null) {
            baseLink = getNewBaseLink();
        }
        return baseLink;
    }

    public URL getGamePackUrl() {
        if (gamePack == null) {
            if (!accept()) return null;
        }
        try {
            return new URL(getWorldUrl() + "/" + gamePack);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getNewBaseLink() {
        int world = 0;
        while (world == 0) {
            world = new Random().nextInt(77);
            for (int id : invalid) {
                if (world == id) {
                    world = 0;
                }
            }
        }
        return "http://oldschool" + world + ".runescape.com";
    }
}
