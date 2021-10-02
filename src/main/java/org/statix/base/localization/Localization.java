package org.statix.base.localization;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@AllArgsConstructor
@Deprecated
public enum Localization {
    /**
     * Список доступных языков
     */
    RUSSIAN(0, "Русский", "§a100%", LocaleStorage.RU_LOCALE, "RUSSIA"),
    ENGLISH(1, "English", "§c~95%", LocaleStorage.EN_LOCALE, "ENGLISH");

    private final int id;
    private final String name;
    private final String percent;
    private final Locale locale;
    private final String headName;

    private static final Map<Integer, Localization> LOCALES = new ConcurrentHashMap<>();
    private static final Localization DEFAULT = RUSSIAN;
    private static final String ERROR = "§cКлюч локализации не найден!";

    @Deprecated
    public static void add(String key, String message) {
        RUSSIAN.getLocale().getMessages().putIfAbsent(key, message);
    }
    @Deprecated
    public static void add(String key, List<String> message) {
        RUSSIAN.getLocale().getListMessages().putIfAbsent(key, message);
    }

    public static Map<Integer, Localization> getLocales() {
        return LOCALES;
    }

    public static Localization getDefaultLocalization() {
        return DEFAULT;
    }

    public static Localization getLocale(int lang) {
        Localization localization = LOCALES.get(lang);
        if (localization == null)
            return DEFAULT;

        return localization;
    }

    /**
     * Получить строку из локализации
     *
     * @param lang - id локализации (По умолчанию - 0)
     * @param key - ключ локализации который вам нужен
     * @return - возвращаем уже полученное сообщение / ошибку о том что мы его не получили
     */
    public static String getString(int lang, String key) {
        Localization localization = getLocale(lang);
        Locale locale = localization.getLocale();
        if (locale.getMessages().containsKey(key)) {
            return locale.getMessages().get(key);
        } else {
            localization = getLocale(DEFAULT.getId());
            locale = localization.getLocale();
            String message = locale.getMessages().get(key);
            return message != null ? message : ERROR;
        }
    }

    public static String getString(int lang, String key, Object... objects) {
        Localization localization = getLocale(lang);
        Locale locale = localization.getLocale();
        if (locale.getMessages().containsKey(key)) {
            return String.format(locale.getMessages().get(key), objects);
        } else {
            localization = getLocale(DEFAULT.getId());
            locale = localization.getLocale();
            String message = locale.getMessages().get(key);
            return String.format((message != null ? message : ERROR), objects);
        }
    }

    /**
     * Получить лист из локализации
     *
     * @param lang - id локализации (По умолчанию - 0)
     * @param key - ключ локализации который вам нужен
     * @return - возвращаем уже полученный лист / ошибку о том что мы его не получили
     */
    public static List<String> getList(int lang, String key) {
        Localization localization = getLocale(lang);
        Locale locale = localization.getLocale();
        if (locale.getListMessages().containsKey(key)) {
            return locale.getListMessages().get(key);
        } else {
            localization = getLocale(DEFAULT.getId());
            locale = localization.getLocale();
            List<String> messages = locale.getListMessages().get(key);
            return (messages != null ? messages : Collections.singletonList(ERROR));
        }
    }

    public static List<String> getList(int lang, String key, Object... objects) {
        Localization localization = getLocale(lang);
        Locale locale = localization.getLocale();
        if (locale.getListMessages().containsKey(key)) {
            return format(locale.getListMessages().get(key), objects);
        } else {
            localization = getLocale(DEFAULT.getId());
            locale = localization.getLocale();
            List<String> messages = locale.getListMessages().get(key);
            return format((messages != null ? messages : Collections.singletonList(ERROR)), objects);
        }
    }

    private static List<String> format(List<String> list, Object... objects) {
        String string = String.join("±", list);
        string = String.format(string, objects);
        return Arrays.asList(string.split("±"));
    }

    static {
        for (Localization locale : values()) {
            int id = locale.getId();
            LOCALES.put(id, locale);
        }
    }
}
