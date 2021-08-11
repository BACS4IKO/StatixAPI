package ru.statix.api.base.localization;

import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.statix.api.base.utility.StringUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Getter
public enum Language {
    RUSSIAN(0, "Русский", LocaleStorage.RU_LOCALE, "RUSSIA"),
    ENGLISH(1, "English", LocaleStorage.EN_LOCALE, "ENGLISH");

    private final int id;
    private final String name;
    private final Locale locale;
    private final String headName;

    /**
     * сколько % перевода сделано, незнаю зачем, но может кому-то
     * для вывода в GUI это нужно.
     *
     * @return - сколько переведено (В процентах)
     */
    public int getPercent() {
        if (this == DEFAULT) {
            return 100;
        }

        int size = this.locale.getSize();

        return StringUtil.onPercent(size, DEFAULT.getLocale().getSize());
    }

    /**
     * Получить лист из локализации
     *
     * @param key - ключ локализации (Например TEST_GUI_LIST)
     */
    public List<String> getList(String key, Object... replaced) {
        List<String> list = locale.getListMessages().get(key);

        if (list == null) {
            list = DEFAULT.locale.getListMessages().get(key);

            if (list == null) {
                list = Collections.singletonList(ERROR);
                System.out.println("[StatixBaseAPI] Не найдена локализация с ключем " + key);
                System.out.println("[StatixBaseAPI] Если вы считаете что все правильно, то");
                System.out.println("[StatixBaseAPI] Отпишите разработчику - https://vk.com/ItzStatix ");            }
        }

        if (replaced.length == 0) {
            return list;
        }

        return format(list, replaced);
    }

    /**
     * Получить строку из локализации
     *
     * @param key - ключ локализации (Например TEST_GUI_DISPLAYNAME)
     */
    public String getString(String key, Object... replaced) {
        String message = locale.getMessages().get(key);

        if (message == null) {
            message = DEFAULT.locale.getMessages().get(key);

            if (message == null) {
                message = ERROR;

                System.out.println("[StatixBaseAPI] Не найдена локализация с ключем " + key);
                System.out.println("[StatixBaseAPI] Если вы считаете что все правильно, то");
                System.out.println("[StatixBaseAPI] Отпишите разработчику - https://vk.com/ItzStatix ");


            }
        }

        if (replaced.length == 0) {
            return message;
        }

        return String.format(message, replaced);
    }

    @Override
    public String toString() {
        return "Language{name=" + name + "}";
    }

    private static List<String> format(List<String> list, Object... objects) {
        String string = String.join("±", list);
        string = String.format(string, objects);
        return Arrays.asList(string.split("±"));
    }

    private static final TIntObjectMap<Language> LOCALES = new TIntObjectHashMap<>();
    private static final Language DEFAULT = RUSSIAN; // Локализация по умолчанию
    private static final String ERROR = "§cОшибочка 0_о"; // Что будет выводиться при ошибке (Ну например если ключ локализации не найден, а GUI открывать нужно)

    /**
     * Получить локализацию по её id
     *
     * @param id - идентификатор локализации
     */
    public static Language getLanguage(int id) {
        Language language = LOCALES.get(id);
        if (language != null) {
            return language;
        }

        return DEFAULT;
    }

    /**
     * Получить локализацию по умолчанию
     *
     * @return - возвращает русскую локализацию (Ну т.к. она стоит дефолтной)
     */
    public static Language getDefault() {
        return DEFAULT;
    }

    static {
        for (Language locale : values()) {
            LOCALES.put(locale.id, locale);
        }
    }
}
