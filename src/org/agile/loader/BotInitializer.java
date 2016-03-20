package org.agile.loader;

import org.agile.bot.Global;
import org.agile.injection.Injector;
import org.agile.loader.applet.CustomAppletLoader;
import org.agile.reflection.loader.ByteClassLoader;
import org.agile.reflection.loader.IdentityLoader;
import org.agile.reflection.storage.ClassStorage;
import org.agile.ui.BotPanel;
import org.agile.ui.MainFrame;

import java.util.Map;

/**
 * User: Francis(AgileTM)
 * Date: 8/08/13
 * Time: 5:11 PM
 * Project: Client
 * Package: org.agile.loader
 */
public class BotInitializer implements Runnable {

    @Override
    public void run() {
        System.out.println("<<< Initial Setup >>>");
        MainFrame.submitStatus("Loading Game...");
        final SessionLoader session = new SessionLoader();
        if (session.accept()) {
            final Map<String, byte[]> content = GamePackLoader.extract(GamePackLoader.getGamePackBytes(session.getGamePackUrl()));
            if (content.size() > 0) {
                System.out.println("\n<<< Client Started >>>");
                IdentityLoader.load();
                final ByteClassLoader loader = new ByteClassLoader(new Injector(content).inject());
                ClassStorage.setClassLoader(loader);
                {
                    System.out.println("\n<<< Applet Setup >>>");
                    Global.applet = new CustomAppletLoader(session);
                    Global.applet.init();
                    Global.applet.start();
                    Global.applet.setLocation(0, 0);
                    Global.applet.setSize(765, 503);
                    Global.applet.setVisible(true);
                    BotPanel.get().removeAll();
                }
            }
        }
    }

}
