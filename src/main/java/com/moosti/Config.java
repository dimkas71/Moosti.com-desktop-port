package com.moosti;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by ������������ on 17.06.2015.
 */
public class Config {

    private static Config INSTANCE = null;

    public String getButtonFocusText() {
        return buttonFocusText;
    }

    public String getButtonShortBreakText() {
        return buttonShortBreakText;
    }

    public String getButtonLongBreakText() {
        return buttonLongBreakText;
    }

    public String getButtonStopText() {
        return buttonStopText;
    }

    public int getDefaultFocusInMinutes() {
        return defaultFocusInMinutes;
    }

    public int getDefaultShortBrkInMinutes() {
        return defaultShortBrkInMinutes;
    }

    public int getDefaultLongBrkInMinutes() {
        return defaultLongBrkInMinutes;
    }

    private String buttonFocusText;
    private String buttonShortBreakText;
    private String buttonLongBreakText;
    private String buttonStopText;

    private int defaultFocusInMinutes;
    private int defaultShortBrkInMinutes;
    private int defaultLongBrkInMinutes;

    private Config() {

        InputStream is = getClass().getResourceAsStream("default.properties");

        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line;

        try {
            while ((line = br.readLine()) != null) {
                if (line.trim().equals("")) continue;

                String key = line.split("=")[0];
                String value = line.split("=")[1];

                switch (key) {
                    case "focus-text":
                        buttonFocusText = value.trim();
                        break;
                    case "short-break-text":
                        buttonShortBreakText = value.trim();
                        break;
                    case "long-break-text":
                        buttonLongBreakText = value.trim();
                        break;
                    case "stop-text":
                        buttonStopText = value.trim();
                        break;
                    case "focus-in-minutes":
                        defaultFocusInMinutes = Integer.parseInt(value.trim());
                        break;
                    case "short-in-minutes":
                        defaultShortBrkInMinutes = Integer.parseInt(value.trim());
                        break;
                    case "long-in-minutes":
                        defaultLongBrkInMinutes = Integer.parseInt(value.trim());
                        break;
                    default:
                        break;
                }

            }

            br.close();
            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }






    }


    public static Config getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new Config();
        }
        return INSTANCE;
    }


}
