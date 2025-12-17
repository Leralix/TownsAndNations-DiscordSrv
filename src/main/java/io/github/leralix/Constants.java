package io.github.leralix;

import io.github.leralix.lang.LangType;
import org.bukkit.configuration.file.FileConfiguration;

import java.awt.*;

public class Constants {

    private static LangType serverLang;

    private static String mainChannelName;
    private static Color embededColor;

    public static void init(FileConfiguration config) {

        serverLang = LangType.fromCode(config.getString("language", "en"));

        mainChannelName = config.getString("broadcastChannel", "");

        embededColor = Color.decode(config.getString("embededColor", "#023E8A"));
    }

    public static String getMainChannelName() {
        return mainChannelName;
    }

    public static LangType getServerLang() {
        return serverLang;
    }

    public static Color getEmbededColor() {
        return embededColor;
    }
}
