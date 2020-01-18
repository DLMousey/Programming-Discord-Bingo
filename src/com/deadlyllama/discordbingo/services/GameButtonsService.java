package com.deadlyllama.discordbingo.services;

import javafx.geometry.Insets;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.util.ArrayList;

public class GameButtonsService {

    private ArrayList<String> buttonLabels;

    private ArrayList<ToggleButton> gameButtons;

    private StringValuesService stringValuesService;

    public GameButtonsService(StringValuesService stringValuesService) {
        this.stringValuesService = stringValuesService;
    }

    public void initialiseLabels() {
        this.buttonLabels = stringValuesService.getGameOptions();
    }

    public void initialiseGameButtons() {
        if (this.buttonLabels == null || this.buttonLabels.size() == 0) {
            this.initialiseLabels();
        }

        this.gameButtons = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            ToggleButton gameButton = new ToggleButton();
            gameButton.setText(buttonLabels.get(i));
            gameButton.setMaxWidth(Double.MAX_VALUE);
            gameButton.setPadding(new Insets(10, 10, 10, 10));
            HBox.setHgrow(gameButton, Priority.ALWAYS);

            this.gameButtons.add(gameButton);
        }
    }

    public ArrayList<ToggleButton> getGameButtons() {
        return this.gameButtons;
    }

}
