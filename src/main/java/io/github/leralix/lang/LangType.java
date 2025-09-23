package io.github.leralix.lang;

import java.util.HashMap;
import java.util.Map;

public enum LangType {

    AFRIKAANS("af"),
    ARABIC("ar"),
    CATALAN("ca"),
    CZECH("cs"),
    DANISH("da"),
    GERMAN("de"),
    GREEK("el"),
    ENGLISH("en"),
    SPANISH("es-ES"),
    FINNISH("fi"),
    FRENCH("fr"),
    HEBREW("he"),
    HUNGARIAN("hu"),
    ITALIAN("it"),
    JAPANESE("ja"),
    KOREAN("ko"),
    DUTCH("nl"),
    NORWEGIAN("no"),
    POLISH("pl"),
    PORTUGUESE("pt-PT"),
    PORTUGUESE_BRAZIL("pt-BR"),
    VIETNAMESE("vi"),
    ROMANIAN("ro"),
    RUSSIAN("ru"),
    SERBIAN("sr"),
    SWEDISH("sv-SE"),
    TURKISH("tr"),
    UKRAINIAN("uk"),
    CHINESE_SIMPLIFIED("zh-CN"),
    CHINESE_TRADITIONAL("zh-TW");

    private static final Map<String, LangType> CODE_MAP = new HashMap<>();

    static {
        for (LangType lang : values()) {
            CODE_MAP.put(lang.code, lang);
        }
    }

    private final String code;

    LangType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static LangType fromCode(String code) {
        return CODE_MAP.get(code.toLowerCase());
    }

    public String getName() {
        return name().toLowerCase();
    }


}
