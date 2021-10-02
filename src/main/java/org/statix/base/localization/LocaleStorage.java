package org.statix.base.localization;

import lombok.experimental.UtilityClass;

import java.io.IOException;

@UtilityClass
public class LocaleStorage {

    /**
     * "ru" и "en" это имена yml файлов с локализацией в репозитории
     * github. Управление тем локализацию из какого репозитория будет прать API
     * можно посмотреть в {@link Locale}
     */
    static Locale RU_LOCALE = new Locale("ru");
    static Locale EN_LOCALE = new Locale("en");

    /**
     * Обновление локализаций
     */
    public static void updateLocales() {
        for (Language language : Language.values()) {
            try {
                language.getLocale().loadFromSite();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
