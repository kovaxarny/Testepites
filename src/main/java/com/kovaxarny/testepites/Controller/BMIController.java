package com.kovaxarny.testepites.Controller;

import com.kovaxarny.testepites.Model.BMIData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.pmw.tinylog.Logger;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BMIController {
    @FXML
    private TextField ageTextField;
    @FXML
    private TextField heightTextField;
    @FXML
    private TextField weightTextField;
    @FXML
    private ComboBox<String> genderComboBox;
    @FXML
    private Button quitButton;
    @FXML
    private Button nextButton;
    @FXML
    private BorderPane bmiBorderPane;

    /**
     * Formatter for double number formatting.
     */
    private final DecimalFormat df = new DecimalFormat("#.00");

    @FXML
    public void initialize() {
        nextButton.setDisable(true);
    }

    @FXML
    public void onNextButtonClicked() {
        try {
            Integer age = Integer.parseInt(ageTextField.getText());
            Double height = Double.parseDouble(heightTextField.getText());
            Double weight = Double.parseDouble(weightTextField.getText());
            BMIData user = new BMIData(genderComboBox.getValue(), age, height, weight);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Your BMI was calculated.");
            alert.setHeaderText("Your BMI is " + df.format(user.getBMI()) +
                    " and your Basal Metabolic Rate is " + df.format(user.getBMR()) + " calories/day" +
                    "\nThis means that you are " + user.getInformation() +
                    " and I suggest you to " + user.getSuggestion() + " exercises.");
            alert.setContentText("Please choose carefully: ");

            ButtonType cardio = new ButtonType("Cardio");
            ButtonType bulking = new ButtonType("Bulking");
            ButtonType quit = new ButtonType("Quit");
            alert.getButtonTypes().setAll(cardio, bulking, quit);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == cardio) {
                Logger.info("Cardio was chosen");
                setUpExerciseScene(true);
            } else if (result.get() == bulking) {
                setUpExerciseScene(false);
                Logger.info("Bulking was chosen");
            } else {
                onQuitButtonClicked();
            }

        } catch (NumberFormatException e) {
            Logger.warn("Invalid input");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Something went wrong :(");
            alert.setHeaderText(null);
            alert.setContentText("Please enter numbers only!");
            alert.showAndWait();
        }
    }

    @FXML
    private void onQuitButtonClicked() {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleKeyReleased() {
        boolean disableButtons = ageTextField.getText().isEmpty() || ageTextField.getText().trim().isEmpty() ||
                heightTextField.getText().isEmpty() || heightTextField.getText().trim().isEmpty() ||
                weightTextField.getText().isEmpty() || weightTextField.getText().trim().isEmpty();
        nextButton.setDisable(disableButtons);
    }

    @FXML
    private void setUpExerciseScene(boolean isItCardio) {

        if (isItCardio) {
            List<String> choices = new ArrayList<>();
            choices.add("Yes");
            choices.add("No");

            ChoiceDialog<String> dialog = new ChoiceDialog<>("Yes", choices);
            dialog.setTitle("");
            dialog.setHeaderText("Are you going to exercise in a gym?");
            dialog.setContentText("Choose your answer:");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                List<String> muscles = new ArrayList<>(Collections.singletonList("Cardio"));
                if (result.get().equals("Yes")) {
                    loadExerciseScene(muscles, "IS NOT NULL");
                } else {
                    loadExerciseScene(muscles, "IS NULL");
                }
            } else {
                BorderPane pane;
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/BMIWindow.fxml"));

                try {
                    pane = fxmlLoader.load();
                    bmiBorderPane.getChildren().setAll(pane);
                } catch (IOException e) {
                    Logger.error("Unable to load fxml file.");
                    e.printStackTrace();
                }
            }
        } else {
            loadChooseScene();
        }
    }

    @FXML
    private void loadChooseScene() {
        BorderPane pane;
        Stage stage = new Stage();
        stage.initOwner(bmiBorderPane.getScene().getWindow());

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/ChooseWindow.fxml"));

        try {
            pane = fxmlLoader.load();
            bmiBorderPane.getChildren().setAll(pane);
        } catch (IOException e) {
            Logger.error("Unable to load fxml file.");
            e.printStackTrace();
        }
    }

    @FXML
    private void loadExerciseScene(List<String> muscle, String equipment) {
        BorderPane pane;
        Stage stage = new Stage();
        stage.initOwner(bmiBorderPane.getScene().getWindow());

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/ExerciseWin.fxml"));

        try {
            pane = fxmlLoader.load();
            ExerciseController controller = fxmlLoader.getController();
            controller.queryExercises(muscle, equipment, "Cardio");
            bmiBorderPane.getChildren().setAll(pane);
        } catch (IOException e) {
            Logger.error("Unable to load fxml file.");
            e.printStackTrace();
        }
    }
}
