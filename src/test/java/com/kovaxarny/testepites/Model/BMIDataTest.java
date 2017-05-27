package com.kovaxarny.testepites.Model;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by kovax on 5/26/2017.
 */
public class BMIDataTest {
    private BMIData user;

    @Before
    public void setUp() throws Exception{
        user = new BMIData("Male",20,170,73);
    }

    @Test
    public void getInformation() throws Exception {
        assertEquals("overweight. You should eat 250 calories less",user.getInformation());
    }

    @Test
    public void getSuggestion() throws Exception {
        assertEquals("either building muscle or cardio",user.getSuggestion());
    }

    @Test
    public void getBMI() throws Exception {
        assertEquals(25.3,user.getBMI(),0.1);
    }

    @Test
    public void getBMR() throws Exception {
        assertEquals(1697.5,user.getBMR(),0.1);
    }

}