package com.example.SpringTypeConverter.formatter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.format.Formatter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

@Slf4j
public class MyNumberFormatter implements Formatter<Number> {

    // 문자 -> 숫자로 변환
    @Override
    public Number parse(String text, Locale locale) throws ParseException {
        log.info("text = {}, locale = {}", text, locale);
        // "1,000" -> 1000
        NumberFormat format = NumberFormat.getInstance(locale);
        /*Number parse = format.parse(text); "1,000" -> 1000
        return parse;*/

        return format.parse(text);
    }

    // 객체 -> 문자로 변환
    @Override
    public String print(Number object, Locale locale) {
        log.info("object = {}, locale = {}", object, locale);
        return NumberFormat.getInstance(locale).format(object);
    }
}
