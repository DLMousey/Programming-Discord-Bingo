package com.deadlyllama.discordbingo;

import com.deadlyllama.discordbingo.services.GameButtonsService;
import com.deadlyllama.discordbingo.services.StringValuesService;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Controller {

    @FXML
    public Button newGameButton;
    @FXML
    private HBox rowFirst_hbox;
    @FXML
    private HBox rowSecond_hbox;
    @FXML
    private HBox rowThird_hbox;
    @FXML
    private HBox rowFourth_hbox;

    protected StringValuesService stringValuesService = new StringValuesService();

    public void initialize() {
        this.initialiseGameBoard();
    }

    @FXML
    public void newGame() {
        this.rowFirst_hbox.getChildren().clear();
        this.rowSecond_hbox.getChildren().clear();
        this.rowThird_hbox.getChildren().clear();
        this.rowFourth_hbox.getChildren().clear();

        this.initialiseGameBoard();
    }

    private void initialiseGameBoard() {
        GameButtonsService gameButtonsService = new GameButtonsService(this.stringValuesService);
        gameButtonsService.initialiseLabels();
        gameButtonsService.initialiseGameButtons();

        ArrayList<ToggleButton> buttons = gameButtonsService.getGameButtons();

        Double longestWidth = 0d;
        for (int i = 0; i < buttons.size(); i++) {
            rowFirst_hbox.getChildren().add(buttons.get(i));

            if (i + 1 <= buttons.size() - 1) {
                rowSecond_hbox.getChildren().add(buttons.get(i + 1));
            }

            if (i + 2 <= buttons.size() - 1) {
                rowThird_hbox.getChildren().add(buttons.get(i + 2));
            }

            if (i + 3 <= buttons.size() - 1) {
                rowFourth_hbox.getChildren().add(buttons.get(i + 3));
            }

            if (i + 3  <= buttons.size() - 1) {
                i += 3;
            }

            if (buttons.get(i).getWidth() > longestWidth) {
                longestWidth = buttons.get(i).getWidth();
            } else {
                buttons.get(i).setPrefWidth(longestWidth);
            }
        }
    }
}
