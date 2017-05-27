package com.kovaxarny.testepites.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.pmw.tinylog.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kovax on 5/16/2017.
 */
public class ChooseWindowController {

    @FXML
    private BorderPane chooseBorderPane;
    @FXML
    private ComboBox gymComboBox;
    @FXML
    private ComboBox mainMuscleComboBox;
    @FXML
    private CheckBox absCheckBox;
    @FXML
    private CheckBox bicepsCheckBox;
    @FXML
    private CheckBox tricepsCheckBox;
    @FXML
    private CheckBox forearmCheckBox;
    @FXML
    private CheckBox glutesCheckBox;
    @FXML
    private Button nextButton;
    @FXML
    private Button quitButton;

    /**
     * A List which contains all the checkboxes form this window.
     */
    private final List<CheckBox> checkBoxes = new ArrayList<>();

    @FXML
    public void initialize() {
        nextButton.setDisable(true);
        checkBoxes.addAll(Arrays.asList(absCheckBox, bicepsCheckBox, tricepsCheckBox, forearmCheckBox, glutesCheckBox));
    }

    @FXML
    public void handleMouseClicked() {
        boolean disableButtons = mainMuscleComboBox.getSelectionModel().isEmpty() && !absCheckBox.isSelected() && !bicepsCheckBox.isSelected() && !tricepsCheckBox.isSelected()
                && !forearmCheckBox.isSelected() && !glutesCheckBox.isSelected();
        nextButton.setDisable(disableButtons);
    }

    @FXML
    public void onNextButtonClicked() {
        List<String> muscles = new ArrayList<>();
        if (!mainMuscleComboBox.getSelectionModel().isEmpty()) {
            muscles.add(mainMuscleComboBox.getValue().toString());
        }
        for (CheckBox checkBox : checkBoxes) {
            if (checkBox.isSelected()) {
                muscles.add(checkBox.getText());
            }
        }
        if (gymComboBox.getValue().equals("Yes")) {
            loadExerciseScene(muscles, "IS NOT NULL");
        } else {
            loadExerciseScene(muscles, "IS NULL");
        }
    }

    @FXML
    public void onQuitButtonClicked() {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onBackButtonClicked() {
        BorderPane pane;
        Stage stage = new Stage();
        stage.initOwner(chooseBorderPane.getScene().getWindow());

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/BMIWindow.fxml"));

        try {
            pane = fxmlLoader.load();
            chooseBorderPane.getChildren().setAll(pane);
        } catch (IOException e) {
            Logger.error("Unable to load fxml");
            e.printStackTrace();
        }
    }

    @FXML
    private void loadExerciseScene(List<String> musclesParam, String equipmentParam) {
        BorderPane pane;
        Stage stage = new Stage();
        stage.initOwner(chooseBorderPane.getScene().getWindow());

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/ExerciseWin.fxml"));

        try {
            pane = fxmlLoader.load();
            chooseBorderPane.getChildren().setAll(pane);
            ExerciseController controller = fxmlLoader.getController();
            controller.queryExercises(musclesParam, equipmentParam, "Bulking");
        } catch (IOException e) {
            Logger.error("Unable to load fxml");
            e.printStackTrace();
        }
    }
}
