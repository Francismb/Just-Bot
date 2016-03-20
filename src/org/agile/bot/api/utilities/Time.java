package org.agile.bot.api.utilities;

/**
 * A utility for manipulating time.
 *
 * @author Timer
 */
public class Time {

    public static void sleep(final int time) {
        try {
            final long start = System.currentTimeMillis();
            Thread.sleep(time);
            long now;
            while (start + time > (now = System.currentTimeMillis())) {
                Thread.sleep(start + time - now);
            }
        } catch (final InterruptedException ignored) {
        }
    }

    public static void sleep(final int min, final int max) {
        sleep(Random.nextInt(min, max));
    }

    public static boolean sleep(final int min, final int max, final Condition condition) {
        return sleep(Random.nextInt(min, max), condition);
    }

    public static boolean sleep(final int time, final Condition condition) {
        final Timer timer = new Timer(time) {{
            reset();
        }};
        while (timer.isRunning() && !condition.valid()) {
            Time.sleep(1);
        }
        return condition.valid();
    }

    public static String format(final long time) {
        final StringBuilder t = new StringBuilder();
        final long total_secs = time / 1000;
        final long total_mins = total_secs / 60;
        final long total_hrs = total_mins / 60;
        final int secs = (int) total_secs % 60;
        final int mins = (int) total_mins % 60;
        final int hrs = (int) total_hrs % 24;
        if (hrs < 10) {
            t.append("0");
        }
        t.append(hrs);
        t.append(":");
        if (mins < 10) {
            t.append("0");
        }
        t.append(mins);
        t.append(":");
        if (secs < 10) {
            t.append("0");
        }
        t.append(secs);
        return t.toString();
    }

    public static interface Condition {
        public boolean valid();
    }
}
