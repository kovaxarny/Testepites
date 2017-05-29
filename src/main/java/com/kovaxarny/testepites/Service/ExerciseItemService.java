package com.kovaxarny.testepites.Service;

import com.kovaxarny.testepites.Entity.ExerciseItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;


public class ExerciseItemService {

    /**
     * Manager of the service.
     */
    private EntityManager manager;

    /**
     * Constructor of the ExerciseItemService.
     *
     * @param manager of the service
     */
    public ExerciseItemService(EntityManager manager) {
        this.manager = manager;
    }

    /**
     * This method connects to the database via JPA, and queries exercises.
     *
     * @param muscles   muscles the user wants to work on
     * @param equipment workout with or without equipment
     * @param type      is it cardio, or muscle building
     * @return list of exercises that match the parameters
     */
    public ObservableList queryExercises(List<String> muscles, String equipment, String type) {

        String musclesString = "('";
        musclesString += muscles.stream()
                .map(String::toString)
                .collect(Collectors.joining("', '"));
        musclesString += "')";

        Query query = manager.createQuery("Select e from com.kovaxarny.testepites.Entity.ExerciseItem e " +
                "WHERE e.type = '" + type + "' " +
                "AND e.muscle IN " + musclesString + " " +
                "AND e.equipment " + equipment + " " +
                "ORDER BY e.muscle", ExerciseItem.class);


        return FXCollections.observableArrayList(query.getResultList());
    }
}
