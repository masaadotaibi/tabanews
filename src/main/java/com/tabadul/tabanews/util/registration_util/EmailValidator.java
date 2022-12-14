package com.tabadul.tabanews.util.registration_util;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmailValidator implements Predicate<String> {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+\n" +
            "        (.[_A-Za-z0-9-]+)*@\" + \"[A-Za-z0-9-]+(.[A-Za-z0-9]+)*\n" +
            "        (.[A-Za-z]{2,})$";

    @Override
    public boolean test(String email) {


        Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

//        return matcher.find();
        return true;
    }
}
