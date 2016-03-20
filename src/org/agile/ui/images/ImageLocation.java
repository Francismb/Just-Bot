package org.agile.ui.images;

import org.agile.Configuration;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Francis(AgileTM)
 * Date: 15/04/13
 * Time: 8:48 PM
 */
public enum ImageLocation {

    PlayImage("images/PlayImage.png"),
    PlayHoverImage("images/PlayHoverImage.png"),
    PauseImage("images/PauseImage.png"),
    PauseHoverImage("images/PauseHoverImage.png"),
    StopImage("images/StopImage.png"),
    StopHoverImage("images/StopHoverImage.png"),
    RestartImage("images/RestartImage.png"),
    RestartHoverImage("images/RestartHoverImage.png"),
    SettingImage("images/SettingImage.png"),
    SettingHoverImage("images/SettingHoverImage.png"),
    LogoImage("images/Logo.png"),
    KeyboardImage("images/KeyboardImage.png"),
    KeyboardHoverImage("images/KeyboardHoverImage.png"),
    CloseImage("images/CloseImage.png"),
    CloseHoverImage("images/CloseHoverImage.png");

    private final String location;

    private ImageLocation(final String location) {
        this.location = location;
    }

    public String getLocation() {
        return Configuration.home + location.replace("/", File.separator).toLowerCase();
    }
}
