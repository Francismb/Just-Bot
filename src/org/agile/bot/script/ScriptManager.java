package org.agile.bot.script;

import org.agile.bot.randoms.RandomEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Francis
 * Date: 28/03/13
 * Time: 5:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class ScriptManager implements Runnable {

    public final Script script;
    public final List<ScriptStates> actives = new ArrayList<>();
    public RandomEvent random = null;

    public ScriptManager(final Script script) {
        this.script = script;
    }

    @Override
    public void run() {
        if (!script.init()) {
            stop();
        }
        while (!actives.contains(ScriptStates.STOPED)) {
            if (!actives.contains(ScriptStates.PAUSED)) {
                if ((random = getRandom()) != null) {
                    random.run();
                } else {
                    try {
                        final int loop = script.run();
                        if (loop < 0) {
                            stop();
                        }
                        Thread.sleep(loop);
                    } catch (final Throwable e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void stop() {
        if (!actives.contains(ScriptStates.STOPED)) actives.add(ScriptStates.STOPED);
        script.stop();
    }

    public void pause() {
        if (!actives.contains(ScriptStates.PAUSED)) actives.add(ScriptStates.PAUSED);
    }

    public void play() {
        if (actives.contains(ScriptStates.PAUSED)) actives.remove(ScriptStates.PAUSED);
        if (!actives.contains(ScriptStates.RUNNING)) actives.add(ScriptStates.RUNNING);
    }

    public boolean runnable() {
        return !actives.contains(ScriptStates.STOPED) && !actives.contains(ScriptStates.PAUSED);
    }

    public RandomEvent getRandom() {
        for (final RandomEvent random : RandomEvent.randoms) if (random.valid()) return random;
        return null;
    }

}
