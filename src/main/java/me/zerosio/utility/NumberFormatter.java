package me.zerosio.utility;

import java.text.DecimalFormat;

public class NumberFormatter {

    private static final DecimalFormat commaFormat = new DecimalFormat("#,###");
    private static final DecimalFormat suffixFormat = new DecimalFormat("0.#");

    public static String commafy(double value) {
        return commaFormat.format(value);
    }

    public static String suffix(double value) {
        if (value >= 1_000_000_000) {
            return suffixFormat.format(value / 1_000_000_000D) + "b";
        } else if (value >= 1_000_000) {
            return suffixFormat.format(value / 1_000_000D) + "m";
        } else if (value >= 1_000) {
            return suffixFormat.format(value / 1_000D) + "k";
        } else {
            return suffixFormat.format(value);
        }
    }
}
