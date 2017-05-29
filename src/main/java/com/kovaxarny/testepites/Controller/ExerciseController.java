package com.kovaxarny.testepites.Controller;

import com.kovaxarny.testepites.Entity.ExerciseItem;
import com.kovaxarny.testepites.Service.ExerciseItemService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.pmw.tinylog.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.List;

public class ExerciseController {

    /**
     * Observable List that stores the queried exercises.
     */
    private ObservableList<ExerciseItem> exerciseItems = FXCollections.observableArrayList();

    @FXML
    private Button quitButton;
    @FXML
    private Button backButton;
    @FXML
    private BorderPane exerciseBorderPane;
    @FXML
    private ListView<ExerciseItem> exerciseListView;
    @FXML
    private TextArea exerciseDetailsTextArea;
    @FXML
    private Label equipmentLabel;

    /**
     * This method connects to the database via JPA, and queries exercises and saves the into an observable list.
     * @param muscles muscles the user wants to work on
     * @param equipment workout with or without equipment
     * @param type is it cardio, or muscle building
     */
    public void queryExercises(List<String> muscles, String equipment, String type) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(
                "ExercisePersistenceUnit");
        EntityManager em = emf.createEntityManager();
        ExerciseItemService exerciseItemService = new ExerciseItemService(em);

        exerciseItems = exerciseItemService.queryExercises(muscles, equipment, type);
        if (exerciseItems == null) {
            Logger.warn("No exercises.");
            return;
        }

        em.close();
        emf.close();
        loadExercises();
    }

    /**
     * After exercise query it lists the exercises in the ListView part of the window.
     */
    private void loadExercises() {
        exerciseListView.getItems().setAll(exerciseItems);
        exerciseListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        exerciseListView.getSelectionModel().selectFirst();

        ExerciseItem item = exerciseListView.getSelectionModel().getSelectedItem();
        exerciseDetailsTextArea.setText(item.getDescription());
        if (item.getEquipment() == null) {
            equipmentLabel.setText("No equipment needed.");
        } else {
            equipmentLabel.setText(item.getEquipment());
        }
    }

    @FXML
    private void handleClickListView() {
        ExerciseItem item = exerciseListView.getSelectionModel().getSelectedItem();
        exerciseDetailsTextArea.setText(item.getDescription());
        if (item.getEquipment() == null) {
            equipmentLabel.setText("No equipment needed.");
        } else {
            equipmentLabel.setText(item.getEquipment());
        }
    }

    @FXML
    public void onBackButtonClicked() {
        BorderPane pane;
        Stage stage = new Stage();
        stage.initOwner(backButton.getScene().getWindow());

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/BMIWindow.fxml"));

        try {
            pane = fxmlLoader.load();
            exerciseBorderPane.getChildren().setAll(pane);
        } catch (IOException e) {
            Logger.error("Unable to load fxml");
            e.printStackTrace();
        }
    }

    @FXML
    public void onQuitButtonClicked() {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }

}
