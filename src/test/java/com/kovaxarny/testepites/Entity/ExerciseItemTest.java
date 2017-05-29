package com.kovaxarny.testepites.Entity;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


public class ExerciseItemTest {

    private ExerciseItem item;

    @Before
    public void setUp() throws Exception{
        item = new ExerciseItem();

        item.setId("1");
        item.setName("Cycling");
        item.setMuscle("Cardio");
        item.setDescription("Indoor cycling is a form of exercise that focuses on endurance, stamina, strength, intensity, intervals and recovery");
        item.setType("Cardio");
        item.setEquipment("Bicycle");

    }

    @Test
    public void getEquipment() throws Exception {
        assertEquals("Bicycle",item.getEquipment());
    }

    @Test
    public void getDescription() throws Exception {
        assertEquals("Indoor cycling is a form of exercise that focuses on endurance, stamina, strength, intensity, intervals and recovery", item.getDescription());
    }

    @Test
    public void toString_Test() throws Exception {
        assertEquals("Cardio: Cycling",item.toString());
    }
}