package br.edu.fatecfranca.basketballapi.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static String localDateTimeToString(LocalDateTime localDateTime, String format) {
        var formatter = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(formatter);
    }

}
