package com.deadlyllama.discordbingo.services;

import java.util.ArrayList;

public class StringValuesService {

    protected static ArrayList<String> options = new ArrayList<String>();

    public ArrayList<String> getOptions() {
        return options;
    }

    public Boolean addOption(String option) {
        return options.add(option);
    }

    public Boolean removeOption(String option) {
        int index = options.indexOf(option);

        if (index != -1) {
            options.remove(index);
            return true;
        }

        return false;
    }
}
