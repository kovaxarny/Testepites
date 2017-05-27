package com.kovaxarny.testepites.Entity;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


/**
 * Created by kovax on 5/21/2017.
 */
public class ExerciseItemTest {

    private ExerciseItem item;

    @Before
    public void setUp() throws Exception{
        item = new ExerciseItem();

        item.setName("fooName");
        item.setDescription("fooDescription");
        item.setEquipment("fooEquipment");
        item.setType("fooType");
        item.setMuscle("fooMuscle");
    }

    @Test
    public void getEquipment() throws Exception {
        assertEquals("fooEquipment",item.getEquipment());
    }

    @Test
    public void getDescription() throws Exception {
        assertEquals("fooDescription", item.getDescription());
    }

    @Test
    public void toString_Test() throws Exception {
        assertEquals("fooMuscle: fooName",item.toString());
    }
}