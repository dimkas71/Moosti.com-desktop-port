package com.moosti;

/**
 * Created by ������������ on 17.06.2015.
 */
public final class Utils {

    public static String toString(String text, int minutes) {

        String converted = (text == null ? "" : text) + "(" + String.format("%d", minutes) + ")";
        return converted;
    }


    public static String fromMillis(long timeInMillis) {
        long seconds = timeInMillis / 1000;
        long minutes = seconds / 60;

        String formatted = String.format("%02d:%02d", minutes, seconds % 60);

        return formatted;
    }
}
