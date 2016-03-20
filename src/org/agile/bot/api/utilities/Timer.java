package org.agile.bot.api.utilities;


public class Timer {
    private long end;
    private final long start;
    private final long period;


    public Timer(final long period) {
        this.period = period;
        start = System.currentTimeMillis();
        end = start + period;
    }

    public long getElapsed() {
        return System.currentTimeMillis() - start;
    }

    public long getRemaining() {
        if (isRunning()) {
            return end - System.currentTimeMillis();
        }
        return 0;
    }

    public boolean isRunning() {
        return System.currentTimeMillis() < end;
    }

    public void reset() {
        end = System.currentTimeMillis() + period;
    }

    public long setEndIn(final long ms) {
        end = System.currentTimeMillis() + ms;
        return end;
    }

    public String toElapsedString() {
        return Time.format(getElapsed());
    }

    public String toRemainingString() {
        return Time.format(getRemaining());
    }
}