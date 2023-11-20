package com.JorzonWeb.Proyecto.util;

import java.util.Locale;

public class LocaleUtil {

    public static boolean checkDefaultLocale(Locale locale){
        return locale.getLanguage().equals("es");
    }
}
