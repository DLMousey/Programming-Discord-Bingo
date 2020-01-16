package com.deadlyllama.discordbingo;

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

import java.util.ArrayList;

public class Controller {

    @FXML
    public Label selectedLabel;

    @FXML
    private BorderPane layout_borderPane;
    @FXML
    private HBox rowFirst_hbox;
    @FXML
    private HBox rowSecond_hbox;
    @FXML
    private HBox rowThird_hbox;
    @FXML
    private HBox rowFourth_hbox;
    @FXML
    private GridPane options_gridPane;

    protected StringValuesService stringValuesService = new StringValuesService();

    public void initialize() {
        ArrayList<String> options = stringValuesService.getOptions();

        String lastPosition = rowFirst_hbox.getId();
        String nextPosition = rowSecond_hbox.getId();

        ArrayList<ToggleButton> buttons = new ArrayList<>();
        for (String option : options) {
            ToggleButton button = new ToggleButton();
            button.setText(option);
            button.setMaxWidth(Double.MAX_VALUE);
            button.setPadding(new Insets(10, 10, 10, 10));
            button.setOnMouseClicked();
            HBox.setHgrow(button, Priority.ALWAYS);
            buttons.add(button);
        }

        Double longestWidth = 0d;
        for (int i = 0; i < buttons.size(); i++) {
            rowFirst_hbox.getChildren().add(buttons.get(i));
            String firstVal = options.get(i);
            String secVal;
            String thirVal;
            String fourVal;

            if (i + 1 <= buttons.size() - 1) {
                rowSecond_hbox.getChildren().add(buttons.get(i + 1));
                secVal = options.get(i);
            }

            if (i + 2 <= buttons.size() - 1) {
                rowThird_hbox.getChildren().add(buttons.get(i + 2));
                thirVal = options.get(i);
            }

            if (i + 3 <= buttons.size() - 1) {
                rowFourth_hbox.getChildren().add(buttons.get(i + 3));
                fourVal = options.get(i);
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


//        options.forEach((o) -> {
//            Button optionButton = new Button();
//            optionButton.setText(o);
//
//            layout_borderPane.getCenter().get
//        });
    }
}
