package com.example.food_project.util;

import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import static org.springframework.util.StringUtils.hasText;

@Component
public class StringUtil {

    public String lowerCase(String s) {
        return hasText(s) ? s.toLowerCase() : s;
    }

    public String removeSpCharsBeginAndEnd(String s) {
        return hasText(s) ? s.replaceAll("^[^a-zA-Z0-9]+|[^a-zA-Z0-9]+$", "") : s;
    }

    public String removeNumAndWhiteSpaceBeginAndEnd(String s) {
        return hasText(s) ? s.replaceAll("^[0-9\\s]+|[0-9\\s]+$", "") : s;
    }


    public String removeNumAndWhiteSpaceEnd(String s) {
        return hasText(s) ? s.replaceAll("[0-9\\s]+$", "") : s;
    }
    public String removeWhiteSpaceBeginAndEnd(String s) {
        return hasText(s) ? s.replaceAll("^\\s+|\\s+$", "") : s;
    }

    public String parseName(String s) {
        return hasText(s) ? titleCase(replaceMultiBySingleWhitespace(removeNumAndWhiteSpaceBeginAndEnd(s))) : s;
    }

    public String replaceMultiBySingleWhitespace(String s) {
        return hasText(s) ? s.replaceAll("\\s+", " ") : s;
    }

    public String titleCase(String s) {
        return hasText(s) ? StringUtils.capitalizeWords(s.toLowerCase()) : s;
    }

    public String parseEmail(String s) {
        return hasText(s) ? lowerCase(removeSpCharsBeginAndEnd(removeNumAndWhiteSpaceEnd(s))) : s;
    }
}
