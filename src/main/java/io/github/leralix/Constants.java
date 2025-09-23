package io.github.leralix;

import io.github.leralix.lang.LangType;
import org.bukkit.configuration.file.FileConfiguration;

public class Constants {

    private static LangType serverLang;

    private static int mainChannelId = -1;

    public static void init(FileConfiguration config) {

        serverLang = LangType.fromCode(config.getString("language", "en"));

        mainChannelId = config.getInt("mainChannelId", -1);

    }

    public static int getMainChannelId() {
        return mainChannelId;
    }

    public static LangType getServerLang() {
        return serverLang;
    }
}
