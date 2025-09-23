package io.github.leralix;

import io.github.leralix.lang.LangType;
import org.bukkit.configuration.file.FileConfiguration;

import java.awt.*;

public class Constants {

    private static LangType serverLang;

    private static int mainChannelId;
    private static Color embededColor;

    public static void init(FileConfiguration config) {

        serverLang = LangType.fromCode(config.getString("language", "en"));

        mainChannelId = config.getInt("mainChannelId", -1);

        embededColor = Color.decode(config.getString("embededColor", "#023E8A"));
    }

    public static int getMainChannelId() {
        return mainChannelId;
    }

    public static LangType getServerLang() {
        return serverLang;
    }

    public static Color getEmbededColor() {
        return embededColor;
    }
}
