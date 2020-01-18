package com.deadlyllama.discordbingo.services;

import java.util.ArrayList;
import java.util.Random;

public class StringValuesService {

    protected static ArrayList<String> options = new ArrayList<String>();

    public ArrayList<String> getOptions() {
        return options;
    }

    public ArrayList<String> getGameOptions() {
        ArrayList<String> gameOptions = new ArrayList<>();
        for (int i = 0; i <= 16; i++) {
            addRandomValue(gameOptions);
        }

        return gameOptions;
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

    private void addRandomValue(ArrayList<String> gameOptions) {
        Random r = new Random();
        Integer randomInt = r.nextInt(options.size() -1) + 1;
        String randomValue = options.get(randomInt);

        if (gameOptions.indexOf(randomValue) == -1) {
            gameOptions.add(randomValue);
        } else {
            addRandomValue(gameOptions);
        }
    }
}
