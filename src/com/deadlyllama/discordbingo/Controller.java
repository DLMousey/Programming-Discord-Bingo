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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private Map<Integer, List<Integer>> selectedMap = new HashMap<>();
    private HashMap<Integer, Boolean> selectedMatches = new HashMap<>();

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
        selectedMatches.clear();
        selectedMap.clear();

        this.gameButtonsService.initialiseLabels();
        this.gameButtonsService.initialiseGameButtons();

        selectedMap.put(0, new ArrayList<>());
        selectedMap.put(1, new ArrayList<>());
        selectedMap.put(2, new ArrayList<>());
        selectedMap.put(3, new ArrayList<>());

        selectedMatches.put(0, false);
        selectedMatches.put(1, false);
        selectedMatches.put(2, false);
        selectedMatches.put(3, false);

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
        HBox parentHbox = (HBox) button.getParent();

        Integer row;
        Integer column = parentHbox.getChildren().indexOf(button);

        switch(parentHbox.getId()) {
            case "rowFirst_hbox":
                row = 0;
                break;
            case "rowSecond_hbox":
                row = 1;
                break;
            case "rowThird_hbox":
                row = 2;
                break;
            case "rowFourth_hbox":
                row = 3;
                break;
            default:
                row = null;
                break;
        }

        if (row == null) {
            throw new RuntimeException("Unable to determine row index");
        }

        List<Integer> values = selectedMap.get(row);

        if (button.isSelected()) {
            this.gameButtonsService.incrementSelectedItems();
            values.add(column);
        } else {
            this.gameButtonsService.decrementSelectedItems();
            values.remove(column);
        }

        System.out.println(button.getText() + " was clicked, selected? : " + button.isSelected());
        for (int i = 0; i < 4; i++) {
            List<Integer> rowValues = selectedMap.get(i);
            if (rowValues.size() == 4) {
                System.out.println("BINGO! ROW VICTORY");
            }
        }

        if (row == 0) {
            selectedMatches.put(0, button.isSelected());
            for (int i = 1; i < 4; i++) {
                List<Integer> rowValues = selectedMap.get(i);
                if (rowValues.indexOf(column) == -1) {
                    selectedMatches.put(i, false);
                } else {
                    selectedMatches.put(i, true);
                }
            }
        } else if (row > 0 && row < 3) {
            Integer remainingIterations = 4 - row;
            selectedMatches.put(column, button.isSelected());
            for (int i = column; i < remainingIterations; i++) {
                List<Integer> rowValues = selectedMap.get(i);
                Integer contains = rowValues.indexOf(column);

                if (rowValues.indexOf(column) == -1) {
                    selectedMatches.put(i, false);
                } else {
                    selectedMatches.put(i, true);
                }

                remainingIterations--;
            }

            // Go back to zero
            // Work up until we reach the row that contains the selected button
            for (int i = 0; i <= row; i++) {
                List<Integer> rowValues = selectedMap.get(i);
                if (rowValues.indexOf(column) == -1) {
                    selectedMatches.put(i, false);
                } else {
                    selectedMatches.put(i, true);
                }
            }
        } else {
            selectedMatches.put(3, button.isSelected());
        }

        if (selectedMatches.get(0) && selectedMatches.get(1) && selectedMatches.get(2) && selectedMatches.get(3)) {
            System.out.println("BINGO! COLUMN VICTORY!");
        }
    }
}
