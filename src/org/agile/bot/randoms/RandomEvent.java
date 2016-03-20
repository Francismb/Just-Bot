package org.agile.bot.randoms;

import java.awt.*;

/**
 * User: Francis(AgileTM)
 * Date: 17/08/13
 * Time: 1:02 PM
 * Project: Client
 * Package: org.agile.bot.randoms
 */
public interface RandomEvent {

    public static RandomEvent[] randoms = new RandomEvent[]{
            new ModelCacheRandom()
    };

    public boolean valid();
    public void run();
    public void paint(final Graphics g);
}
