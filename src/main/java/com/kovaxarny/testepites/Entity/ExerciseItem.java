package com.kovaxarny.testepites.Entity;

import javax.persistence.*;

@Entity
@Table(name = "EXERCISES")
public class ExerciseItem {

    /**
     * ID value of an ExerciseItem.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private String id;
    /**
     * Name value of an ExerciseItem.
     */
    @Column(name = "NAME")
    private String name;
    /**
     * Muscle value of an ExerciseItem.
     */

    @Column(name = "MUSCLE")
    private String muscle;
    /**
     * Description value of an ExerciseItem.
     */
    @Column(name = "DESCRIPTION")
    private String description;
    /**
     * Equipment value of an ExerciseItem.
     */
    @Column(name = "EQUIPMENT")
    private String equipment;
    /**
     * Type value of an ExerciseItem.
     */
    @Column(name = "TYPE")
    private String type;

    /**
     * public constructor of the ExerciseItem with no parameters.
     */
    public ExerciseItem() {
    }

    /**
     * Constructor with parameters.
     *
     * @param id          id of the exercise
     * @param name        name of the exercise
     * @param muscle      muscle worked by the exercise
     * @param description description of the exercise
     * @param equipment   equipment needed for the exercise
     * @param type        type of the exercise
     */
    public ExerciseItem(String id, String name, String muscle, String description, String equipment, String type) {
        this.id = id;
        this.name = name;
        this.muscle = muscle;
        this.description = description;
        this.equipment = equipment;
        this.type = type;
    }

    /**
     * Used to return the equipment needed for the exercise.
     *
     * @return The equipment needed for the given exercise
     */
    public String getEquipment() {
        return equipment;
    }

    /**
     * Used to get the description of the exercise.
     *
     * @return The description of the exercise
     */
    public String getDescription() {
        return description;
    }

    /**
     * Used to set the id value of the exercise.
     *
     * @param id the string to be set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Used to set the name value of the exercise.
     *
     * @param name the string to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Used to set the name value of the exercise.
     *
     * @param muscle the string to be set
     */
    public void setMuscle(String muscle) {
        this.muscle = muscle;
    }

    /**
     * Used to set the name value of the exercise.
     *
     * @param description the string to be set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Used to set the name value of the exercise.
     *
     * @param type the string to be set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Used to set the name value of the exercise.
     *
     * @param equipment the string to be set
     */
    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    /**
     * Used to get the exercise muscle and name.
     *
     * @return The muscle and the name of the exercise as a string
     */
    @Override
    public String toString() {
        return muscle + ": " + name;
    }
}
