package io.github.leralix.lang;

import io.github.leralix.TownsAndNationsDiscordSrv;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.leralix.lib.utils.config.ConfigUtil;

import java.io.File;
import java.util.EnumMap;

public enum Lang {
    ATTACK_DECLARED_TITLE,
    ATTACK_DECLARED,
    ATTACK_WON_BY_ATTACKER_TITLE,
    ATTACK_WON_BY_ATTACKER,
    ATTACK_WON_BY_DEFENDER_TITLE,
    ATTACK_WON_BY_DEFENDER,
    TERRITORY_SURRENDER_TITLE,
    TERRITORY_SURRENDER,
    NEWSLETTER_DIPLOMACY_PROPOSAL_ACCEPTED_TITLE,
    NEWSLETTER_DIPLOMACY_PROPOSAL_ACCEPTED,
    NEWSLETTER_DIPLOMACY_PROPOSAL_TITLE,
    NEWSLETTER_DIPLOMACY_PROPOSAL,
    NEWSLETTER_PLAYER_APPLICATION_TITLE,
    NEWSLETTER_PLAYER_APPLICATION,
    REGION_CREATED_NEWSLETTER_TITLE,
    REGION_CREATED_NEWSLETTER,
    REGION_DELETED_NEWSLETTER_TITLE,
    REGION_DELETED_NEWSLETTER,
    TOWN_INDEPENDENCE_NEWSLETTER_TITLE,
    TOWN_INDEPENDENCE_NEWSLETTER,
    TOWN_LEAVE_REGION_NEWSLETTER_TITLE,
    TOWN_LEAVE_REGION_NEWSLETTER,
    TOWN_JOIN_REGION_ACCEPTED_NEWSLETTER_TITLE,
    TOWN_JOIN_REGION_ACCEPTED_NEWSLETTER,
    FORCED_VASSALAGE_NEWSLETTER_TITLE,
    FORCED_VASSALAGE_NEWSLETTER,
    NEWSLETTER_JOIN_REGION_PROPOSAL_TITLE,
    NEWSLETTER_JOIN_REGION_PROPOSAL,
    TOWN_CREATED_NEWSLETTER_TITLE,
    TOWN_CREATED_NEWSLETTER,
    TOWN_DELETED_NEWSLETTER_TITLE,
    TOWN_DELETED_NEWSLETTER,
    PLAYER_JOINED_TOWN_NEWSLETTER_TITLE,
    PLAYER_JOINED_TOWN_NEWSLETTER,
    LANDMARK_CLAIMED_NEWSLETTER_TITLE,
    LANDMARK_CLAIMED_NEWSLETTER,
    LANDMARK_UNCLAIMED_NEWSLETTER_TITLE,
    LANDMARK_UNCLAIMED_NEWSLETTER;

    private static LangType serverLang;

    private static final EnumMap<Lang, String> translations = new EnumMap<>(Lang.class);

    static final String MESSAGE_NOT_FOUND_FOR = "Message not found for ";
    static final String IN_THIS_LANGUAGE_FILE = " in this language file.";

    public static void loadTranslations(File langFolder, LangType mainLangType) {

        serverLang = mainLangType;

        if (!langFolder.exists()) {
            langFolder.mkdir();
        }

        for(LangType langType : LangType.values()) {

            File specificLangFolder = new File(langFolder, langType.getCode());
            if(!specificLangFolder.exists()) {
                specificLangFolder.mkdir();
            }

            File file = new File(specificLangFolder, "main.yml");
            ConfigUtil.saveAndUpdateResource(TownsAndNationsDiscordSrv.getInstance(), "lang/" + langType.getCode() + "/main.yml");

            if(langType.equals(serverLang)){
                YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

                for (Lang key : values()) {

                    String message = config.getString("language." + key.name());
                    if (message != null) {
                        translations.put(key, message);
                    }
                }
            }
        }
    }

    public String get() {
        return get(serverLang);
    }

    private String get(LangType lang) {
        String translation = translations.get(this);
        if (translation != null) {
            return ChatColor.translateAlternateColorCodes('§', translation);
        }
        if(lang == LangType.ENGLISH) {
            return MESSAGE_NOT_FOUND_FOR + this.name() + IN_THIS_LANGUAGE_FILE;
        }
        return get(LangType.ENGLISH);
    }

    public String get(Object... placeholders) {
        return get(serverLang, placeholders);
    }

    public String get(LangType lang, Object... placeholders) {
        String translation = translations.get(this);
        if (translation != null) {
            translation = ChatColor.translateAlternateColorCodes('§', translation);
            for (int i = 0; i < placeholders.length; i++) {
                String val = placeholders[i] == null ? "null" : placeholders[i].toString();
                translation = translation.replace("{" + i + "}",val);
            }
            return translation;
        }
        if(lang == LangType.ENGLISH) {
            return MESSAGE_NOT_FOUND_FOR + this.name() + IN_THIS_LANGUAGE_FILE;
        }
        return get(LangType.ENGLISH, placeholders);
    }


}
