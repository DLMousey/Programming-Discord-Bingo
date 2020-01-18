package com.deadlyllama.discordbingo;

import com.deadlyllama.discordbingo.services.GameButtonsService;
import com.deadlyllama.discordbingo.services.StringValuesService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

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
    protected GameButtonsService gameButtonsService = new GameButtonsService(this.stringValuesService);

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
        this.gameButtonsService.initialiseLabels();
        this.gameButtonsService.initialiseGameButtons();

        ArrayList<ToggleButton> buttons = gameButtonsService.getGameButtons();

        Double longestWidth = 0d;

        for(ToggleButton button : buttons) {
            button.setOnAction(this::onButtonClick);
        }

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

    private void onButtonClick(ActionEvent event) {
        ToggleButton button = (ToggleButton) event.getSource();
        if (button.isSelected()) {
            this.gameButtonsService.incrementSelectedItems();
        } else {
            this.gameButtonsService.decrementSelectedItems();
        }

        HBox parentHbox = (HBox) button.getParent();

        System.out.println(button.getText() + " was clicked, selected? : " + button.isSelected());
        System.out.println("Button parent: " + parentHbox.getId() + " pos: " + parentHbox.getChildren().indexOf(button));
        System.out.println("Selected items: " + this.gameButtonsService.getSelectedItems());
    }
}
