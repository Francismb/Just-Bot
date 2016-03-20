package org.agile.loader.applet;

import java.applet.AudioClip;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: Francis(AgileTM)
 * Date: 3/08/13
 * Time: 3:52 PM
 */
public class CustomAudioClip implements AudioClip {

    public static final short STATE_STOPPED = 0;
    public static final short STATE_PLAYING = 1;
    public static final short STATE_LOOPING = 2;
    private final URL sourceURL;
    private short audioClipState;

    public CustomAudioClip(final URL sourceURL) {
        this.sourceURL = sourceURL;
        audioClipState = 0;
    }

    public short getAudioClipState() {
        return audioClipState;
    }

    public URL getURL() {
        return sourceURL;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AudioClip)) {
            return false;
        }
        final CustomAudioClip ac = (CustomAudioClip) obj;
        return ac.getAudioClipState() == audioClipState && ac.getURL().equals(sourceURL);
    }

    public void play() {
        audioClipState = STATE_PLAYING;
    }

    public void loop() {
        audioClipState = STATE_LOOPING;
    }

    public void stop() {
        audioClipState = STATE_STOPPED;
    }

}
