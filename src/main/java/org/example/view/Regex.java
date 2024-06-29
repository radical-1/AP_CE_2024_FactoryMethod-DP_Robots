package org.example.view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Regex {
    CREATE_SECURITY_ROBOT("create security robot (?<id>\\d+) (?<power>\\d+) (?<live>\\d+)"),
    CREATE_DELIVERY_ROBOT("create delivery robot (?<id>\\d+) (?<vehicle>\\w+)"),
    CREATE_CLEANING_ROBOT("create cleaning robot (?<id>\\d+) (?<numTask>\\d+) (?<areas>(\\d+\\s*)+)"),
    DELIVER("deliver robot (?<id>\\d+)"),
    CLEAN("clean robot (?<id>\\d+) (?<area>\\d+)"),
    PERFORM("perform task robot (?<id>\\d+)"),
    ATTACK("attack robot (?<id>\\d+) (?<enemyPower>\\d+)"),
    GET_ALL("get all robots"),
    END("end");
    private final String regex;

    Regex(String regex) {
        this.regex = regex;
    }

    private Matcher getMather(String input) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        matcher.matches();
        return matcher;
    }

    public boolean isMatch(String input) {
        return getMather(input).matches();
    }

    public boolean isFind(String input) {
        return getMather(input).find();
    }

    public String getGroup(String input, String group) {
        return getMather(input).group(group);
    }
}
